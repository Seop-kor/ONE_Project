package dong.mirae.one.Controller;

import dong.mirae.one.DTO.MemberDTO;
import dong.mirae.one.Entity.MemberEntity;
import dong.mirae.one.Entity.PK.MemberPK;
import dong.mirae.one.Service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
@Log
public class LoginController {
    private MemberService service;
    private PasswordEncoder encoder;

    @PostMapping("/loginff")
    public @ResponseBody
    boolean goSubmit(HttpSession session, MemberDTO bean){
        if(service.checkPasswd(new MemberPK(bean.getId())) == null){
            return false;
        }
        MemberEntity passwd = service.checkPasswd(new MemberPK(bean.getId()));
        if(encoder.matches(bean.getPasswd1(), passwd.getPasswd1())){
            int login = service.checkData(new MemberPK(bean.getId()));
            if(login == 1){
                session.setAttribute("id", passwd.getMemberPK().getId());
                session.setAttribute("adminstat", passwd.getAdmin());
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @GetMapping("/admin/login")
    public String adminlogin(){
        return "adminlogin";
    }

    @PostMapping("/admin/login")
    public String admin_login(MemberDTO bean, HttpSession session){
        MemberEntity passwd = service.checkAdminPasswd(new MemberPK(bean.getId()), 1);
        if(passwd != null && encoder.matches(bean.getPasswd1(), passwd.getPasswd1())){
            int login = service.checkData(new MemberPK(bean.getId()));

            if(login == 1){
                session.setAttribute("id", passwd.getMemberPK().getId());
                session.setAttribute("adminstat", passwd.getAdmin());
                return "redirect:/admin";
            }else{
                return "adminlogin";
            }
        }else{
            return "adminlogin";
        }
    }

    @GetMapping("/memberinfo")
    public @ResponseBody
    Map<String, String> getinfo(@PathParam("id")String id){
        Map<String, String> info = new HashMap<>();
        MemberEntity entityinfo = service.checkPasswd(new MemberPK(id)); //메소드 이름만 패스워드고 정보 확인하는 메소드임.
        info.put("name", entityinfo.getName());
        info.put("student_id",entityinfo.getStudent_id());
        info.put("phone", entityinfo.getPhone());
        return info;
    }

    @GetMapping("/logoutf")
    public String goLogout(HttpSession session){
        session.invalidate();
        return "index1";
    }
}
