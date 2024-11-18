package maktabSharifHw.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class Librarian extends Person {
    @OneToMany(mappedBy = "librarian")
    private List<Book> books = new ArrayList<>();
}
