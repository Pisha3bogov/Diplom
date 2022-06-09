package ru.xgame.pavlovo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.xgame.pavlovo.model.Type;

import java.util.List;

public class TipeService implements Service<Type,String> {

    private final SessionFactory factory;

    public TipeService(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Type findById(String id) {
        try(Session session = factory.openSession()){
            return session.get(Type.class,id);
        }
    }

    @Override
    public List<Type> findAll() {
        try(Session session = factory.openSession()){
            return session.createQuery("from Type",Type.class).list();
        }
    }

    @Override
    public void save(Type type) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(type);
            transaction.commit();
        }
    }

    @Override
    public void update(Type type) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(type);
            transaction.commit();
        }
    }

    @Override
    public void delete(Type type) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(type);
            transaction.commit();
        }
    }
}
