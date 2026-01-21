package com.ezequiel.tema03.kanbanHibernate.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.ezequiel.tema03.kanbanHibernate.models.Tarjeta;
import com.ezequiel.tema03.kanbanHibernate.util.HibernateUtil;

public class TarjetaRepository {

  public void guardar(Tarjeta tarjeta) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.persist(tarjeta);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null)
        transaction.rollback();
      e.printStackTrace();
    }
  }

  public void actualizar(Tarjeta tarjeta) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.merge(tarjeta); // merge actualiza si existe
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null)
        transaction.rollback();
      e.printStackTrace();
    }
  }

  public Tarjeta buscarPorId(int id) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.get(Tarjeta.class, id);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void eliminar(Tarjeta tarjeta) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.remove(tarjeta);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null)
        transaction.rollback();
      e.printStackTrace();
    }
  }

  public java.util.List<Tarjeta> buscarPorEtiquetaYUsuario(int etiquetaId, int usuarioId) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      String hql = "SELECT t FROM Tarjeta t JOIN t.etiquetas e " +
          "WHERE e.id = :etiquetaId " +
          "AND t.columna.tablero.usuario.id = :usuarioId";

      Query<Tarjeta> query = session.createQuery(hql, Tarjeta.class);
      query.setParameter("etiquetaId", etiquetaId);
      query.setParameter("usuarioId", usuarioId);
      return query.list();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
