package com.snippetshare.api.repository;

import com.snippetshare.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> searchFirstByEmailAndPassword(String email, String password);

}
