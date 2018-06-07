package com.learningstuff.onlineaddressbook.Service;

import com.learningstuff.onlineaddressbook.Model.Role;
import com.learningstuff.onlineaddressbook.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRole()
    {
        return roleRepository.findAll();
    }

    public Role findByRole(String role)
    {
         return roleRepository.findByRole(role);
    }

    public void createRole(Role role)
    {
        roleRepository.save(role);
    }
}
