package com.peakyblinders.peakyblindersfood.domain.services;


import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityInUseException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.PermissionNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.Permission;
import com.peakyblinders.peakyblindersfood.domain.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    public Permission save(Permission permission){
        return permissionRepository.save(permission);
    }

    @Transactional
    public void remove(Long permissionId){
        try {
            permissionRepository.deleteById(permissionId);
            permissionRepository.flush();
        }catch (EmptyResultDataAccessException e){
            throw new PermissionNotFoundException(permissionId);
        }
        catch (DataIntegrityViolationException e){
            throw new EntityInUseException(String.format("Permissão de código %d não pode ser removida, pois esta em uso", permissionId));
        }
    }

    public Permission seekOrFail(Long permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new PermissionNotFoundException(permissionId));

    }
}
