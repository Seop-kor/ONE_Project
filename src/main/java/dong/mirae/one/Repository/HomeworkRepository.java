package dong.mirae.one.Repository;

import dong.mirae.one.Entity.HomeworkEntity;
import dong.mirae.one.Entity.PK.HomeworkPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HomeworkRepository extends JpaRepository<HomeworkEntity, HomeworkPK> {
    @Query(value = "select * from homework where homework_end >= now() order by homework_id asc",nativeQuery = true)
    List<HomeworkEntity> findBetween();
}
