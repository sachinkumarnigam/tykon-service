package com.tykon.api.framework.redis.repository;


import org.springframework.stereotype.Repository;


@Repository
public abstract class BaseRedisRepository<String, E>{

    /*private RedisTemplate<String,E> redisTemplate;
    private HashOperations hashOperations;
    
    public BaseRedisRepository(RedisTemplate<String, E> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    Getting all Items from tSable
    public Map<Integer,E> getAllItems(){
        return hashOperations.entries(KEY);
    }

    Getting a specific item by item id from table
    public E get(String key, int itemId){
        return (E) hashOperations.get(key,itemId);
    }

    Adding an item into redis database
    public void addItem(String key, E item){
        hashOperations.put(key,null,item);
    }

    delete an item from database
    public void deleteItem(String key, int id){
        hashOperations.delete(key,id);
    }

    update an item from database
    public void updateItem(String key, E item){
        addItem(key, item);
    }*/
}
