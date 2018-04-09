package com.learningstuff.librarymanagentsystem.Model.Dao;

import com.learningstuff.librarymanagentsystem.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, String> {
}
