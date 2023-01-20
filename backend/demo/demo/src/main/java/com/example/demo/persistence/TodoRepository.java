package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

import com.example.demo.model.TodoEntity;


import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{
	List<TodoEntity> findByUserId(String userId);
	
	@Lock(value = LockModeType.PESSIMISTIC_WRITE)
    	List<TodoEntity> findByUserIdWithPessimisticLock(String id);
}
