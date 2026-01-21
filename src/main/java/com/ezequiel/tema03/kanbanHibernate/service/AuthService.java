package com.ezequiel.tema03.kanbanHibernate.service;

import com.ezequiel.tema03.kanbanHibernate.models.Usuario;
import com.ezequiel.tema03.kanbanHibernate.repository.UsuarioRepository;
import com.ezequiel.tema03.kanbanHibernate.util.PasswordUtil;

public class AuthService {
  private UsuarioRepository usuarioRepository;

  public AuthService() {
    this.usuarioRepository = new UsuarioRepository();
  }

  public Usuario registrar(String email, String passwd) throws Exception {
    // Validar que existe.
    if (usuarioRepository.buscarPorEmail(email) != null) {
      throw new Exception("El email ya está registrado");
    }

    // Hashear passwd
    String hash = PasswordUtil.hashPassword(passwd);

    // Crear y guardar
    Usuario usuario = new Usuario(email, hash);
    usuarioRepository.guardar(usuario);

    return usuario;
  }

  public Usuario login(String email, String passwd) throws Exception {
    Usuario usuario = usuarioRepository.buscarPorEmail(email);

    if (usuario == null || !PasswordUtil.verifyPassword(passwd, usuario.getPasswd())) {
      throw new Exception("Credenciales incorrectas");
    }
    return usuario;
  }
}
