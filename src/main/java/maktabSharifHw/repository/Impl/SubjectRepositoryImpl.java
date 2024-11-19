package maktabSharifHw.repository.Impl;

import maktabSharifHw.Exception.GenerallyNotFoundException;
import maktabSharifHw.model.Subject;
import maktabSharifHw.util.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
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
            Optional<Subject> foundedSubject = this.findById(object.getId());
            if (!foundedSubject.isPresent()) {
                throw new GenerallyNotFoundException("Subject not found");
            }
            transaction.begin();
            foundedSubject.get().setSubjectTitle(object.getSubjectTitle());
            foundedSubject.get().setSubjectDescription(object.getSubjectDescription());
            foundedSubject.get().setBooks(object.getBooks());
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
        Optional<Subject> optionalSubject = this.findById(id);
        if (!this.findById(id).isPresent()) {
            throw new GenerallyNotFoundException("Subject not found");
        }
        try {
            transaction.begin();
            entityManager.remove(optionalSubject);
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
}
