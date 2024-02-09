package com.peakyblinders.peakyblindersfood.api.controlles;

import com.peakyblinders.peakyblindersfood.api.assembler.PermissionModelAssembler;
import com.peakyblinders.peakyblindersfood.api.model.dto.PermissionModelDTO;
import com.peakyblinders.peakyblindersfood.domain.models.Group;
import com.peakyblinders.peakyblindersfood.domain.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/groups/{groupId}/permissions")
public class GroupPermissionController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionModelAssembler permissionModelAssembler;

    @GetMapping
    public List<PermissionModelDTO> list(@PathVariable Long groupId){
        Group group = groupService.seekOrFail(groupId);
        return permissionModelAssembler.toCollectionModel(group.getPermissions());
    }

    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long groupId, @PathVariable Long permissionId){
        groupService.associatePermission(groupId,permissionId);
    }
    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long groupId, @PathVariable Long permissionId){
        groupService.disassociatePermission(groupId,permissionId);
    }
}
