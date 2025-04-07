package maktabSharifHw.service.Impl;

import maktabSharifHw.exception.GenerallyNotFoundException;
import maktabSharifHw.model.*;
import maktabSharifHw.repository.MemberRepository;
import maktabSharifHw.service.MemberService;
import maktabSharifHw.util.EntityManagerProvider;
import maktabSharifHw.ui.Menu;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService {
    EntityManagerProvider entityManagerProvider = new EntityManagerProvider();
    MemberRepository memberRepository = new maktabSharifHw.repository.Impl.MemberRepositoryImpl(entityManagerProvider);

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Role login(String username, String password) {
        Optional<Person> personFounded = null;
        try {
            personFounded = memberRepository.FindByUsernameAndPassword(username,password);
        } catch (Exception e) {
            System.out.println("\u001B[31m" +"password or username is wrong"+ "\u001B[0m");
            Menu.logInMenu();
        }
        Role role = null;
        if (personFounded.isPresent()) {
           role = personFounded.get().getRole();
        }else{
            System.out.println(("username or pass is wrong"));

        }
        return role;
    }

    @Override
    public void printAllMember() throws GenerallyNotFoundException {
        List<Memberss> subjectList = null;
        try {
            subjectList = memberRepository.getAll();
        } catch (Exception e) {
            throw new GenerallyNotFoundException("Members not founded");
        }
        for (Memberss subject : subjectList) {
            System.out.println(subject);
        }
    }

    @Override
    public void deleteMember(long id) throws GenerallyNotFoundException {

        try {
           memberRepository.delete(id);
        } catch (Exception e) {
            throw new GenerallyNotFoundException("member not founded");
        }
    }

    @Override
    public void addMember(String firstName, String lastname, Gender gender, String national_code, String password, String username, Role role,Date dob,String country,String city ,String street , String zipcode) {
    Address address = new Address();
    address.setCity(city);
    address.setStreet(street);
    address.setZipCode(zipcode);
    address.setCountry(country);

    Memberss memberss = new Memberss();
    memberss.setFirstName(firstName);
    memberss.setLastName(lastname);
    memberss.setGender(gender);
    memberss.setNationalCode(national_code);
    memberss.setPassword(password);
    memberss.setUsername(username);
    memberss.setRole(role);
    memberss.setDob(dob);
    memberss.setAddress(address);
    memberRepository.saveOrUpdate(memberss);
    }

    @Override
    public void updateMember(long id,String firstName , String lastname , Gender gender , String national_code , String password, String username , Role role, Date dob,String country,String city ,String street , String zipcode) {
        Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setZipCode(zipcode);
        address.setCountry(country);

        Memberss memberss = new Memberss();
        memberss.setId(id);
        memberss.setFirstName(firstName);
        memberss.setLastName(lastname);
        memberss.setGender(gender);
        memberss.setNationalCode(national_code);
        memberss.setPassword(password);
        memberss.setUsername(username);
        memberss.setRole(role);
        memberss.setCreate_time(new Date());
        memberss.setDob(dob);
        memberss.setAddress(address);
        memberRepository.saveOrUpdate(memberss);
    }
}
