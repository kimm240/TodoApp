package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoService{
	
	@Autowired
	private TodoRepository repository;
	
	public String testService() {
		//TodoEntity ����
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		//TodoEntity ����
		repository.save(entity);
		//TodoEntity �˻�
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return savedEntity.getTitle();
	}

	public List<TodoEntity> create(final TodoEntity entity){
		// Validations
		validate(entity);
		//entity�� database�� �����Ѵ�.
		repository.save(entity);
		log.info("Entity id : {} is saved.", entity.getId());
		//������ entity�� �����ϴ� �� list�� return�Ѵ�.
		return repository.findByUserId(entity.getUserId());
	}
	
	public List<TodoEntity> retrieve(final String userId){
		return repository.findByUserId(userId);
	}
	
	public List<TodoEntity> update(TodoEntity entity){
		// (1) ������ ��ƼƼ�� ��ȿ���� Ȯ���Ѵ�.
		validate(entity);
		
		// (2) �Ѱܹ��� ��ƼƼ id�� �̿��� TodoEntity�� �����´�. �������� �ʴ� ��ƼƼ�� ������Ʈ�� �� ���� �����̴�.
		final Optional<TodoEntity> original = repository.findById(entity.getId());
		
		if(original.isPresent()) {
			// (3) ��ȯ�� TodoEntity�� �����ϸ� ���� �� entity ������ ���� �����.
			final TodoEntity todo = original.get();
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			
			// (4) �����ͺ��̽��� �� ���� �����Ѵ�.
			repository.save(todo);
		}
		
		return retrieve(entity.getUserId());
	}
	
	public List<TodoEntity> delete(TodoEntity entity){
		// (1) ������ ��ƼƼ�� ��ȿ���� Ȯ���Ѵ�.
		validate(entity);
		
		try {
			// (2) ��ƼƼ�� �����Ѵ�.
			repository.delete(entity);
		}catch(Exception e) {
			// (3) exception �߻� �� id�� exception�� �α��Ѵ�.
			log.error("error deleting entity ", entity.getId(), e);
			
			// (4) ��Ʈ�ѷ��� exception�� ������.
			// �����ͺ��̽� ���� ������ ĸ��ȭ�Ϸ��� e�� �������� �ʰ� �� exception ������Ʈ�� �����Ѵ�.
			throw new RuntimeException("error deleting entity " + entity.getId());
		}
		
		// (5) �� Todo ����Ʈ�� ������ �����Ѵ�.
		return retrieve(entity.getUserId());
	}
	
	//�����丵�� �ż���
	private void validate(final TodoEntity entity) {
		//Validations
		if(entity == null) {
			log.warn("Entity cannot be null.");
			throw new RuntimeException("Entity cannot be null.");
		}
				
		if(entity.getUserId() == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}
}