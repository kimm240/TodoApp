package com.example.demo.facade;

import com.example.demo.service.TodoService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import com.example.demo.model.TodoEntity;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Component
public class RedissonLockTodoFacade {
	
    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private TodoService todoService;

    public List<TodoEntity> update(TodoEntity entity) {
        RLock lock = redissonClient.getLock(entity.getId().toString());

        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);

            if (!available) {
                System.out.println("lock �쉷�뱷 �떎�뙣");
                return null;
            }

            List<TodoEntity> entities = todoService.update(entity);
	    return entities;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
