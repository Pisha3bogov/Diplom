package ru.xgame.pavlovo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.xgame.pavlovo.model.Hardware;
import ru.xgame.pavlovo.model.Reports;

import java.util.List;

public class ReportsService implements Service<Reports,Integer>{
    private final SessionFactory factory;

    public ReportsService(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Reports findById(Integer id) {
        try(Session session = factory.openSession()){
            return session.get(Reports.class,id);
        }
    }

    @Override
    public List<Reports> findAll() {
        try(Session session = factory.openSession()){
            return session.createQuery("from Reports ",Reports.class).list();
        }
    }

    @Override
    public void save(Reports reports) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(reports);
            transaction.commit();
        }
    }

    @Override
    public void update(Reports reports) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(reports);
            transaction.commit();
        }
    }

    @Override
    public void delete(Reports reports) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(reports);
            transaction.commit();
        }
    }
}
