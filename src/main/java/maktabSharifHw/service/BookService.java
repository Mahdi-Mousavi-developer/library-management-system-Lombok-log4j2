package maktabSharifHw.service;

import maktabSharifHw.Exception.GenerallyNotFoundException;

public interface BookService {
    void printAllBook() throws GenerallyNotFoundException;

    void setSubjectToBook(Long subjectId, Long bookId);

    void deleteBook(long id) throws GenerallyNotFoundException;

    void addBook(Long Circulation, String author, String isbn, String publisher, String title) throws GenerallyNotFoundException;

    void updateBook(long id, Long Circulation, String author, String isbn, String publisher, String title);
}
