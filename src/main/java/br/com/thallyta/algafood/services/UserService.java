package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.core.exceptions.ValidateMessageException;
import br.com.thallyta.algafood.models.Group;
import br.com.thallyta.algafood.models.UserSystem;
import br.com.thallyta.algafood.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserSystem save(UserSystem user) {
        userRepository.detachUser(user);
        Optional<UserSystem> userFound = userRepository.findByEmail(user.getEmail());

        if(userFound.isPresent() && !userFound.get().equals(user)){
            throw new ValidateMessageException("Já existe um usuário com o email " + user.getEmail());
        }

        if (user.isNew()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.save(user);
    }

    @Transactional
    public void updatePassword(Long userId, String currentPassword, String newPassword) {
        UserSystem user = findOrFail(userId);

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new ValidateMessageException("Senha atual informada não coincide com a senha do usuário.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
    }

    public UserSystem findOrFail(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado usuário com id informado"));
    }

    @Transactional
    public void unbindGroup(Long userId, Long groupId) {
        UserSystem user = findOrFail(userId);
        Group group = groupService.findOrFail(groupId);
        user.getGroups().remove(group);
    }

    @Transactional
    public void bindGroup(Long userId, Long groupId) {
        UserSystem user = findOrFail(userId);
        Group group = groupService.findOrFail(groupId);
        user.getGroups().add(group);
    }
}
