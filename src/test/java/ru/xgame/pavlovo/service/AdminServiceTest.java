package ru.xgame.pavlovo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.*;
import org.junit.Test;
import org.junit.jupiter.api.*;
import ru.xgame.pavlovo.model.Admin;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AdminServiceTest {

    private static SessionFactory factory;
    private Session session;

    private final AdminService adminService;

    public AdminServiceTest() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        this.adminService = new AdminService(factory);
        session = factory.openSession();
    }

    @Test
    public void findByLogin(){
        System.out.println("Running testGet...");

        String id = "2";

        Admin admin = adminService.findByLogin(id);

        assertEquals("2", admin.getLogin());
    }

    @Test
    public void findAll() {
        System.out.println("Running testList...");

        List<Admin> resultList = adminService.findAll();

        Assertions.assertFalse(resultList.isEmpty());
    }

    @Test
    public void save() {
        System.out.println("Running testCreate...");

        Admin admin = new Admin("5", "5","0");
        adminService.save(admin);

        Admin adminOnBD = adminService.findByLogin("5");

        assertEquals(adminOnBD.getId(),admin.getId());
    }

    @Test
    public void update() {
        System.out.println("Running testUpdate...");

        Admin admin = adminService.findByLogin("2");

        admin.setPassword("3");

        adminService.update(admin);

        Admin updatedProduct = adminService.findByLogin("2");

        assertEquals(admin.getPassword(), updatedProduct.getPassword());
    }

    @Test
    public void delete() {
        System.out.println("Running testDelete...");

        Admin searchAd = adminService.findByLogin("5");
        adminService.delete(searchAd);
        Admin deletedAdmin = adminService.findByLogin("5");

        Assertions.assertNull(deletedAdmin);
    }
}