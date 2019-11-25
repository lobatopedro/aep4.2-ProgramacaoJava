package com.unicesumar.AEP42.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class LivroEntity {
	
	@Id
	private String id;
	
	@NotBlank
	private String isbn;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String descricaoLivro;
	
	@NotEmpty
	private Set<AutorEntity> autores;
	
	@NotBlank
	private String editora;
	
	public LivroEntity() {
		id = UUID.randomUUID().toString();
	}
	
	public LivroEntity(String isbn, String titulo, Set<AutorEntity> autores, String editora) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autores = autores;
        this.editora = editora;
    }

    public LivroEntity(String isbn, String titulo, String editora) {
        this(isbn, titulo, new HashSet<>(), editora);
    }
    
    public void addAutor(AutorEntity autor) {
        this.autores.add(autor);
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitle(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricaoLivro() {
        return descricaoLivro;
    }

    public void setDescription(String descricaoLivro) {
        this.descricaoLivro = descricaoLivro;
    }

    public Set<AutorEntity> getAutores() {
        return autores;
    }

    public void setAuthors(Set<AutorEntity> autores) {
        this.autores = autores;
    }

    public String getEditora() {
        return editora;
    }

    public void setPublisher(String editora) {
        this.editora = editora;
    }
	

}
