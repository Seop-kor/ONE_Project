package dong.mirae.one.Repository;

import dong.mirae.one.Entity.MemberEntity;
import dong.mirae.one.Entity.PK.MemberPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<MemberEntity, MemberPK> {
    int countByMemberPKEquals(MemberPK memberPK);
    MemberEntity findByMemberPKEquals(MemberPK memberPK);
    int countByEmailEquals(String email);
    MemberEntity findByMemberPKEqualsAndAdminEquals(MemberPK memberPK, int admin);

    @Query(value = "update member set passwd1=:passwd where id=:id",nativeQuery = true)
    void updatePass(@Param("passwd") String passwd1, @Param("id") String id);

    @Query(value = "update member set admin=1 where id=:id", nativeQuery = true)
    void updateAdmin(@Param("id") String id);

    void deleteByMemberPK(MemberPK memberPK);
}
