package com.peakyblinders.peakyblindersfood.domain.services;


import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityInUseException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.GroupNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.Group;
import com.peakyblinders.peakyblindersfood.domain.models.Permission;
import com.peakyblinders.peakyblindersfood.domain.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupService {

    public static final String GROUP_OF_NOT_BE_REMOTVED_BECAUSE_IT_IS_IN_USE
            = "Group de código %d não pode ser removido, pois esta em uso";

   @Autowired
   private GroupRepository groupRepository;

   @Autowired
   private PermissionService permissionService;

   @Transactional
   public Group save(Group group){
        return groupRepository.save(group);
    }

    @Transactional
    public void remove(Long groupId){
      try {
          groupRepository.deleteById(groupId);
          groupRepository.flush();
      }catch (EmptyResultDataAccessException e ) {
          throw new GroupNotFoundException(groupId);
      }
      catch (DataIntegrityViolationException e){
          throw new EntityInUseException(String.format(GROUP_OF_NOT_BE_REMOTVED_BECAUSE_IT_IS_IN_USE,
                  groupId));
      }
    }

    @Transactional
    public void disassociatePermission(Long groupId, Long permissionId){
        Group group = seekOrFail(groupId);
        Permission permission = permissionService.seekOrFail(permissionId);
        group.removePermission(permission);
    }
    @Transactional
    public  void associatePermission(Long groupId, Long permissionId){
       Group group = seekOrFail(groupId);
       Permission permission = permissionService.seekOrFail(permissionId);
       group.addPermission(permission);
    }

    public Group seekOrFail(Long groupId){
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
    }


}
