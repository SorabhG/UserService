package com.example.UserService.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "users")
public class User {

    private static final long serialVersionUID = -1234123412341234L;


    public User(String id, Title title, String firstName, String lastName, Gender gender, String empID) {
this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.empID = empID;

    }

    public User() {

    }

    @Id
    @Column(name = "id", unique = true)
    @Pattern(regexp = "[0-9]{1,8}", message = "UserID should be Numeric only.")
    private String id;


    @Column(name = "title")
    @Enumerated(EnumType.STRING)
    private Title title;

    @Column(name = "first_name")
    @NotBlank(message = "First Name of User is required")
    @Size(min = 2, message = "FirstName should have atleast 2 characters")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last Name of User is required")
    private String lastName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "emp_id", unique = true)
    private String empID;

    @JsonManagedReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;
}
