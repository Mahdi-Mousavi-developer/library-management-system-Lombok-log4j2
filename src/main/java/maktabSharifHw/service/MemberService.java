package maktabSharifHw.service;

import maktabSharifHw.model.Role;

public interface MemberService {
    Role login(String username, String password);
}
