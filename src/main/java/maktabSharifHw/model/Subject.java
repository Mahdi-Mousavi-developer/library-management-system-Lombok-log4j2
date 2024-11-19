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
@Table

public class Subject extends BaseModel {
    @Column(name = "subject_title")
    private String subjectTitle;
    @Column(name = "subject_description")
    private String subjectDescription;
   // @Column(name = "number_of_books")
   // private Long numberOfBooks= this.books.size();

    @OneToMany(mappedBy = "subjects")
    private List<Book> books = new ArrayList<>();

    @Override
    public String toString() {
        return "\u001B[32m"+"Subject{" +
                "subjectTitle='" + subjectTitle + '\'' +
                ", subjectDescription='" + subjectDescription + '\'' +
                ", numberOfBooks=" + books.size() +"\u001B[0m"+
                '}';
    }
//    public Long getBooks() {
//        return (long) books.size();
//    }

}
