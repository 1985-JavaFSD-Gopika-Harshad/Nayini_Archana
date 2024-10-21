package service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.UserRequest;
import dto.UserResponse;
import model.User;
import repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	
	//add user details..
	public UserResponse addUser(UserRequest userRequest) {
		User user=mapToUser(userRequest);
		user=userRepository.save(user);
		return mapToUserResponse(user);
	}
	
	//get user details by user id..
	public UserResponse getByUserId(long id) {
		Optional<User> user=userRepository.findById(id);
		if(user.isPresent()) {
			User userdata=user.get();
			return mapToUserResponse(userdata);
		}
		return null;
	}
	
	//get all user details..
	public List<UserResponse> getAllUsers(){
		List<User> users=(List<User>) userRepository.findAll();
//		List<User> userList = StreamSupport
//	            .stream(users.spliterator(), false)
//	            .collect(Collectors.toList());
		return users.stream()
				.map(this::mapToUserResponse)
				.collect(Collectors.toList());
	}
	
	//delete user by id..
	public boolean deleteByUserId(long id) {
		Optional<User> user=userRepository.findById(id);
		if(user.isPresent()) {
			userRepository.deleteById(id);
			return true;
			}
		return false;
	}
	
	//update user details
	public UserResponse updateUser(UserRequest request,long id) {
		Optional<User> user=userRepository.findById(id);
		if(user.isPresent()) {
			User details=user.get();
			details.setFirstName(request.getFirstName());
			details.setLastName(request.getLastName());
			details.setEmail(request.getEmail());
			details.setPassword(request.getPassword());
			details.setContact(request.getContact());
			details.setAddress(request.getAddress());
			details=userRepository.save(details);
			return mapToUserResponse(details);
		}
		return null;
	}
	public User mapToUser(UserRequest user) {
		return User.builder()
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.password(user.getPassword())
				.contact(user.getContact())
				.address(user.getAddress())
				.build();
	}
	
	public UserResponse mapToUserResponse(User user) {
		return UserResponse.builder()
				.id(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.password(user.getPassword())
				.contact(user.getContact())
				.address(user.getAddress())
				.build();
	}
}
