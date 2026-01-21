package com.ezequiel.tema03.kanbanHibernate.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.ezequiel.tema03.kanbanHibernate.models.Columna;
import com.ezequiel.tema03.kanbanHibernate.util.HibernateUtil;

public class ColumnaRepository {

  public void guardar(Columna columna) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.persist(columna);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null)
        transaction.rollback();
      e.printStackTrace();
    }
  }

  public Columna buscarPorId(int id) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.get(Columna.class, id);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void actualizar(Columna columna) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.merge(columna);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null)
        transaction.rollback();
      e.printStackTrace();
    }
  }

  public void eliminar(Columna columna) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.remove(columna);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null)
        transaction.rollback();
      e.printStackTrace();
    }
  }
}
