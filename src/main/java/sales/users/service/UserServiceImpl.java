package sales.users.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sales.roles.domain.Role;
import sales.roles.service.RoleServiceImpl;
import sales.security.StringToMd5;
import sales.users.domain.User;
import sales.users.repository.UserRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by taras on 29.07.15.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleServiceImpl roleService;

    @Override
    public User getById(Long id) {
        logger.debug("Get User by id");
        return userRepository.getOne(id);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getShop(Long id) {
        logger.debug("Get Shop by id");
        return userRepository.findByRoleAndId(roleService.getRoleByValue("shop"), id);
    }

    @Override
    public User getClient(Long id) {
        logger.debug("Get Client by id");
        return userRepository.findByRoleAndId(roleService.getRoleByValue("client"), id);
    }

    @Override
    public User addUser(User user) {
        //hashing password
        try {
            user.setPassword(StringToMd5.getMd5(user.getPassword()));
        } catch(Exception e) {
            logger.error("Can`t convert String to MD5 hash");
        }
        logger.debug("Added User to database");
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
        logger.debug("Deleted User from database");
    }

    @Override
    public List<User> findBy(String roleValue, String searchField, String value, int page, int amount, String sortField) {
        logger.debug("Getting User by");
        Sort sort = new Sort(Sort.Direction.ASC, sortField);
        PageRequest pageRequest = new PageRequest(page, amount, sort);
        Role role = roleService.getRoleByValue(roleValue);

        switch(searchField){
            case "firstName": {
                return userRepository.findByRoleAndFirstName(role, value, pageRequest).getContent();
            }
            case "lastName": {
                userRepository.findByRoleAndLastName(role, value, pageRequest).getContent();
            }
            case "address": {
                return userRepository.findByRoleAndAddress(role, value, pageRequest).getContent();
            }
            case "email": {
                return userRepository.findByRoleAndEmail(role, value, pageRequest).getContent();
            }
            case "phoneNumber": {
                return userRepository.findByRoleAndPhoneNumber(role, value, pageRequest).getContent();
            }
            default: return null;
        }
    }

    @Override
    public List<User> findByRole(String roleValue, int page, int amount, String sortField) {
        Sort sort = new Sort(Sort.Direction.ASC, sortField);
        PageRequest pageRequest = new PageRequest(page, amount, sort);

        Role role = roleService.getRoleByValue(roleValue);

        return userRepository.findByRole(role, pageRequest).getContent();
    }

    @Override
    public List<User> findByCreationDateAfter(String creationDate) {
        return null;
    }

    @Override
    public List<User> findByCreationDateAfterAndRole(Date creationDate, Role role) {
        return userRepository.findByCreationDateAfterAndRole(creationDate, role);
    }

    @Override
    public List<User> findByCreationDateAfter(Date creationDate) {
        return  userRepository.findByCreationDateAfter(creationDate);
    }

    @Override
    public List<User> findByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    public void changeUserLock(Long id) {
        User user = userRepository.findOne(id);
        user.setIsBlocked(!user.isBlocked());
        userRepository.save(user);
    }

    @Override
    public void changeUserLock(Long id) {
        User user = userRepository.findOne(id);
        user.setIsBlocked(!user.isBlocked());
        userRepository.save(user);
    }
}
