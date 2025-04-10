package maktabSharifHw.repository;

import maktabSharifHw.model.Memberss;
import maktabSharifHw.model.Person;

import java.sql.SQLException;
import java.util.Optional;

public interface MemberRepository extends BaseRepository<Memberss> {
    Optional<Person> FindByUsernameAndPassword (String username , String password)throws Exception;
}
