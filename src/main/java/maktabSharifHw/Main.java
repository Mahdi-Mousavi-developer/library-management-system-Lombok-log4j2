package maktabSharifHw;


import maktabSharifHw.repository.BookRepository;
import maktabSharifHw.repository.Impl.BookRepositoryImpl;
import maktabSharifHw.repository.Impl.MemberRepositoryImpl;

import maktabSharifHw.repository.Impl.SubjectRepositoryImpl;
import maktabSharifHw.repository.MemberRepository;
import maktabSharifHw.repository.SubjectRepository;
import maktabSharifHw.service.BookService;
import maktabSharifHw.service.Impl.BookServiceImpl;
import maktabSharifHw.service.Impl.SubjectServiceImpl;
import maktabSharifHw.service.SubjectService;
import maktabSharifHw.util.EntityManagerProvider;
import maktabSharifHw.util.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {
    public static void main(String[] args) throws Exception {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbc-postgres");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityManagerProvider entityManagerProvider = new EntityManagerProvider();
        SubjectRepository subjectRepository = new SubjectRepositoryImpl(entityManagerProvider);
        BookRepository bookRepository = new BookRepositoryImpl(entityManagerProvider);
        MemberRepository memberRepository = new MemberRepositoryImpl(entityManagerProvider);
        SubjectService subjectService = new SubjectServiceImpl(subjectRepository);

        BookService bookService = new BookServiceImpl(bookRepository);

        Menu.mainMenu();
    }
}
