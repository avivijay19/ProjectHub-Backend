package com.ProjectHub.model;

import com.ProjectHub.entities.StudentProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Avinash Vijayvargiya on 30-09-2021.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 275347623L;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(StudentProfile studentProfile) {
        this.username = studentProfile.getUsername();
        this.password = studentProfile.getPassword();
        this.active = studentProfile.isActive();
        this.authorities = Arrays.stream(studentProfile.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}