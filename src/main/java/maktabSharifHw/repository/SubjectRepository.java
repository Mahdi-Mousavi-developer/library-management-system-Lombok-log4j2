package maktabSharifHw.repository;

import maktabSharifHw.model.Book;
import maktabSharifHw.model.Subject;

import java.util.List;

public interface SubjectRepository extends BaseRepository<Subject>{
    List<Subject>  ShowSubjectsWhoHaveOneBook();

}
