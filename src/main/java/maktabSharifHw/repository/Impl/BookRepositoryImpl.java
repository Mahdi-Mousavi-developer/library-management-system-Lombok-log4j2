package maktabSharifHw.repository.Impl;

import maktabSharifHw.Exception.GenerallyNotFoundException;
import maktabSharifHw.model.Book;
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
            Optional<Book> foundedBook = this.findById(object.getId());
            if (!foundedBook.isPresent()) {
                throw new GenerallyNotFoundException("Book not found");
            }
            transaction.begin();
            foundedBook.get().setTitle(object.getTitle());
            foundedBook.get().setAuthor(object.getAuthor());
            foundedBook.get().setIsbn(object.getIsbn());
            foundedBook.get().setPublisher(object.getPublisher());
            foundedBook.get().setCirculation(object.getCirculation());
            foundedBook.get().setLibrarian(object.getLibrarian());
            foundedBook.get().setMember(object.getMember());
            foundedBook.get().setCreate_time(object.getCreate_time());
            foundedBook.get().setSubjects(object.getSubjects());
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
        Optional<Book> optionalBook = findById(id);
        if (!this.findById(id).isPresent()) {
            throw new GenerallyNotFoundException("Book not found");
        }
        try {
            transaction.begin();
            entityManager.remove(optionalBook);
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
