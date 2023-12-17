package com.example.APIRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

import com.example.APIRest.entidade.Livros;
import com.example.APIRest.repositorio.LivrosRepositorio;

@RestController
@RequestMapping ("/livros")
@CrossOrigin
public class LivrosController {
	@Autowired
	LivrosRepositorio repo;
	

	@GetMapping
	public ResponseEntity<List<Livros>> getLivros(){
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livros> getLivrosPorId(@PathVariable("id") Long id){
		Optional<Livros> opLivros = repo.findById(id);
		try {
			Livros Lv = opLivros.get();
			return ResponseEntity.status(HttpStatus.OK).body(Lv);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}		
	}
	
	@PostMapping
	public ResponseEntity<Livros> inserirLivro(@RequestBody Livros livro){
		Livros lv = repo.save(livro);
		return ResponseEntity.status(HttpStatus.CREATED).body(lv);
	}


	@PutMapping
	public ResponseEntity<Livros> alterarLivros (@RequestBody Livros livro){
		Optional<Livros> opLivros = repo.findById(livro.getId());
		try {
			Livros lv = opLivros.get();
			lv.setTitulo(livro.getTitulo());
			lv.setAutor(livro.getAutor());
			lv.setEditora(livro.getEditora());
			lv.setAno(livro.getAno());
			repo.save(lv);
			return ResponseEntity.status(HttpStatus.OK).body(lv);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
		
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Livros> excluirLivro(@PathVariable("id") Long id){
		Optional<Livros> opLivros = repo.findById(id);
		try {
			Livros lv = opLivros.get();
			repo.delete(lv);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
			
}
