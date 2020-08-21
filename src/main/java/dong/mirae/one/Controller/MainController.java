package dong.mirae.one.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(){
        return "index1";
    }

    @GetMapping("/admin")
    public String admin(){
        return "adminpage";
    }
}
