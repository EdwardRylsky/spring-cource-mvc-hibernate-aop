package com.zaurtregulov.spring.mvc_hibernate_aop.dao;

import com.zaurtregulov.spring.mvc_hibernate_aop.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private boolean isFirstSelect = true;
    @Transactional
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();

        if (isFirstSelect) {
            fillEmployee(session);
            isFirstSelect = false;
        }
        Query<Employee> fromEmployees = session.createQuery("from Employee", Employee.class);
        return fromEmployees.getResultList();
    }

    private static void fillEmployee(Session session) {
        session.save(new Employee(1, "Zaur", "Tregulov", "IT", 500));
        session.save(new Employee(2, "Oleg", "Ivanov", "Sales", 700));
        session.save(new Employee(3, "Nina", "Sidorova", "HR", 850));
    }
}