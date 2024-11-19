package com.rutuja.district.entity;


import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "userdetails")
public class UserEntity implements UserDetails {
    @Id
    @Column(value = "username")
    private String username;
    @Column(value = "password")
    private String password;
    @Column(value = "active")
    private boolean active = true;

    @Builder
    public UserEntity(String username, String password){
        this.username = username;
        this.password = password;

    }
    public UserEntity(){

    }

    public UserEntity(UserEntity userEntity) {
        this.username = userEntity.getUsername();
        this.password = "{noop}"+userEntity.getPassword();
    }



    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
