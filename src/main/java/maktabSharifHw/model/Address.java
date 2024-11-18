package maktabSharifHw.model;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable

public class Address {
    private String country;
    private String city;
    private String street;
    private String zipCode;

}
