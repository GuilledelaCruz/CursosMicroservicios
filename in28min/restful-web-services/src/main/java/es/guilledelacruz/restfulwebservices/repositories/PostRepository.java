package es.guilledelacruz.restfulwebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.guilledelacruz.restfulwebservices.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{}
