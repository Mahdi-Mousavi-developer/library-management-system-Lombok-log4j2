package maktabSharifHw.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Memberss extends Person {
    @OneToMany(mappedBy = "member")
    private List<Book> books = new ArrayList<>();

}
