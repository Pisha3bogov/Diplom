package ru.xgame.pavlovo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class StatusService implements Service<StatusService,String> {

    private final SessionFactory factory;

    public StatusService(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public StatusService findById(String id) {
        try(Session session = factory.openSession()){
            return session.get(StatusService.class,id);
        }
    }

    @Override
    public List<StatusService> findAll() {
        try(Session session = factory.openSession()){
            return session.createQuery("from Status", StatusService.class).list();
        }
    }

    @Override
    public void save(StatusService status) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(status);
            transaction.commit();
        }
    }

    @Override
    public void update(StatusService status) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(status);
            transaction.commit();
        }
    }

    @Override
    public void delete(StatusService status) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(status);
            transaction.commit();
        }
    }
}
