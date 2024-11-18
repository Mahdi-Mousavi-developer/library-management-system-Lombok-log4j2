package maktabSharifHw.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@ToString
public class Book extends BaseModel {
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Long Circulation;

    @ManyToMany
    @JoinTable(
            name = "j_book_subject"
            ,joinColumns ={@JoinColumn(name ="fk_subject")}
            ,inverseJoinColumns = {@JoinColumn(name = "fk_book")}
    )
    private List<Subject> subjects = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fk_librarian")
    private Librarian librarian;
    @ManyToOne
    @JoinColumn(name="fk_member")
    private Member member;
}
