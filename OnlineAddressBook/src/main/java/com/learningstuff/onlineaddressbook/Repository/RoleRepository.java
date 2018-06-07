package com.learningstuff.onlineaddressbook.Repository;

import com.learningstuff.onlineaddressbook.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer>{

    Role findByRole(String role);
}
