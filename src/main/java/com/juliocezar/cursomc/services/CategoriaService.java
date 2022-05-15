package com.juliocezar.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliocezar.cursomc.domain.Categoria;
import com.juliocezar.cursomc.repositories.CategoriaRepository;
import com.juliocezar.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrato! Id:" + id + ", Tipo: " + Categoria.class.getName()));
	}

}
