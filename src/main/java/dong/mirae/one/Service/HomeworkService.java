package dong.mirae.one.Service;

import dong.mirae.one.DTO.HomeworkDTO;
import dong.mirae.one.Entity.HomeworkEntity;
import dong.mirae.one.Repository.HomeworkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HomeworkService {
    private HomeworkRepository repository;

    public void save(HomeworkDTO homeworkDTO){
        repository.save(homeworkDTO.toEntity());
    }

    public List<HomeworkEntity> findAll(){
        return repository.findAll();
    }

    public List<HomeworkEntity> findBetween(){
        return repository.findBetween();
    }
}
