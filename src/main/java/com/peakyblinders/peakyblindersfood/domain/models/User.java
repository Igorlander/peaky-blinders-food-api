package com.peakyblinders.peakyblindersfood.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_USER")
public class User {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "registration_date", nullable = false)
    private OffsetDateTime registrationDate;


    @ManyToMany
    @JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groupUsers = new HashSet<>();

    public boolean passwordMatches(String password) {
        return getPassword().equals(password);
    }

    public boolean passwordDoesNotMatch(String password) {
        return !passwordMatches(password);
    }

    public boolean removeGroup(Group group){
        return getGroupUsers().remove(group);
    }
    public boolean addGroup(Group group){
        return getGroupUsers().add(group);
    }
}
