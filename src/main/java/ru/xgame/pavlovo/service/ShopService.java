package ru.xgame.pavlovo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.xgame.pavlovo.model.Shop;

import java.util.List;

public class ShopService implements Service<Shop,String> {

    private final SessionFactory factory;

    public ShopService(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Shop findById(String id) {
        try(Session session = factory.openSession()){
            return session.get(Shop.class,id);
        }
    }

    @Override
    public List<Shop> findAll() {
        try(Session session = factory.openSession()){
            return session.createQuery("from Shop",Shop.class).list();
        }
    }

    @Override
    public void save(Shop shop) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(shop);
            transaction.commit();
        }
    }

    @Override
    public void update(Shop shop) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(shop);
            transaction.commit();
        }
    }

    @Override
    public void delete(Shop shop) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(shop);
            transaction.commit();
        }
    }
}
