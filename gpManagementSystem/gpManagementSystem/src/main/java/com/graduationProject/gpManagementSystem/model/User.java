package com.graduationProject.gpManagementSystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
// import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.graduationProject.gpManagementSystem.enums.Role;
import com.graduationProject.gpManagementSystem.enums.Status;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED) // TPT strategy
@Table(name = "users")
@Entity

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class User implements UserDetails{
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
     private Role role ;

     @Enumerated(EnumType.STRING)
     private Status status;




     //***************** */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
 


    @ManyToMany(mappedBy = "users" , cascade = CascadeType.ALL)
    private List<ChatRoom> chatRooms;


    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> messages;




    // public User () {

    // }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }



    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

 
}
