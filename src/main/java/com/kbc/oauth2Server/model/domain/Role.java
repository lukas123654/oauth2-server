package com.kbc.oauth2Server.model.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(nullable = false)
    private String description;

    public Role(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }
}
