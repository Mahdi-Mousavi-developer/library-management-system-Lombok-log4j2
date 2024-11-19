package maktabSharifHw.repository;

import maktabSharifHw.Exception.GenerallyNotFoundException;
import maktabSharifHw.model.Book;
import maktabSharifHw.model.Subject;

import java.util.List;

public interface SubjectRepository extends BaseRepository<Subject>{
    List<Subject>  ShowSubjectsWhoHaveOneBook();
  void deleteBookBySubjectId(long id) throws GenerallyNotFoundException;
}
