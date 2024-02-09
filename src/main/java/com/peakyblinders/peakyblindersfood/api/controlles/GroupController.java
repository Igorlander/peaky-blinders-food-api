package com.peakyblinders.peakyblindersfood.api.controlles;


import com.peakyblinders.peakyblindersfood.api.assembler.GroupInputDisassembler;
import com.peakyblinders.peakyblindersfood.api.assembler.GroupModelAssembler;
import com.peakyblinders.peakyblindersfood.api.model.dto.GroupModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.input.GroupInput;
import com.peakyblinders.peakyblindersfood.domain.exceptions.BusinessException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.Group;
import com.peakyblinders.peakyblindersfood.domain.repositories.GroupRepository;
import com.peakyblinders.peakyblindersfood.domain.services.GroupService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;


    @Autowired
    private GroupModelAssembler groupModelAssembler;

    @Autowired
    private GroupInputDisassembler groupInputDisassembler;

    @GetMapping
    public List<GroupModelDTO> list() {
        List<Group> groupsTotal = groupRepository.findAll();
        return groupModelAssembler.toCollectionModel(groupsTotal);
    }

    @GetMapping("/{groupId}")
    public GroupModelDTO searchById(@PathVariable Long groupId) {
        Group group = groupService.seekOrFail(groupId);
        return groupModelAssembler.toModel(group);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupModelDTO save(@RequestBody @Valid GroupInput groupInput) {
        try {
            Group group = groupInputDisassembler.toDomainObject(groupInput);
            groupRepository.save(group);
            return groupModelAssembler.toModel(group);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{groupId}")
    public GroupModelDTO update(@PathVariable  Long groupId
            , @RequestBody @Valid GroupInput groupInput) {
        Group groupCurrent = groupService.seekOrFail(groupId);
        try {

            groupInputDisassembler.copyToDomainObjtect(groupInput, groupCurrent);
            return groupModelAssembler.toModel(groupService.save(groupCurrent));
        } catch (EntityNotFoundException e) {
           throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long groupId) {
        try {
            groupService.remove(groupId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

}


