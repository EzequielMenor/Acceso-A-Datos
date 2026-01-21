package com.ezequiel.tema03.kanbanHibernate.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ezequiel.tema03.kanbanHibernate.models.Tablero;
import com.ezequiel.tema03.kanbanHibernate.models.Usuario;
import com.ezequiel.tema03.kanbanHibernate.util.HibernateUtil;

public class TableroRepository {

  public void guardar(Tablero tablero) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.persist(tablero);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null)
        transaction.rollback();
      e.printStackTrace();
    }
  }

  public List<Tablero> listarPorUsuario(Usuario usuario) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // OJO: "Tablero" con T mayúscula porque es la CLASE, no la tabla
      String hql = "FROM Tablero t WHERE t.usuario.id = :usuarioId";
      Query<Tablero> query = session.createQuery(hql, Tablero.class);
      query.setParameter("usuarioId", usuario.getId());
      return query.list();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public Tablero buscarPorId(int id) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.get(Tablero.class, id);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void eliminar(Tablero tablero) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.remove(tablero);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null)
        transaction.rollback();
      e.printStackTrace();
    }
  }
}