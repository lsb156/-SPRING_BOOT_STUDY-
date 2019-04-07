package com.example.demo.jpa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserEntity {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private Integer age;
    private Date createAt;

    @Column(name="role")
    private UserRole role;

    @PrePersist
    public void beforeCreate() {
        createAt = new Date();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
