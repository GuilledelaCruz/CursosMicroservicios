package es.guilledelacruz.restfulwebservices.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import es.guilledelacruz.restfulwebservices.entities.Post;
import es.guilledelacruz.restfulwebservices.entities.User;
import es.guilledelacruz.restfulwebservices.services.UserService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
	
	@Autowired
	UserService service;

	@GetMapping("/users")
	public List<User> getUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public MappingJacksonValue getUserById(@PathVariable Integer id) {
		User u = service.findUserById(id);
		
		EntityModel<User> resource = EntityModel.of(u);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("name", "birthDate");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(resource);
		mapping.setFilters(filters);
		
		return mapping;
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User nUser = service.createUser(user.getName(), user.getBirthDate());
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(nUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable Integer id) {
		service.deleteUserById(id);
	}

	@GetMapping("/users/{id}/posts")
	public List<Post> getUserPostsByUserId(@PathVariable Integer id) {
		return service.findUserPostsById(id);
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Post> createPost(@Valid @RequestBody Post post, @PathVariable Integer id) {
		User u = service.findUserById(id);
		Post p = service.createUserPost(u, post.getDescription());

		URI location = ServletUriComponentsBuilder
				.fromPath("/posts/{id}")
				.buildAndExpand(p.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/posts/{id}")
	public Post getPostById(@PathVariable Integer id) {
		return service.findPostById(id);
	}
}
