package ru.xgame.pavlovo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.xgame.pavlovo.model.Admin;

import java.util.List;

public class AdminService implements Service<Admin,String> {

    private final SessionFactory factory;

    public AdminService(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Admin findById(String id) {
        try(Session session = factory.openSession()){
            return session.get(Admin.class,id);
        }
    }

    @Override
    public List<Admin> findAll() {
        try(Session session = factory.openSession()) {
            return session.createQuery("From Admin",Admin.class).list();
        }
    }

    @Override
    public void save(Admin admin) {
        try(Session session = factory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.save(admin);
            transaction.commit();
        }
    }

    @Override
    public void update(Admin admin) {
        try(Session session = factory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.update(admin);
            transaction.commit();
        }
    }

    @Override
    public void delete(Admin admin) {
        try(Session session = factory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.delete(admin);
            transaction.commit();
        }
    }

    public Admin findByLogin(String str){
        try(Session session = factory.openSession()) {
            Query<Admin> query = session.createQuery("from Admin where login = :login",Admin.class);
            query.setParameter("login",str);
            return query.uniqueResult();
        }
    }
}
