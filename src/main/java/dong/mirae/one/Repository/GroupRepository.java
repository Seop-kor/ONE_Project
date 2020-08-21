package dong.mirae.one.Repository;

import dong.mirae.one.Entity.GroupEntity;
import dong.mirae.one.Entity.PK.GroupPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, GroupPK> {
    GroupEntity findByGroupPK(GroupPK groupPK);
    void deleteByGroupPK(GroupPK groupPK);
}
