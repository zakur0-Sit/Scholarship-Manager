package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.model.UserModel;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long>
{
    Optional<UserModel> findByLoginAndPassword(String login, String password);
}
