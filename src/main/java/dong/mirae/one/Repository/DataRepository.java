package dong.mirae.one.Repository;


import dong.mirae.one.Entity.DataEntity;
import dong.mirae.one.Entity.PK.DataPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<DataEntity, DataPK> {
    DataEntity findByDataPK(DataPK dataPK);
}
