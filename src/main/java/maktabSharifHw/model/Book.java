package maktabSharifHw.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
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

    @Override
    public String toString() {
        return "Book{" +"\u001B[34m"+
                "id= "+getId().toString()+
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisher='" + publisher + '\'' +
                ", Circulation=" + Circulation +
                ", subjects=" + subjects +"\u001B[34m"+
                ", librarian=" + librarian +
                ", member=" + member+"\u001B[0m"+
                '}';
    }
}
