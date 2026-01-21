package com.ezequiel.tema03.kanbanHibernate.repository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.ezequiel.tema03.kanbanHibernate.models.Etiqueta;
import com.ezequiel.tema03.kanbanHibernate.util.HibernateUtil;

public class EtiquetaRepository {

  public void guardar(Etiqueta etiqueta) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.persist(etiqueta);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null)
        transaction.rollback();
      e.printStackTrace();
    }
  }

  public List<Etiqueta> listarTodas() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery("FROM Etiqueta", Etiqueta.class).list();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public Etiqueta buscarPorId(int id) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.get(Etiqueta.class, id);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
