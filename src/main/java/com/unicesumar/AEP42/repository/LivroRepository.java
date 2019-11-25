package com.unicesumar.AEP42.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unicesumar.AEP42.entity.LivroEntity;

@Repository
public interface LivroRepository extends JpaRepository<LivroEntity, String> {

	Optional<LivroEntity> findByIsbn(String isbn);

}
