package dong.mirae.one.Service;

import dong.mirae.one.DTO.ScheduleDTO;
import dong.mirae.one.Entity.ScheduleEntity;
import dong.mirae.one.Repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {
    private ScheduleRepository repository;

    public List<ScheduleEntity> selectSchedule(){
        return repository.findAllByDateBetween();
    }

    public void save(ScheduleDTO scheduleDTO){
        repository.save(scheduleDTO.toEntity());
    }
}
