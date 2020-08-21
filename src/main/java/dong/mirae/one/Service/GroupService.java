package dong.mirae.one.Service;

import dong.mirae.one.DTO.GroupDTO;
import dong.mirae.one.Entity.GroupEntity;
import dong.mirae.one.Entity.PK.GroupPK;
import dong.mirae.one.Repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class GroupService {
    private GroupRepository repository;

    public void save(GroupDTO groupDTO){
        repository.save(groupDTO.toEntity());
    }

    public List<GroupEntity> findAll(){
        return repository.findAll();
    }

    public GroupEntity findByPk(int pk){
        return repository.findByGroupPK(new GroupPK(pk));
    }

    @Transactional
    public void delete(int pk){
        repository.deleteByGroupPK(new GroupPK(pk));
    }
}
