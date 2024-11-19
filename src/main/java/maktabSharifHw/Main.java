package maktabSharifHw;

import maktabSharifHw.model.Book;
import maktabSharifHw.repository.BookRepository;
import maktabSharifHw.repository.Impl.BookRepositoryImpl;
import maktabSharifHw.util.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbc-postgres");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityManagerProvider entityManagerProvider = new EntityManagerProvider();
        BookRepository bookRepository = new BookRepositoryImpl(entityManagerProvider);
        try {
            Book book = new Book();
            book.setAuthor("Mahdi");
            book.setTitle("mowe");
            Book book2 = new Book();
            book2.setAuthor("ali");
            book2.setTitle("woowww");
            bookRepository.saveOrUpdate(book);
            bookRepository.saveOrUpdate(book2);
           List<Book> bookList= bookRepository.getAll();
           for (Book bookfound : bookList) {
               System.out.println(bookfound);
           }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
