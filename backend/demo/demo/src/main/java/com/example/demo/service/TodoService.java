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

	public List<TodoEntity> create(final TodoEntity entity){
		// Validation
		validate(entity);
		//entity를 database에 저장한다.
		repository.save(entity);
		log.info("Entity id : {} is saved.", entity.getId());
		//저장한 entity를 포함하는 새 list를 return한다.
		return repository.findByUserId(entity.getUserId());
	}
	
	public List<TodoEntity> retrieve(final String userId){
		return repository.findByUserId(userId);
	}
	
	public List<TodoEntity> update(TodoEntity entity){
		//Validation
		validate(entity);
		
		//넘겨받은 엔티티 id를 이용해 TodoEntity를 가져온다.
		final Optional<TodoEntity> original = repository.findById(entity.getId());
		
		if(original.isPresent()) {
			//반환된 TodoEntity가 존재하면, 해당 값을 새 entity 값으로 바꿔준다.
			final TodoEntity todo = original.get();
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			
			//dtabase에 새로운 entity를 저장한다.
			repository.save(todo);
		}
		
		return retrieve(entity.getUserId());
	}
	
	public List<TodoEntity> delete(TodoEntity entity){
		//Validation
		validate(entity);
		
		try {
			//entity 삭제			
			repository.delete(entity);
		}catch(Exception e) {
			//exception 발생 시 id와 exception을 로깅한다.
			log.error("error deleting entity ", entity.getId(), e);
			
			//컨트롤러로 exception을 보낸다.
			throw new RuntimeException("error deleting entity " + entity.getId());
		}
		
		//갱신된 Todo 리스트를 가져와 리턴한다.
		return retrieve(entity.getUserId());
	}
	
	//리팩토링한 매서드
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
