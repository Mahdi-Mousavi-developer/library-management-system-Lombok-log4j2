package maktabSharifHw.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "library_Person")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public abstract class  Person extends BaseModel {

    private String username;
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "national_code", unique = true)
    private String nationalCode;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private Role role;

}
