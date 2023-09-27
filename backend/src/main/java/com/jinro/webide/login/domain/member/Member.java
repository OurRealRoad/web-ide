package com.jinro.webide.login.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Setter
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "member_id")
    private long id;
    @Column(name = "member_email")
    private String email;
    @Column(name = "member_name")
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "member_password")
    private String password;
    private String picture;
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    @Column(name = "regdate")
    private String createdDate;
    @Column(name = "chgdate")
    private String updatedDate;

    public Member () {}

}
