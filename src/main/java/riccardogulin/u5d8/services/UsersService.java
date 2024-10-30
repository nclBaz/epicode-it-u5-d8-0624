package riccardogulin.u5d8.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardogulin.u5d8.entities.User;
import riccardogulin.u5d8.exceptions.BadRequestException;
import riccardogulin.u5d8.exceptions.NotFoundException;
import riccardogulin.u5d8.payloads.UsersPayload;
import riccardogulin.u5d8.repositories.UsersRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	public User save(UsersPayload body) {
		// 1. Verifico che l'email non sia già in uso
		this.usersRepository.findByEmail(body.getEmail()).ifPresent(
				// 1.1 Se trovo uno user con quell'indirizzo triggera un errore
				user -> {
					throw new BadRequestException("Email " + body.getEmail() + " già in uso!");
				}
		);

		// 2. Se è ok allora aggiungo i campi "server-generated" come ad esempio avatarURL
		User newUser = new User(body.getName(), body.getSurname(), body.getEmail(), body.getPassword(),
				"https://ui-avatars.com/api/?name=" + body.getName() + "+" + body.getSurname());

		// 3. Salvo il nuovo utente
		return this.usersRepository.save(newUser);
	}

	public List<User> findAll() {
		return this.usersRepository.findAll();
	}

	public User findById(UUID userId) {
		return this.usersRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
	}

	public User findByIdAndUpdate(UUID userId, UsersPayload body) {
		// 1. Cerco l'utente nel db
		User found = this.findById(userId);

		// 2. Controllo se l'email nuova è già in uso
		if (!found.getEmail().equals(body.getEmail())) {
			this.usersRepository.findByEmail(body.getEmail()).ifPresent(
					// 1.1 Se trovo uno user con quell'indirizzo triggera un errore
					user -> {
						throw new BadRequestException("Email " + body.getEmail() + " già in uso!");
					}
			);
		}

		// 3. Modifico l'utente trovato nel db
		found.setName(body.getName());
		found.setSurname(body.getSurname());
		found.setEmail(body.getEmail());
		found.setPassword(body.getPassword());
		found.setAvatarURL("https://ui-avatars.com/api/?name=" + body.getName() + "+" + body.getSurname());

		// 4. Risalvo l'utente
		return this.usersRepository.save(found);
	}

	public void findByIdAndDelete(UUID userId) {
		User found = this.findById(userId);
		this.usersRepository.delete(found);
	}
}
