package maktabSharifHw.service.Impl;

import maktabSharifHw.model.Person;
import maktabSharifHw.model.Role;
import maktabSharifHw.repository.MemberRepository;
import maktabSharifHw.service.MemberService;
import maktabSharifHw.util.EntityManagerProvider;
import maktabSharifHw.util.Menu;

import java.util.Optional;

public class MemberServiceImpl implements MemberService {
    EntityManagerProvider entityManagerProvider = new EntityManagerProvider();
    MemberRepository memberRepository = new maktabSharifHw.repository.Impl.MemberRepositoryImpl(entityManagerProvider);

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Role login(String username, String password) {
        Optional<Person> personFounded = memberRepository.FindByUsernameAndPassword(username,password);
        Role role = null;
        if (personFounded.isPresent()) {
           role = personFounded.get().getRole();
        }else{
            System.out.println(("username or pass is wrong"));
            Menu.logInMenu();
        }
        return role;
    }
}
