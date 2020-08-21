package dong.mirae.one.Controller;

import dong.mirae.one.DTO.ScheduleDTO;
import dong.mirae.one.Entity.ScheduleEntity;
import dong.mirae.one.Service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ScheduleController {
    private ScheduleService service;

    @GetMapping("/schedule")
    public @ResponseBody
    Map<String, Object> scheduleFunc(){
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        List<ScheduleEntity> sc = service.selectSchedule();

        for(ScheduleEntity s : sc){
            Map<String, String> data = new HashMap<String, String>();

            data.put("no", String.valueOf(s.getSchedulePK().getNo()));
            data.put("date", s.getDate().toString());
            data.put("title", s.getTitle());

            dataList.add(data);
        }
        Map<String, Object> genres = new HashMap<String, Object>();
        genres.put("datas", dataList);

        return genres;
    }

    @PostMapping("/schedule_register_save")
    public @ResponseBody
    boolean schedule_save(ScheduleDTO scheduleDTO){
        service.save(scheduleDTO);
        return true;
    }
}
