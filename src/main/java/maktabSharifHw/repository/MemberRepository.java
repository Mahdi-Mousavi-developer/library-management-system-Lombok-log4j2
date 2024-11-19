package maktabSharifHw.repository;

import maktabSharifHw.model.Book;
import maktabSharifHw.model.Memberss;
import maktabSharifHw.model.Person;
import maktabSharifHw.model.Role;

public interface MemberRepository extends BaseRepository<Memberss> {
    Role FindByUsernameAndPassword (Person person);
}
