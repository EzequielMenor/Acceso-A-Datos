package com.ezequiel.tema03.kanbanHibernate.repository;

import com.ezequiel.tema03.kanbanHibernate.models.Usuario;
import com.ezequiel.tema03.kanbanHibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UsuarioRepository {

  // Guardar un usuario en la base de datos
  public void guardar(Usuario usuario) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // Empezamos la transacción
      transaction = session.beginTransaction();

      // Guardamos el objeto
      session.persist(usuario);

      // Confirmamos los cambios
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback(); // Si falla, deshacemos
      }
      e.printStackTrace();
    }
  }

  // Buscar un usuario por su email
  public Usuario buscarPorEmail(String email) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // HQL: Hibernate Query Language (usamos el nombre de la Clase, no de la tabla)
      String hql = "FROM Usuario u WHERE u.email = :email";
      Query<Usuario> query = session.createQuery(hql, Usuario.class);
      query.setParameter("email", email);

      // Devuelve el único resultado o null si no existe
      return query.uniqueResult();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
