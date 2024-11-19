package maktabSharifHw.repository.Impl;

import maktabSharifHw.Exception.GenerallyNotFoundException;
import maktabSharifHw.model.Book;
import maktabSharifHw.model.Memberss;
import maktabSharifHw.repository.BookRepository;
import maktabSharifHw.util.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl implements BookRepository {
 EntityManagerProvider entityManagerProvider;

    public BookRepositoryImpl(EntityManagerProvider entityManagerProvider) {
        this.entityManagerProvider = entityManagerProvider;
    }

    @Override
    public void saveOrUpdate(Book object) {
        if (object.getId() == null) {
                saveBook(object);
        } else {
            updateBook(object);
        }
    }

    private void updateBook(Book object) {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Book foundedBook = entityManager.find(Book.class,object.getId());
            transaction.begin();
            foundedBook.setTitle(object.getTitle());
            foundedBook.setAuthor(object.getAuthor());
            foundedBook.setIsbn(object.getIsbn());
            foundedBook.setPublisher(object.getPublisher());
            foundedBook.setCirculation(object.getCirculation());
            foundedBook.setLibrarian(object.getLibrarian());
            foundedBook.setMember(object.getMember());
            foundedBook.setCreate_time(object.getCreate_time());
            foundedBook.setSubjects(object.getSubjects());
            entityManager.persist(foundedBook);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    private void saveBook(Book object) {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(object);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Book foundedBook = entityManager.find(Book.class,id);
        try {
            transaction.begin();
            entityManager.remove(foundedBook);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        Optional<Book> optionalBook = Optional.ofNullable(entityManager.find(Book.class, id));
        return optionalBook;
    }

    @Override
    public List<Book> getAll() throws Exception {
        return entityManagerProvider.getEntityManager().createQuery("select a from Book a").getResultList();
    }
}
