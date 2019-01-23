package com.ssabae.study.oauth.study.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>  {
    User findByUsername(String username);
}
