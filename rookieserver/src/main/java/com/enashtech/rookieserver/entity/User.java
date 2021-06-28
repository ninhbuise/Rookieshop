package com.enashtech.rookieserver.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    private int id;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String fist_name;
    private String last_name;
    private String email;
    private String phone;
    private String user_name;
    private String pass_word;
    private Date birth_day;    
    private UserRole userRole;    
    private String url_avatar;

    public User(String fist_name, String last_name, String email, String phone, String user_name, String pass_word, Date birth_day, UserRole userRole, String url_avatar) {
        this.fist_name = fist_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.user_name = user_name;
        this.pass_word = pass_word;
        this.birth_day = birth_day;
        this.userRole = userRole;
        this.url_avatar = url_avatar;
    }

    public User() {
    } 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFist_name() {
        return fist_name;
    }

    public void setFist_name(String fist_name) {
        this.fist_name = fist_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public Date getBirth_day() {
        return birth_day;
    }
    
    public void setBirth_day(Date birth_day) {
        this.birth_day = birth_day;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getUrl_avatar() {
        return url_avatar;
    }

    public void setUrl_avatar(String url_avatar) {
        this.url_avatar = url_avatar;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.user_name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.user_name, other.user_name)) {
            return false;
        }
        return true;
    }
}
