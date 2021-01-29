package es.guilledelacruz.restfulwebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.guilledelacruz.restfulwebservices.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{}
