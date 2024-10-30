package riccardogulin.u5d8.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardogulin.u5d8.entities.User;
import riccardogulin.u5d8.payloads.UsersPayload;
import riccardogulin.u5d8.services.UsersService;

import java.util.List;
import java.util.UUID;

/*

1. GET http://localhost:3001/users
2. POST http://localhost:3001/users (+ req.body) --> 201
3. GET http://localhost:3001/users/{userId}
4. PUT http://localhost:3001/users/{userId} (+ req.body)
5. DELETE http://localhost:3001/users/{userId} --> 204

*/


@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private UsersService usersService;

	// 1. GET http://localhost:3001/users
	@GetMapping
	public List<User> findAll() {
		return this.usersService.findAll();
	}

	// 2. POST http://localhost:3001/users (+ req.body) --> 201
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User save(@RequestBody UsersPayload body) {
		return this.usersService.save(body);
	}

	// 3. GET http://localhost:3001/users/{userId}
	@GetMapping("/{userId}")
	public User findById(@PathVariable UUID userId) {
		return this.usersService.findById(userId);
	}

	// 4. PUT http://localhost:3001/users/{userId} (+ req.body)
	@PutMapping("/{userId}")
	public User findByIdAndUpdate(@PathVariable UUID userId, @RequestBody UsersPayload body) {
		return this.usersService.findByIdAndUpdate(userId, body);
	}

	// 5. DELETE http://localhost:3001/users/{userId} --> 204
	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void findByIdAndDelete(@PathVariable UUID userId) {
		this.usersService.findByIdAndDelete(userId);
	}
}
