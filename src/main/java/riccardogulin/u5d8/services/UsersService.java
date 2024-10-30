package riccardogulin.u5d8.services;

import org.springframework.stereotype.Service;
import riccardogulin.u5d8.entities.User;
import riccardogulin.u5d8.payloads.UsersPayload;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {

	public User save(UsersPayload body) {
	}

	public List<User> findAll() {
	}

	public User findById(UUID userId) {
	}

	public User findByIdAndUpdate(UUID userId, UsersPayload body) {
	}

	public void findByIdAndDelete(UUID userId) {
	}
}
