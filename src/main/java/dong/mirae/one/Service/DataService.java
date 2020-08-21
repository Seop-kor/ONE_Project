package dong.mirae.one.Service;

import dong.mirae.one.DTO.DataDTO;
import dong.mirae.one.Entity.DataEntity;
import dong.mirae.one.Entity.PK.DataPK;
import dong.mirae.one.Repository.DataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DataService {
    private DataRepository repository;

    public List<DataEntity> findByAll(){
        return repository.findAll();
    }

    public void save(DataDTO dataDTO){
        repository.save(dataDTO.toEntity());
    }

    public DataEntity findByData_id(int data_id){
        DataPK dataPK = new DataPK(data_id);
        return repository.findByDataPK(dataPK);
    }
}
