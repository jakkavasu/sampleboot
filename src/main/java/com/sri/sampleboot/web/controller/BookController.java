package com.sri.sampleboot.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.sri.sampleboot.persistence.model.Book;
import com.sri.sampleboot.persistence.repository.BookRepository;
import com.sri.sampleboot.web.exception.BookIdMissmatchException;
import com.sri.sampleboot.web.exception.BookNotFoundException;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping
	public Iterable<Book> findAll(){
		return bookRepository.findAll();
	}
	
	@GetMapping("/title/{booktitle}")
	public List<Book> finByTitle(@PathVariable String title){
		return bookRepository.findByTitle(title);	
	}
	
	@GetMapping("/{id}")
	public Book findOne(@PathVariable long id){
		 Optional<Book> book =bookRepository.findById(id);
		 
		 if(book.isPresent()){
			 return book.get();
		 }
		 
		 throw new BookNotFoundException();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book create(@RequestBody Book book){
		return bookRepository.save(book);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id){
		Optional<Book> book = bookRepository.findById(id);
		if(book.isPresent()){
			bookRepository.deleteById(id);
		}
		throw new BookNotFoundException();
	}
	
	@PutMapping("{id}")
	public Book updateBook(@RequestBody Book book,@PathVariable long id){
		if(book.getId()!=id){
			throw new BookIdMissmatchException();
		}
		
		Optional<Book> oldBook = bookRepository.findById(id);
		if(oldBook.isPresent()){
			return bookRepository.save(book);
		}
		
		throw new BookNotFoundException();
	}
}
