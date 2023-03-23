package com.example.demo.facade;

import com.example.demo.service.TodoService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import com.example.demo.model.TodoEntity;

import java.util.concurrent.TimeUnit;

@Component
public class RedissonLockTodoFacade {

    private RedissonClient redissonClient;

    private todoService todoService;

    public RedissonLockStockFacade(RedissonClient redissonClient, TodoService todoService) {
        this.redissonClient = redissonClient;
        this.todoService = todoService;
    }

    public void update(TodoEntity entity) {
        RLock lock = redissonClient.getLock(key.toString());

        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);

            if (!available) {
                System.out.println("lock 획득 실패");
                return;
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