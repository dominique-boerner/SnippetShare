package com.snippetshare.api.repository;

import com.snippetshare.api.entity.User;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends Repository<User, Long> {

    Optional<User> searchFirstByEmailAndPassword(String email, String password);
}
