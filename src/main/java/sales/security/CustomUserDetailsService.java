package sales.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sales.users.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by taras on 28.07.15.
 */

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
    final static Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);


    @Autowired
    private UserRepository userRepository;

    /**
     * Returns a populated {@link UserDetails} object.
     * The username is first retrieved from the database and then mapped to
     * a {@link UserDetails} object.
     */
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        logger.info("Process of authorization");

        sales.users.domain.User user = userRepository.findByEmail(userEmail);
        throwExceptionIfNotFound(user, userEmail);


        sales.users.domain.User domainShop = userRepository.findByEmail(userEmail);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        User authUser = new User(
            domainShop.getEmail(),
            domainShop.getPassword().toLowerCase(),
            enabled,
            accountNonExpired,
            credentialsNonExpired,
            accountNonLocked,
            getAuthorities(domainShop.getRole().getId()));

        return authUser;
    }

    private void throwExceptionIfNotFound(sales.users.domain.User user, String login) {
        if (user == null) {
            throw new UsernameNotFoundException("User with login " + login + "  has not been found.");
        }
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Long role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }

    public List<String> getRoles(Long role) {
        List<String> roles = new ArrayList<String>();

        switch(role.intValue()) {
            case 1: {
                roles.add("ROLE_CLIENT");
                roles.add("ROLE_ADMIN");
                roles.add("ROLE_SHOP");
                break;
            }
            case 2: {
                roles.add("ROLE_CLIENT");
                break;
            }
            case 3: {
                roles.add("ROLE_SHOP");
                break;
            }
        }

        return roles;
    }
    
    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}