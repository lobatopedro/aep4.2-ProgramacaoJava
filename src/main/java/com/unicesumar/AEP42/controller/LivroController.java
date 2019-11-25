package com.unicesumar.AEP42.controller;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.unicesumar.AEP42.entity.LivroEntity;
import com.unicesumar.AEP42.exception.BookIsbnAlreadyExistsException;
import com.unicesumar.AEP42.exception.BookNotFoundException;
import com.unicesumar.AEP42.repository.LivroRepository;

@RestController
@RequestMapping(value = "/api/livros")
public class LivroController {
	
	private final LivroRepository livroRepo;

    public LivroController(LivroRepository livroRepo) {
        this.livroRepo = livroRepo;
    }

    @PostMapping
    public ResponseEntity<?> criarLivro(@Valid @RequestBody LivroEntity livro, UriComponentsBuilder ucBuilder) {
        if (livroRepo.findByIsbn(livro.getIsbn()).isPresent()) {
            throw new BookIsbnAlreadyExistsException(livro.getIsbn());
        }
        livroRepo.save(livro);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/livros/{isbn}").buildAndExpand(livro.getIsbn()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<LivroEntity> getBook(@PathVariable("isbn") String isbn) {
        return livroRepo.findByIsbn(isbn)
                .map(livro -> new ResponseEntity<>(livro, HttpStatus.OK))
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }


    @PutMapping("/{isbn}")
    public ResponseEntity<LivroEntity> atualizarLivro(@PathVariable("isbn") String isbn, @Valid @RequestBody LivroEntity livro) {
        return livroRepo.findByIsbn(isbn)
                .map(livroParaAtualizar -> {
                	livroParaAtualizar.setIsbn(livro.getIsbn());
                	livroParaAtualizar.setTitle(livro.getTitulo());
                	livroParaAtualizar.setDescription(livro.getDescricaoLivro());
                	livroParaAtualizar.setAuthors(livro.getAutores());
                	livroParaAtualizar.setPublisher(livro.getEditora());
                    livroRepo.save(livroParaAtualizar);

                    return new ResponseEntity<>(livroParaAtualizar, HttpStatus.OK);
                })
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deletarLivro(@PathVariable("isbn") String isbn) {
        return livroRepo.findByIsbn(isbn)
                .map(livro -> {
                    livroRepo.delete(livro);
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                })
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

	
}
