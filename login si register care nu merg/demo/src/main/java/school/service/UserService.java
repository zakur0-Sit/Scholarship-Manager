package school.service;

import org.springframework.stereotype.Service;
import school.model.UserModel;
import school.repository.UserRepository;

@Service
public class UserService
{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel registerUser(String login, String password, String email)
    {
        if(login != null && password != null)
        {
            UserModel userModel = new UserModel();
            userModel.setLogin(login);
            userModel.setPassword(password);
            userModel.setEmail(email);
            return userRepository.save(userModel);
        }
        else return null;
    }

    public UserModel authentication(String login, String password)
    {
        return userRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}

