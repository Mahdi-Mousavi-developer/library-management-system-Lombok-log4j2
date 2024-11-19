package maktabSharifHw.repository.Impl;

import maktabSharifHw.Exception.GenerallyNotFoundException;
import maktabSharifHw.model.Book;
import maktabSharifHw.model.Person;
import maktabSharifHw.model.Subject;
import maktabSharifHw.util.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class SubjectRepositoryImpl implements maktabSharifHw.repository.SubjectRepository {
    EntityManagerProvider entityManagerProvider;

    public SubjectRepositoryImpl(EntityManagerProvider entityManagerProvider) {
        this.entityManagerProvider = entityManagerProvider;
    }
    @Override
    public void saveOrUpdate(Subject object) {
        if (object.getId() == null) {
            saveSubject(object);
        } else {
            updateSubject(object);
        }
    }

    private void updateSubject(Subject object) {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Subject foundedSubject = entityManager.find(Subject.class,object.getId());
            transaction.begin();
            foundedSubject.setSubjectTitle(object.getSubjectTitle());
            foundedSubject.setSubjectDescription(object.getSubjectDescription());
            foundedSubject.setBooks(object.getBooks());
            entityManager.persist(foundedSubject);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    private void saveSubject(Subject object) {
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
        Subject subject = entityManager.find(Subject.class,id);
        if (!this.findById(id).isPresent()) {
            throw new GenerallyNotFoundException("Subject not found");
        }
        try {
            transaction.begin();
            entityManager.remove(subject);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Subject> findById(Long id) {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        Optional<Subject> optionalSubject = Optional.ofNullable(entityManager.find(Subject.class, id));
        return optionalSubject;
    }

    @Override
    public List<Subject> getAll() throws Exception {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        Query query = null;
        try {
            query = entityManager.createQuery("select c from Subject c");
        } catch (Exception e) {
            throw new GenerallyNotFoundException("Subject not found");
        }
        return query.getResultList();
    }

    @Override
    public List<Subject> ShowSubjectsWhoHaveOneBook() {
        Query query = entityManagerProvider.getEntityManager().createQuery("select c from Subject c where books.size >=1");
         List<Subject> subjects = query.getResultList();
        return subjects;
    }

    @Override
    public void deleteBookBySubjectId(long id) throws GenerallyNotFoundException {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        Query query = null;
        EntityTransaction transaction = entityManager.getTransaction();
        Subject subject = entityManager.find(Subject.class,id);
        try {
            transaction.begin();
            query = entityManager.createQuery("delete from Book b where b.subjects=?1");
            query.setParameter(1,subject);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            throw new GenerallyNotFoundException("Subject not found");
        }

    }
}
