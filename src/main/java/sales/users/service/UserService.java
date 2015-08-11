package sales.users.service;

import sales.roles.domain.Role;
import sales.users.domain.User;

import java.util.Date;
import java.util.List;

/**
 * Created by taras on 29.07.15.
 */
public interface UserService {

    User getById(Long id);

    User getByEmail(String email);

    User getShop(Long id);

    User getClient(Long id);

    User addUser(User user);

    void deleteUser(Long id);

    List<User> findBy(String roleValue ,String searchField, String value, int page, int amount, String sortField);

    List<User> findByRole(String roleValue, int page, int amount, String sortField);

    List<User> findByCreationDateAfter(String creationDate);

    void changeUserLock(Long id);
    
    List<User> findByCreationDateAfterAndRole(Date creationDate, Role role);

    List<User> findByCreationDateAfter(Date creationDate);

    List<User> findByRole(Role role);
}
