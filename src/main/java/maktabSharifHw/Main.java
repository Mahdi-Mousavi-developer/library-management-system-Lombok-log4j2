package maktabSharifHw;

import maktabSharifHw.model.Book;
import maktabSharifHw.model.Gender;
import maktabSharifHw.model.Memberss;
import maktabSharifHw.model.Role;
import maktabSharifHw.repository.BookRepository;
import maktabSharifHw.repository.Impl.BookRepositoryImpl;
import maktabSharifHw.repository.Impl.MemberRepositoryImpl;
import maktabSharifHw.repository.MemberRepository;
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
        MemberRepository memberRepository = new MemberRepositoryImpl(entityManagerProvider);
        try {
            Memberss member = new Memberss();
            member.setUsername("mahdi");
            member.setPassword("mahdi");
            member.setRole(Role.MEMBER);
            memberRepository.saveOrUpdate(member);
            Role role = memberRepository.FindByUsernameAndPassword(member);
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
            System.out.println(role);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
