package maktabSharifHw.repository.Impl;

import maktabSharifHw.exception.GenerallyNotFoundException;
import maktabSharifHw.model.Book;
import maktabSharifHw.model.Subject;
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
            Book foundedBook = entityManager.find(Book.class, object.getId());
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
        Book foundedBook = entityManager.find(Book.class, id);
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
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        Query query = null;
        try {
            query = entityManager.createQuery("select c from Book c");
        } catch (Exception e) {
            throw new GenerallyNotFoundException("Book not found");
        }
        return query.getResultList();
    }

    @Override
    public void SetSubjectToBook(Subject subject, Long bookId) throws GenerallyNotFoundException {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Query query = null;
        try {
            transaction.begin();
            query = entityManager.createQuery("update Book b set b.subjects=?1 where id = ?2 ");
            query.setParameter(1, subject);
            query.setParameter(2, bookId);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            throw new GenerallyNotFoundException("Book not found");
        }
    }
}
