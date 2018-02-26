package com.kbc.oauth2Server.integration.oauth2.model.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "city")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String state;

    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }
}
