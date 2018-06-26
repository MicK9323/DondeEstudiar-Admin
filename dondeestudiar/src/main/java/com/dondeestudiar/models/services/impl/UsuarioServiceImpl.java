package com.dondeestudiar.models.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dondeestudiar.models.dao.IUsuarioDAO;
import com.dondeestudiar.models.entities.Usuario;
import com.dondeestudiar.models.services.IUsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	IUsuarioDAO usuarioDAO;

	@Override
	@Transactional(readOnly = true)
	public Usuario login(String usuario, String clave) {
		Usuario user = null;
		try {
			user = usuarioDAO.login(usuario, clave);
		} catch (Exception e) {
			user = null;
			e.printStackTrace();
		}
		return user;
	}

    @Override
    public Usuario findByDni(String dni) {
        return usuarioDAO.findById(dni).orElse(null);
    }

    @Override
    public void disabledUser(String dni) {
        usuarioDAO.sp_disabledUsuario(dni);
    }

    @Override
    public void enabledUser(String dni) {
        usuarioDAO.sp_enabledUsuario(dni);
    }

    @Override
	public void mergeUsuario(Usuario user) {
		usuarioDAO.save(user);
	}

	@Override
	public List<Usuario> listAll() {
		return usuarioDAO.findAll();
	}


}
