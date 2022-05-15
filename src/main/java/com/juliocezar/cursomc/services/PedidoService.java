package com.juliocezar.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliocezar.cursomc.domain.Pedido;
import com.juliocezar.cursomc.repositories.PedidoRepository;
import com.juliocezar.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrato! Id:" + id + ", Tipo: " + Pedido.class.getName()));
	}

}
