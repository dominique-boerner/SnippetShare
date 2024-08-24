package com.snippetshare.api.repository;

import com.snippetshare.api.entity.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    Optional<User> searchFirstByEmailAndPassword(String email, String password);
}
