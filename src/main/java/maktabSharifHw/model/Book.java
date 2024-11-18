package maktabSharifHw.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table
@ToString
public class Book extends BaseModel {
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Long Circulation;

    @ManyToOne

    private Subject subjects;

    @ManyToOne
    @JoinColumn(name = "registration_librarian")
    private Librarian librarian;
    @ManyToOne
    @JoinColumn(name="borrowing_member")
    private Memberss member;
}
