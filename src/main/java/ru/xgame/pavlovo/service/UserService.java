package ru.xgame.pavlovo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.xgame.pavlovo.model.User;

import java.util.List;

public class UserService implements Service<User,String> {

    private final SessionFactory factory;

    public UserService(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public User findById(String id) {
        try(Session session = factory.openSession()) {
            return session.get(User.class,id);
        }
    }

    @Override
    public List<User> findAll() {
        try(Session session = factory.openSession()){
            return session.createQuery("from User",User.class).list();
        }
    }

    @Override
    public void save(User user) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public void update(User user) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
    }

    @Override
    public void delete(User user) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        }
    }
}
