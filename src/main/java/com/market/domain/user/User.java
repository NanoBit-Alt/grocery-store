package com.market.domain.user;

import java.time.Instant;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Setter
@Getter
@Table(name = "user_account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_name")
    @JsonProperty("user_name")
    private String userName;

    private String email;

    @ToString.Exclude
    private String password;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "authority")
    private int authorityLevel;

}