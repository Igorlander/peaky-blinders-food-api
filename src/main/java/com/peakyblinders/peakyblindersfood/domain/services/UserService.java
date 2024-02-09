package com.peakyblinders.peakyblindersfood.domain.services;

import com.peakyblinders.peakyblindersfood.domain.exceptions.BusinessException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityInUseException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.UserNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.Group;
import com.peakyblinders.peakyblindersfood.domain.models.User;
import com.peakyblinders.peakyblindersfood.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    public static final String
            USER_OF_CODE_CAN_NOT_BE_REMOVED_BECAUSE_IT_IS_IN_USE =
            "Usuário de código %d não pode ser removida, pois esta em uso";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupService groupService;

    @Transactional
    public User save(User user) {
        userRepository.detach(user);
        Optional<User> userExisting = userRepository.findByEmail(user.getEmail());
        if (userExisting.isPresent() && !userExisting.get().equals(user) ) {
            throw new BusinessException(
                    String.format("Já existe um usuário cadastrado com o e-mail %s .", user.getEmail()));
        }
        return userRepository.save(user);
    }

    @Transactional
    public void remove(Long userId) {
        try {
            User user = seekOrFail(userId);
            userRepository.deleteById(userId);
            userRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(userId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(
                    USER_OF_CODE_CAN_NOT_BE_REMOVED_BECAUSE_IT_IS_IN_USE,
                    userId));
        }
    }

    @Transactional
    public void updatePassword(Long userId, String passwordCurrent,
                               String newPassword) {
        User user = seekOrFail(userId);

        if (user.passwordDoesNotMatch(passwordCurrent)) {
            throw new BusinessException("Senha atual informada não " +
                    "coincide com a senha do usuário.");
        }
        user.setPassword(newPassword);
    }


    public User seekOrFail(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

    }
    @Transactional
    public void disassociateGroup(Long groupId, Long userId){
        Group group = groupService.seekOrFail(groupId);
        User user = seekOrFail(userId);
        user.removeGroup(group);

    }
    @Transactional
    public void associateGroup(Long groupId, Long userId){
        Group group = groupService.seekOrFail(groupId);
        User user = seekOrFail(userId);
        user.addGroup(group);
    }
}
