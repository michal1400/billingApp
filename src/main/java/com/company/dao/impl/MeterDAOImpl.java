package com.company.dao.impl;

import com.company.dao.IMeterDAO;
import com.company.model.Meter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class MeterDAOImpl implements IMeterDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public boolean addMeter(Meter meter) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(meter);
            tx.commit();
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateValue(Meter meter) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(meter);
            tx.commit();
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<Meter> getAllMeter() {
        Session session = this.sessionFactory.openSession();
        Query<Meter> query = session.createQuery("FROM com.company.model.Meter");
        List<Meter> meters = query.getResultList();
        session.close();
        return meters;
    }

    @Override
    public Meter getMeterByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Meter> query = session.createQuery("FROM com.company.model.Meter WHERE id = :id");
        query.setParameter("id", id);
        Meter result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Nie znaleziono odczytu !!");
        }
        session.close();
        return result;
    }

    @Override
    public Meter getMeterByUserId(int user_id) {
        Session session = this.sessionFactory.openSession();
        Query<Meter> query = session.createQuery("FROM com.company.model.Meter WHERE user_id = :user_id");
        query.setParameter("user_id", user_id);
        Meter result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Nie znaleziono odczytu !!");
        }
        session.close();
        return result;
    }
}
