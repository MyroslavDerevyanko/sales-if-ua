package sales.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by taras on 28.07.15.
 */
@Controller
public class LoginController {
    final static Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Resource(name = "sessionRegistry")
    private SessionRegistryImpl sessionRegistry;

    @RequestMapping(
            value = "/login/success")
    public void logged(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Action after successful authorization");
        HttpSession session = request.getSession(false);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        logger.info(name);

        session.setAttribute("userEmail", name);
        session.setAttribute("check", "true");

        response.sendRedirect("/Practice/");
    }

    @RequestMapping(
            value = "/login/failure")
    public void failure(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.debug("Action after failed authorization");
        HttpSession session = request.getSession(false);

        session.setAttribute("check", "error");

        response.sendRedirect("/Practice/");
    }

    @RequestMapping(
            value = "/logout/success")
    public String logout() {
        logger.debug("Action after successful logout");
        String message = "Logout Success!";
        return "redirect:/login?message="+message;
    }
}
