package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.core.exceptions.ValidateMessageException;
import br.com.thallyta.algafood.models.User;
import br.com.thallyta.algafood.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user) {
        userRepository.detachUser(user);
        Optional<User> userFound = userRepository.findByEmail(user.getEmail());

        if(userFound.isPresent() && !userFound.get().equals(user)){
            throw new ValidateMessageException("Já existe um usuário com o email " + user.getEmail());
        }
        return userRepository.save(user);
    }

    @Transactional
    public void updatePassword(Long userId, String currentPassword, String newPassword) {
        User user = findOrFail(userId);

        if (user.passwordDoesNotMatchWith(currentPassword)) {
            throw new ValidateMessageException("Senha atual informada não coincide com a senha do usuário.");
        }

        user.setPassword(newPassword);
    }

    public User findOrFail(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado usuário com id informado"));
    }
}
