package ru.xgame.pavlovo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.xgame.pavlovo.model.Hardware;

import java.util.List;

public class HardwareService implements Service<Hardware,Integer> {

    private final SessionFactory factory;

    public HardwareService(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Hardware findById(Integer id) {
        try(Session session = factory.openSession()){
            return session.get(Hardware.class,id);
        }
    }

    @Override
    public List<Hardware> findAll() {
        try(Session session = factory.openSession()){
            return session.createQuery("from Hardware",Hardware.class).list();
        }
    }

    @Override
    public void save(Hardware hardware) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(hardware);
            transaction.commit();
        }
    }

    @Override
    public void update(Hardware hardware) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(hardware);
            transaction.commit();
        }
    }

    @Override
    public void delete(Hardware hardware) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(hardware);
            transaction.commit();
        }
    }
}
