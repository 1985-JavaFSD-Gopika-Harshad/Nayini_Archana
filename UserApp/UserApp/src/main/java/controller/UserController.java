package controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.UserRequest;
import dto.UserResponse;
import service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	} 
	@PostMapping
	public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest){
		UserResponse user=userService.addUser(userRequest);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<UserResponse> getUserById(@RequestParam long id){
		UserResponse user=userService.getByUserId(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserResponse> >getAllUsers(){
		List<UserResponse> user=userService.getAllUsers();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deleteByUserId(@RequestParam long id){
		boolean status=userService.deleteByUserId(id);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest request,@RequestParam long id){
		UserResponse user=userService.updateUser(request,id);
		return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
	}
}
