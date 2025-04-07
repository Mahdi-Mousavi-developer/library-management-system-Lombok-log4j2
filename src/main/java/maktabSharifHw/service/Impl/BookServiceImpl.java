package maktabSharifHw.service.Impl;

import maktabSharifHw.exception.GenerallyNotFoundException;
import maktabSharifHw.model.Book;
import maktabSharifHw.model.Subject;
import maktabSharifHw.repository.BookRepository;
import maktabSharifHw.repository.Impl.BookRepositoryImpl;
import maktabSharifHw.repository.Impl.SubjectRepositoryImpl;
import maktabSharifHw.repository.SubjectRepository;
import maktabSharifHw.service.BookService;
import maktabSharifHw.util.EntityManagerProvider;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    EntityManagerProvider entityManagerProvider = new EntityManagerProvider();
    BookRepository bookRepository = new BookRepositoryImpl(entityManagerProvider);
    SubjectRepository subjectRepository = new SubjectRepositoryImpl(entityManagerProvider);
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void printAllBook() throws GenerallyNotFoundException {
        List<Book> books = null;
        try {
            books = bookRepository.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }


    @Override
    public void deleteBook(long id) throws GenerallyNotFoundException {
        try {

            bookRepository.delete(id);
        } catch (Exception e) {
            throw new GenerallyNotFoundException("book not founded");
        }
    }

    @Override
    public void addBook(Long Circulation, String author, String isbn, String publisher, String title) throws GenerallyNotFoundException {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setTitle(title);
        book.setCirculation(Circulation);
        book.setCreate_time(new Date());
        bookRepository.saveOrUpdate(book);
    }

    @Override
    public void updateBook(long id, Long Circulation, String author, String isbn, String publisher, String title) {
        Book book = new Book();
        book.setId(id);
        book.setIsbn(isbn);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setTitle(title);
        book.setCirculation(Circulation);
        bookRepository.saveOrUpdate(book);
    }

    @Override
    public void setSubjectToBook(Long subjectId, Long bookId) {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if (subject.isPresent()) {
            try {
                bookRepository.SetSubjectToBook(subject.get(),bookId);
            } catch (GenerallyNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
