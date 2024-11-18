package maktabSharifHw.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
public class Subject extends BaseModel {
    @Column(name = "subject_title")
    private String subjectTitle;
    @Column(name = "subject_description")
    private String subjectDescription;
    @Column(name = "number_of_books")
    private Long numberOfBooks;

    @ManyToMany(mappedBy = "subjects")
    private List<Book> books = new ArrayList<>();

    public void setNumberOfBooks(List<Book> books) {
        this.numberOfBooks = (long) books.size();
    }
}
