package ru.xgame.pavlovo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.xgame.pavlovo.model.Sale;

import java.util.List;

public class SaleService implements Service<Sale,Integer> {

    private final SessionFactory factory;

    public SaleService(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Sale findById(Integer id) {
        try(Session session = factory.openSession()){
            return session.get(Sale.class,id);
        }
    }

    @Override
    public List<Sale> findAll() {
        try(Session session = factory.openSession()){
            return session.createQuery("from Sale",Sale.class).list();
        }
    }

    @Override
    public void save(Sale sale) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(sale);
            transaction.commit();
        }
    }

    @Override
    public void update(Sale sale) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(sale);
            transaction.commit();
        }
    }

    @Override
    public void delete(Sale sale) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(sale);
            transaction.commit();
        }
    }
}
