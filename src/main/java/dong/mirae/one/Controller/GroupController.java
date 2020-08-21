package dong.mirae.one.Controller;

import dong.mirae.one.DTO.GroupDTO;
import dong.mirae.one.Entity.GroupEntity;
import dong.mirae.one.Service.GroupService;
import dong.mirae.one.Service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class GroupController {
    private GroupService service;
    private MemberService memberService;

    @GetMapping("/group_get")
    public @ResponseBody
    List<Map<String, String>> groupget(){
        List<GroupEntity> list = service.findAll();
        List<Map<String, String>> reval = new ArrayList<>();

        for(GroupEntity item : list){
            Map<String, String> it = new HashMap<>();
            it.put("pk", String.valueOf(item.getGroupPK().getPk()));
            it.put("name", item.getName());
            it.put("phone", item.getPhone());
            it.put("student_id", String.valueOf(item.getStudent_id()));
            it.put("motive", item.getMotive());
            it.put("wanted", item.getWanted());

            reval.add(it);
        }

//        log.info(reval.toString()); 여기는 문제없음 제대로 넘어감

        return reval;
    }

    @PostMapping("/regi_group")
    public @ResponseBody
    boolean regigroup(GroupDTO groupDTO){
        service.save(groupDTO);
        return true;
    }

    @GetMapping("/admin/ok")
    public @ResponseBody boolean ok(@PathParam("pk")int pk) throws Exception{
        service.delete(pk);
        return true;
    }

    @GetMapping("/admin/sorry")
    public @ResponseBody boolean sorry(@PathParam("pk")int pk) throws Exception{
        GroupEntity entity = service.findByPk(pk);
        service.delete(pk);
        memberService.delete(entity.getId());
        return true;
    }
}
