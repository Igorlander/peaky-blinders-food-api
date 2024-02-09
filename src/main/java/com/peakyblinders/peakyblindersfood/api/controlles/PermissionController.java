package com.peakyblinders.peakyblindersfood.api.controlles;


import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityInUseException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.Permission;
import com.peakyblinders.peakyblindersfood.domain.repositories.PermissionRepository;
import com.peakyblinders.peakyblindersfood.domain.services.PermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("permissions")
public class PermissionController {

    @Autowired
    private PermissionRepository permissionRepository;


    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public List<Permission> list (){return permissionRepository.findAll();}

    @GetMapping("/{permissionId}")
    public ResponseEntity<Permission> searchById(@PathVariable Long permissionId){
        Optional<Permission> permission = permissionRepository.findById(permissionId);
        if (permission.isPresent()){
            return ResponseEntity.ok(permission.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Permission permission) {
        Permission permissionOk = permissionService.save(permission);
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionOk);
    }

    @PutMapping("/{permissionId}")
    public ResponseEntity<Permission> update(@PathVariable Long permissionId,
                                                @RequestBody Permission permission){

        Permission permissionCurrent = permissionRepository.findById(permissionId).orElse(null);
        if (permissionCurrent != null){
            BeanUtils.copyProperties(permission ,permissionCurrent, "id");
            permissionCurrent = permissionService.save(permissionCurrent);
            return ResponseEntity.ok(permissionCurrent);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<?> remove(@PathVariable Long permissionId){
        try{
            permissionService.remove(permissionId);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
}
