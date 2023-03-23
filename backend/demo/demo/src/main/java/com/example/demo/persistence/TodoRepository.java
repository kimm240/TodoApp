package com.example.demo.persistence;

import com.example.demo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.query;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{
	List<TodoEntity> findByUserId(String userId);
	
	//@Lock(value = LockModeType.OPTIMISTIC)
	//@Query("select * from Todo t where t.userId = ?1")
	//List<TodoEntity> findByUserIdWithOptimisticLock(String userId);
	
	//@Lock(value = LockModeType.PESSIMISTIC_WRITE)
    	//List<TodoEntity> findByUserIdWithPessimisticLock(String userId);
}
