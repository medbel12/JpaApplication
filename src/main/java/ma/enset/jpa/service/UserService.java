package ma.enset.jpa.service;

import ma.enset.jpa.entities.Role;
import ma.enset.jpa.entities.User;

public interface UserService {
    User addNewUser(User User);
    Role addNewRole(Role role);
   User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String userName, String roleName);
    User authenticate(String userName, String password);

}
