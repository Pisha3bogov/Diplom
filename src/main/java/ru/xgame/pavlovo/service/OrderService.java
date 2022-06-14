package ru.xgame.pavlovo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.xgame.pavlovo.model.Order;

import javax.management.Query;
import java.util.List;

public class OrderService implements Service<Order,Integer> {
    private final SessionFactory factory;

    public OrderService(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Order findById(Integer id) {
        try(Session session = factory.openSession()){
            return session.get(Order.class,id);
        }
    }

    @Override
    public List<Order> findAll() {
        try(Session session = factory.openSession()){
            return session.createQuery("from Order",Order.class).list();
        }
    }

    @Override
    public void save(Order order) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        }
    }

    @Override
    public void update(Order order) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
        }
    }

    @Override
    public void delete(Order order) {
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
        }
    }
}
