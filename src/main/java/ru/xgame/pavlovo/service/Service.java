package ru.xgame.pavlovo.service;

import java.util.List;

public interface Service<Entity,Integer> {
        Entity findById(Integer id);
        List<Entity> findAll();
        void save(Entity entity);
        void update(Entity entity);
        void delete(Entity entity);
}
