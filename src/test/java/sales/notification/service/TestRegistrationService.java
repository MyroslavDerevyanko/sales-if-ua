package sales.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import sales.roles.service.RoleService;
import sales.users.domain.User;
import sales.users.service.CityService;
import sales.util.Constants;

import java.util.Date;

/**
 * Created by taras on 07.08.15.
 */
@ContextConfiguration(locations =
        {
                "classpath:META-INF/applicationContext.xml",
                "classpath:test-mvc-servlet.xml"
        },
        loader = ContextLoader.class)
@WebAppConfiguration
public class TestRegistrationService extends AbstractTestNGSpringContextTests {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    CityService cityService;

    @Autowired
    RoleService roleService;

    @Test
    public void testSendEmail() {

        User user = new User("1a1dc91c907325c69271ddf0c944bc72",
                "taras",
                "client",
                cityService.getOneByName("смт Зуя"),
                "tarik.danylyuk@gmail.com",
                "12342535",
                new Date(),
                roleService.getRoleByValue(Constants.CLIENT));

        registrationService.register(user);
    }
}
