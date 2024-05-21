package br.com.ewerton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ewerton.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
