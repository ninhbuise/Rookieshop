package com.enashtech.rookieserver.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    @Size(min = 3, max = 50)
    private String city;
    @NotBlank
    @Size(min = 3, max = 50)
    private String district;
    @NotBlank
    @Size(min = 3, max = 50)
    private String ward;
    @NotBlank
    @Size(min = 3, max = 50)
    private String street;

    public Address() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.city);
        hash = 67 * hash + Objects.hashCode(this.district);
        hash = 67 * hash + Objects.hashCode(this.ward);
        hash = 67 * hash + Objects.hashCode(this.street);
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
        final Address other = (Address) obj;
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.district, other.district)) {
            return false;
        }
        if (!Objects.equals(this.ward, other.ward)) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        return true;
    }

}
