package dong.mirae.one.Service;

import dong.mirae.one.DTO.MemberDTO;
import dong.mirae.one.Entity.MemberEntity;
import dong.mirae.one.Entity.PK.MemberPK;
import dong.mirae.one.Repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.sql.SQLException;

@Service
@AllArgsConstructor
public class MemberService {
    private MemberRepository repository;

    public int checkData(MemberPK memberPK){
        return repository.countByMemberPKEquals(memberPK);
    }

    public MemberEntity checkPasswd(MemberPK memberPK){
        return repository.findByMemberPKEquals(memberPK);
    }

    public int checkEmail(String email){
        return repository.countByEmailEquals(email);
    }

    public boolean insertMember(MemberDTO memberDTO){
        try{
            repository.save(memberDTO.toEntity());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public MemberEntity checkAdminPasswd(MemberPK memberPK, int adminstat){
        return repository.findByMemberPKEqualsAndAdminEquals(memberPK, adminstat);
    }

    public void updatePass(String id, String passwd1){
        repository.updatePass(passwd1, id);
    }

    public void updateAdmin(String id){
        repository.updateAdmin(id);
    }

    @Transactional
    public void delete(String id){
        repository.deleteByMemberPK(new MemberPK(id));
    }
}
