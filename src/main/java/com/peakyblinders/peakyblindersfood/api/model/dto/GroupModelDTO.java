package com.peakyblinders.peakyblindersfood.api.model.dto;


import com.peakyblinders.peakyblindersfood.domain.models.Permission;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GroupModelDTO {
    private Long id;
    private String name;
}
