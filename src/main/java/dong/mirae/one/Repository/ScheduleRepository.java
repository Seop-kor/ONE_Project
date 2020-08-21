package dong.mirae.one.Repository;

import dong.mirae.one.Entity.PK.SchedulePK;
import dong.mirae.one.Entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, SchedulePK> {
    @Query(value = "select * from tdate where date between now() and date_add(now(), interval 1 month) order by date desc",nativeQuery = true)
    List<ScheduleEntity> findAllByDateBetween();
}
