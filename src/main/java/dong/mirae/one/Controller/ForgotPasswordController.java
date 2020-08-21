package dong.mirae.one.Controller;

import dong.mirae.one.Config.JwtTokenUtil;
import dong.mirae.one.Entity.MemberEntity;
import dong.mirae.one.Entity.PK.MemberPK;
import dong.mirae.one.Service.JwtUserDetailsService;
import dong.mirae.one.Service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@AllArgsConstructor
public class ForgotPasswordController {
    private JavaMailSender mailSender;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;
    private MemberService memberService;
    private PasswordEncoder encoder;

    @GetMapping("/forgot")
    public String forgot(){
        return "forgotpass";
    }

    @PostMapping("/forgotpass")
    public @ResponseBody
    boolean pass(@RequestParam("id")String id) throws Exception{
        MemberEntity memberEntity = memberService.checkPasswd(new MemberPK(id));
//        authenticate(id , "toor");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(id);
        final String Token = jwtTokenUtil.generateToken(userDetails);
        MailThread mailThread = new MailThread(memberEntity.getEmail(), Token);
        mailThread.start();
        return true;
    }


    @GetMapping("/modifypass")
    public String modify(@RequestParam("Authorization")String token, Model model, HttpServletResponse response) throws Exception{
        Date date = new Date();
        if(jwtTokenUtil.getExpirationDateFromToken(token.substring(7)).after(date)){
            model.addAttribute("token", token.substring(7));
            return "modifypass";
        }else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalidate");
            return null;
        }
    }

    @PostMapping("/modifypass")
    public @ResponseBody
    boolean modidify(@RequestParam("passwd")String passwd, @RequestParam("token")String token){
        String id = jwtTokenUtil.getUsernameFromToken(token);
        passwd = encoder.encode(passwd);
        memberService.updatePass(id, passwd);
        return true;
    }

    private class MailThread extends Thread{
        private String email;
        private String token;

        public MailThread(String email, String token){
            this.email = email;
            this.token = token;
        }

        private void sendmail(){
            final MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
                    helper.setFrom("dbdlstjq960@naver.com");
                    helper.setTo(email);
                    helper.setSubject("O.N.E. 비밀번호 변경");
                    helper.setText("<html>" +
                            "<body>" +
                            "전공동아리 O.N.E. 입니다. 비밀번호를 변경하려면 밑에 변경하기를 눌러주세요." +
                            "<br>" +
                            "<a href='localhost:8080/modifypass?Authorization=Bearer " + token + "'>변경하기</a>" +
                            "</body>" +
                            "</html>",true);
                }
            };
            mailSender.send(preparator);
        }

        @Override
        public void run() {
            sendmail();
        }
    }
}