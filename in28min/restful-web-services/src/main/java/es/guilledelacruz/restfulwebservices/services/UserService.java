package es.guilledelacruz.restfulwebservices.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.guilledelacruz.restfulwebservices.entities.Post;
import es.guilledelacruz.restfulwebservices.entities.User;
import es.guilledelacruz.restfulwebservices.exceptions.UserNotFoundException;
import es.guilledelacruz.restfulwebservices.repositories.PostRepository;
import es.guilledelacruz.restfulwebservices.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findUserById(Integer id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException("User with id " + id + " not found!");
		
		return user.get();
	}
	
	public User createUser(String name, Date birth) {
		return userRepository.save(new User(0, name, birth));
	}
	
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}

	public List<Post> findUserPostsById(Integer id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException("User with id " + id + " not found!");
		
		return user.get().getPosts();
	}
	
	public Post createUserPost(User user, String description) {
		return postRepository.save(new Post(0, description, user));
	}
	
	public Post findPostById(Integer id) {
		Optional<Post> post = postRepository.findById(id);

		if (!post.isPresent())
			throw new UserNotFoundException("Post with id " + id + " not found!");
		
		return post.get();
	}
}
