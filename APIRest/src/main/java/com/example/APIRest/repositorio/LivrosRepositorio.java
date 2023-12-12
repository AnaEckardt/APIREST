package com.example.APIRest.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.APIRest.entidade.Livros;

@Repository
public interface LivrosRepositorio extends JpaRepository<Livros, Long>{
	
}

