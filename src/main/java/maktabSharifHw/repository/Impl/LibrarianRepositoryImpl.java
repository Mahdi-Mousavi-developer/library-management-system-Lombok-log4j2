package maktabSharifHw.repository.Impl;

import maktabSharifHw.Exception.GenerallyNotFoundException;
import maktabSharifHw.model.Librarian;
import maktabSharifHw.model.Memberss;
import maktabSharifHw.repository.LibrarianRepository;
import maktabSharifHw.util.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LibrarianRepositoryImpl implements LibrarianRepository {
    EntityManagerProvider entityManagerProvider;

    public LibrarianRepositoryImpl(EntityManagerProvider entityManagerProvider) {
        this.entityManagerProvider = entityManagerProvider;
    }
    @Override
    public void saveOrUpdate(Librarian object) {
        if (object.getId() == null) {
            saveLibrarian(object);
        } else {
            updateLibrarian(object);
        }
    }

    private void updateLibrarian(Librarian object) {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Optional<Librarian> foundedLibrarian = this.findById(object.getId());
            if (!foundedLibrarian.isPresent()) {
                throw new GenerallyNotFoundException("Librarian not found");
            }
            transaction.begin();
            foundedLibrarian.get().setBooks(object.getBooks());
            foundedLibrarian.get().setAddress(object.getAddress());
            foundedLibrarian.get().setDob(object.getDob());
            foundedLibrarian.get().setFirstName(object.getFirstName());
            foundedLibrarian.get().setLastName(object.getLastName());
            foundedLibrarian.get().setCreate_time(object.getCreate_time());
            foundedLibrarian.get().setGender(object.getGender());
            foundedLibrarian.get().setNationalCode(object.getNationalCode());
            foundedLibrarian.get().setPassword(object.getPassword());
            foundedLibrarian.get().setRole(object.getRole());
            foundedLibrarian.get().setUsername(object.getUsername());
            entityManager.persist(foundedLibrarian);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    private void saveLibrarian(Librarian object) {
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
        Optional<Librarian> optionalLibrarian = findById(id);
        if (!this.findById(id).isPresent()) {
            throw new GenerallyNotFoundException("Librarian not found");
        }
        try {
            transaction.begin();
            entityManager.remove(optionalLibrarian);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Librarian> findById(Long id) {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        Optional<Librarian> optionalLibrarian = Optional.ofNullable(entityManager.find(Librarian.class, id));
        return optionalLibrarian;
    }

    @Override
    public List<Librarian> getAll() throws Exception {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        Query query = null;
        try {
            query = entityManager.createQuery("select c from Librarian c");
        } catch (
                Exception e) {
            throw new GenerallyNotFoundException("Librarian not found");
        }
        return query.getResultList();
    }
}
