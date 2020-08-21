package dong.mirae.one.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Bean
    public JavaMailSenderImpl mailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        Properties props = new Properties();
        props.setProperty("mail.smtp.starttls.enable","true");
        props.setProperty("mail.smtp.auth","true");
        props.setProperty("mail.smtps.ssl.checkserveridentity","true");
        props.setProperty("mail.smtps.ssl.trust","*");
        props.setProperty("mail.debug","true");
        props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        javaMailSender.setHost("smtp.naver.com");
        javaMailSender.setPort(465);
        javaMailSender.setUsername("dbdlstjq960");
        javaMailSender.setPassword("apfhdgl0");
        javaMailSender.setJavaMailProperties(props);
        return javaMailSender;
    }
}
