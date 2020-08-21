package dong.mirae.one.Controller;

import dong.mirae.one.Service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class AdminController {
    private MemberService memberService;

    @PostMapping("/admin/give")
    public @ResponseBody
    boolean give_admin(@RequestParam("id")String id){
        memberService.updateAdmin(id);
        return true;
    }
}
