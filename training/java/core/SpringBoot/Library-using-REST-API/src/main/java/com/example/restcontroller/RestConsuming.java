package com.example.restcontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.model.Books;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/rest")
public class RestConsuming {
	@Autowired
	RestTemplate rest;
	@GetMapping("/showBooks")
	public ArrayList<Books> showdata(){
		HttpHeaders h=new HttpHeaders();
		h.add("invocationForm", "RestTemplate");
		HttpEntity<Books> he=new HttpEntity<Books>(h);
		try {
			ResponseEntity<ArrayList> res=rest.exchange("http://localhost:8000/books/show",HttpMethod.GET,he,ArrayList.class);
			if(res!=null) {
				log.info("executed");
			}
			else {
				log.warn("There is no book");
			}
			return res.getBody();
		}
		catch(Exception e) {
			log.error("Error occured");
		}
		return null;
	}
	@Autowired
	WebClient web;
	@PostMapping("/addBooks")
	public Mono<String> addBooks(@RequestBody Books b) {
		return web.post().uri("http://localhost:8000/books/add")
				.header("invocationform", "WebClient")
				.body(Mono.just(b),Books.class)
				.retrieve()
				.bodyToMono(String.class);
	}
//	@PostMapping("/addBooks")
//	public Mono<String> addBook(@RequestBody Books b) {
//		return web.post().uri("http://10.129.242.209:8080/book/books")
//				.header("invocationform", "WebClient")
//				.body(Mono.just(b),Books.class)
//				.retrieve()
//				.bodyToMono(String.class);
//	}
//	@GetMapping("/showBooks")
//	public Flux<Books> showBooks() {
//		return web.post().uri("http://10.129.242.209:8080/book/books")
//				.header("invocationform", "WebClient")
//				.retrieve()
//				.bodyToFlux(Books.class);
//	}
}
