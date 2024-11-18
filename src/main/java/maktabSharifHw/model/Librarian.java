package maktabSharifHw.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;


@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class Librarian extends Person {
}
