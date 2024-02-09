package com.peakyblinders.peakyblindersfood.domain.repositories;

import com.peakyblinders.peakyblindersfood.domain.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {

}
