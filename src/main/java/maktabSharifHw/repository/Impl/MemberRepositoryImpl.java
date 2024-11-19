package maktabSharifHw.repository.Impl;

import maktabSharifHw.Exception.GenerallyNotFoundException;
import maktabSharifHw.model.Book;
import maktabSharifHw.model.Memberss;
import maktabSharifHw.repository.MemberRepository;
import maktabSharifHw.util.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MemberRepositoryImpl implements MemberRepository {
    EntityManagerProvider entityManagerProvider;

    public MemberRepositoryImpl(EntityManagerProvider entityManagerProvider) {
        this.entityManagerProvider = entityManagerProvider;
    }

    @Override
    public void saveOrUpdate(Memberss object) {
        if (object.getId() == null) {
            saveMemberss(object);
        } else {
            updateMemberss(object);
        }
    }

    private void saveMemberss(Memberss object) {
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

    private void updateMemberss(Memberss object) {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Optional<Memberss> foundedMemberss = this.findById(object.getId());
            if (!foundedMemberss.isPresent()) {
                throw new GenerallyNotFoundException("Member not found");
            }
            transaction.begin();
            foundedMemberss.get().setBooks(object.getBooks());
            foundedMemberss.get().setAddress(object.getAddress());
            foundedMemberss.get().setDob(object.getDob());
            foundedMemberss.get().setFirstName(object.getFirstName());
            foundedMemberss.get().setLastName(object.getLastName());
            foundedMemberss.get().setCreate_time(object.getCreate_time());
            foundedMemberss.get().setGender(object.getGender());
            foundedMemberss.get().setNationalCode(object.getNationalCode());
            foundedMemberss.get().setPassword(object.getPassword());
            foundedMemberss.get().setRole(object.getRole());
            foundedMemberss.get().setUsername(object.getUsername());
            entityManager.persist(foundedMemberss);
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
        Optional<Memberss> optionalMemberss = findById(id);
        if (!this.findById(id).isPresent()) {
            throw new GenerallyNotFoundException("Member not found");
        }
        try {
            transaction.begin();
            entityManager.remove(optionalMemberss);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Memberss> findById(Long id) {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        Optional<Memberss> optionalMemberss = Optional.ofNullable(entityManager.find(Memberss.class, id));
        return optionalMemberss;
    }


    @Override
    public List<Memberss> getAll() throws Exception {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        Query query = null;
        try {
            query = entityManager.createQuery("select c from Memberss c");
        } catch (
                Exception e) {
            throw new GenerallyNotFoundException("Member not found");
        }
        return query.getResultList();
    }
}
