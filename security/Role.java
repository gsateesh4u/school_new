package com.amex.sms.school.security;

import lombok.Data;

import javax.persistence.*;

/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 09 Nov, 2023
 */
@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }
}
