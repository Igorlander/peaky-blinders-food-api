package com.peakyblinders.peakyblindersfood.api.controlles;

import com.peakyblinders.peakyblindersfood.api.assembler.UserInputDisassembler;
import com.peakyblinders.peakyblindersfood.api.assembler.UserModelAssembler;
import com.peakyblinders.peakyblindersfood.api.model.dto.UserModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.input.PasswordInput;
import com.peakyblinders.peakyblindersfood.api.model.input.UserInput;
import com.peakyblinders.peakyblindersfood.api.model.input.UserWithPasswordInput;
import com.peakyblinders.peakyblindersfood.domain.exceptions.BusinessException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.UserNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.User;
import com.peakyblinders.peakyblindersfood.domain.repositories.UserRepository;
import com.peakyblinders.peakyblindersfood.domain.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @Autowired
    private UserInputDisassembler userInputDisassembler;

    @GetMapping
    public List<UserModelDTO> lis() {
        return userModelAssembler
                .toCollectionModel(userRepository.findAll());
    }

    @GetMapping("/{userId}")
    public UserModelDTO searchById(@PathVariable Long userId) {
        return userModelAssembler.toModel(userService.seekOrFail(userId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModelDTO save(@RequestBody @Valid UserWithPasswordInput userWithPasswordInput) {
        try {
            User user = userInputDisassembler.toDomainObject(userWithPasswordInput);
            user = userService.save(user);
            return userModelAssembler.toModel(user);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public UserModelDTO update(@PathVariable Long userId,
                               @RequestBody @Valid UserInput userInput) {
        User userCurrent = userService.seekOrFail(userId);
        try {
            userInputDisassembler.copyToDomainObjtect(userInput, userCurrent);
            userCurrent = userService.save(userCurrent);
            return userModelAssembler.toModel(userCurrent);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId) {
        try {
            userService.remove(userId);
        } catch (UserNotFoundException e) {
        throw new BusinessException(e.getMessage());
    }

}
    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable Long userId,
                               @RequestBody @Valid PasswordInput password) {
        userService.updatePassword(userId , password.getPasswordCurrent()
        ,password.getNewPassword());
    }
}
