package com.juliocezar.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juliocezar.cursomc.domain.Categoria;
import com.juliocezar.cursomc.domain.Estado;
import com.juliocezar.cursomc.domain.Produto;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
	
	

}
