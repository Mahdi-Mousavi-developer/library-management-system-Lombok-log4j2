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
@ToString
public class Subject extends BaseModel {
    @Column(name = "subject_title")
    private String subjectTitle;
    @Column(name = "subject_description")
    private String subjectDescription;
   // @Column(name = "number_of_books")
   // private Long numberOfBooks= this.getBooks();

    @OneToMany(mappedBy = "subjects")
    private List<Book> books = new ArrayList<>();

//    public Long getBooks() {
//        return (long) books.size();
//    }

}
