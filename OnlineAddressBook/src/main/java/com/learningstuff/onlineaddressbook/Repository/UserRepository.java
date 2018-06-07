package com.learningstuff.onlineaddressbook.Repository;

import com.learningstuff.onlineaddressbook.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByEmail(String email);

    List<User> findUserByNameLike(String name);
}
