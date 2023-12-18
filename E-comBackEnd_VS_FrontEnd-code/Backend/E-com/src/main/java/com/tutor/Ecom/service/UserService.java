package com.tutor.Ecom.service;

import com.tutor.Ecom.dao.RoleDao;
import com.tutor.Ecom.dao.UserDao;
import com.tutor.Ecom.entity.Role;
import com.tutor.Ecom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public User createNewUser(User user) {

        Role role = roleDao.findById("User").get();

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        return userDao.save(user);
    }

    public void initRoleAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("Super Admin");
        adminRole.setRoleDescription("Admin role...");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("User role..");
        roleDao.save(userRole);


        User adminUser = new User();
        adminUser.setUserName("hana123");
        adminUser.setFirstName("Hari");
        adminUser.setLastName("T");
        adminUser.setUserPassword(getEncodedPassword("12345"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userDao.save(adminUser);

//        User user = new User();
//        user.setUserName("hris007");
//        user.setFirstName("Haris");
//        user.setLastName("TS");
//        user.setUserPassword(getEncodedPassword("haris@pass123"));
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRoles(userRoles);
//        userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
