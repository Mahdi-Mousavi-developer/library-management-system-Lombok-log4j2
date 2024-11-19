package maktabSharifHw.repository;

import maktabSharifHw.Exception.GenerallyNotFoundException;
import maktabSharifHw.model.Book;
import maktabSharifHw.model.Subject;

public interface BookRepository extends BaseRepository<Book>{
    void SetSubjectToBook(Subject subject,Long bookId) throws GenerallyNotFoundException;
}
