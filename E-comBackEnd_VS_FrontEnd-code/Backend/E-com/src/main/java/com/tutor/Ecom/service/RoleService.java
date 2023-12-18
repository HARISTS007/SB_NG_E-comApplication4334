package com.tutor.Ecom.service;

import com.tutor.Ecom.dao.RoleDao;
import com.tutor.Ecom.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}
