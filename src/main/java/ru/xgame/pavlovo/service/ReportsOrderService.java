package ru.xgame.pavlovo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.xgame.pavlovo.model.ReportsOrder;

import java.util.List;

public class ReportsOrderService {

    private final SessionFactory factory;

    public ReportsOrderService(SessionFactory factory) {
        this.factory = factory;
    }

    public List<ReportsOrder> findAll(){
        try(Session session = factory.openSession()) {
            return session.createQuery("from ReportsOrder ",ReportsOrder.class).list();
        }
    }

    public ReportsOrder findByIdReports(Integer reportsId){
        try(Session session = factory.openSession()){
            return session.get(ReportsOrder.class,reportsId);
        }
    }

}
