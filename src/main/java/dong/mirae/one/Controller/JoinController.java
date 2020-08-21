package dong.mirae.one.Controller;

import dong.mirae.one.DTO.MemberDTO;
import dong.mirae.one.Entity.PK.MemberPK;
import dong.mirae.one.Service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
@Log
public class JoinController {
    private MemberService service;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public String insert(MemberDTO bean) {
        log.info(bean.getName());
        String encodepasswd1 = passwordEncoder.encode(bean.getPasswd1());
        bean.setPasswd1(encodepasswd1);

        boolean b =service.insertMember(bean);
        if(b) {
            return "index1";
        } else {
            return "index1"; //error
        }
    }

    @PostMapping("/idCheck")
    public @ResponseBody
    Map<String, String> goSubmit(HttpSession session, MemberDTO memberDTO){
        Map<String, String> idCheckResult = new HashMap<String, String>();
        MemberPK memberPK = new MemberPK(memberDTO.getId());
        int realid = service.checkData(memberPK);
        if(realid == 1){
            idCheckResult.put("idCheckResult", "false");
            return idCheckResult;
        }else{
            idCheckResult.put("idCheckResult", "success");
            return idCheckResult;
        }
    }
    //여기 하다 말았음

    @PostMapping("/emailCheck")
    public @ResponseBody
    Map<String, String> goSubmit(HttpSession session, @RequestParam("email")String email){
        Map<String, String> result = new HashMap<String, String>();
        int realemail = service.checkEmail(email);

        if(realemail == 1){
            result.put("emailCheckResult", "false");
            return result;
        }else{
            result.put("emailCheckResult", "success");
            return result;
        }
    }
}
