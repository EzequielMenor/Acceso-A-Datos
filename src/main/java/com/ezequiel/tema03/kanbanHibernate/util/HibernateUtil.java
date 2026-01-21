package com.ezequiel.tema03.kanbanHibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
  private static final SessionFactory sessionFactory;

  static {
    try {
      Configuration configuration = new Configuration().configure();
      // Carga la configuración desde hibernate.cfg.xml
      configuration.addAnnotatedClass(com.ezequiel.tema03.kanbanHibernate.models.Usuario.class);
      configuration.addAnnotatedClass(com.ezequiel.tema03.kanbanHibernate.models.Tablero.class);
      configuration.addAnnotatedClass(com.ezequiel.tema03.kanbanHibernate.models.Columna.class);
      configuration.addAnnotatedClass(com.ezequiel.tema03.kanbanHibernate.models.Tarjeta.class);
      configuration.addAnnotatedClass(com.ezequiel.tema03.kanbanHibernate.models.Etiqueta.class);
      sessionFactory = configuration.buildSessionFactory();
    } catch (Throwable ex) {
      System.err.println("Fallo al crear SessionFactory: " + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static void shutdown() {
    getSessionFactory().close();
  }
}
