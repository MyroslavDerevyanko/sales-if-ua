package sales.notification.service;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import sales.users.domain.User;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by taras on 31.07.15.
 */
@Service("registration")
public class RegistrationServiceImpl implements RegistrationService {
    final static Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);


    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public void register(User user) {
        //
        sendConfirmationEmail(user);
    }

    private void sendConfirmationEmail(final User user) {
        logger.debug("Creating mail text for email confirmation");
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(user.getEmail());
                message.setFrom("salesifua@gmail.com");
                Map model = new HashMap();
                model.put("user", user);
                //String text = "This is my email notification!";
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "email-template/registration-confirmation.vm", model);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }
}
