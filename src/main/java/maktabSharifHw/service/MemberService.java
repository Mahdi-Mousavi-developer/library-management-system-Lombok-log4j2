package maktabSharifHw.service;

import maktabSharifHw.exception.GenerallyNotFoundException;
import maktabSharifHw.model.Gender;
import maktabSharifHw.model.Role;

import java.util.Date;

public interface MemberService {
    Role login(String username, String password);
    void printAllMember() throws GenerallyNotFoundException;
    void deleteMember(long id) throws GenerallyNotFoundException;
    void addMember(String firstName , String lastname , Gender gender , String national_code , String password, String username , Role role, Date dob,String country,String city ,String street , String zipcode);
    void updateMember(long id,String firstName , String lastname , Gender gender , String national_code , String password, String username , Role role, Date dob,String country,String city ,String street , String zipcode);
}
