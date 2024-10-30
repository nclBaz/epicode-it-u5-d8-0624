package riccardogulin.u5d8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardogulin.u5d8.entities.User;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<User, UUID> {
}
