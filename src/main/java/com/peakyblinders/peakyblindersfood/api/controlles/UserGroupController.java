package com.peakyblinders.peakyblindersfood.api.controlles;

import com.peakyblinders.peakyblindersfood.api.assembler.GroupModelAssembler;
import com.peakyblinders.peakyblindersfood.api.model.dto.GroupModelDTO;
import com.peakyblinders.peakyblindersfood.domain.models.User;
import com.peakyblinders.peakyblindersfood.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users/{userId}/groups")
public class UserGroupController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupModelAssembler groupModelAssembler;


    @GetMapping
    public List<GroupModelDTO> list(@PathVariable Long userId){
        User user = userService.seekOrFail(userId);
        return groupModelAssembler.toCollectionModel(user.getGroupUsers());
    }

    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long userId, @PathVariable Long groupId){
        userService.associateGroup(userId, groupId);
    }
    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long userId, @PathVariable Long groupId){
        userService.disassociateGroup(userId,groupId);
    }
}
