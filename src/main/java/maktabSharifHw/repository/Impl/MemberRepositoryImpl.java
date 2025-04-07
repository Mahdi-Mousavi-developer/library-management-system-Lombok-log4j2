package maktabSharifHw.repository.Impl;

import maktabSharifHw.exception.GenerallyNotFoundException;
import maktabSharifHw.model.Memberss;
import maktabSharifHw.model.Person;
import maktabSharifHw.repository.MemberRepository;
import maktabSharifHw.util.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class MemberRepositoryImpl implements MemberRepository {
    EntityManagerProvider entityManagerProvider;

    public MemberRepositoryImpl(EntityManagerProvider entityManagerProvider) {
        this.entityManagerProvider = entityManagerProvider;
    }

    @Override
    public Optional<Person> FindByUsernameAndPassword(String username , String password)throws Exception {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        TypedQuery<Person> personFounded = entityManager.createNamedQuery("Person.findByUsernamePassword", Person.class);
        personFounded.setParameter(1, username);
        personFounded.setParameter(2, password);
        return Optional.ofNullable(personFounded.getSingleResult());
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
            Memberss foundedMemberss = entityManager.find(Memberss.class,object.getId());
            transaction.begin();
            foundedMemberss.setBooks(object.getBooks());
            foundedMemberss.setAddress(object.getAddress());
            foundedMemberss.setDob(object.getDob());
            foundedMemberss.setFirstName(object.getFirstName());
            foundedMemberss.setLastName(object.getLastName());
            foundedMemberss.setCreate_time(object.getCreate_time());
            foundedMemberss.setGender(object.getGender());
            foundedMemberss.setNationalCode(object.getNationalCode());
            foundedMemberss.setPassword(object.getPassword());
            foundedMemberss.setRole(object.getRole());
            foundedMemberss.setUsername(object.getUsername());
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
        Memberss foundedMemberss = entityManager.find(Memberss.class,id);
        try {
            transaction.begin();
            entityManager.remove(foundedMemberss);
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
