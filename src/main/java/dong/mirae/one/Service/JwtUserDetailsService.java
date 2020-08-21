package dong.mirae.one.Service;

import dong.mirae.one.Entity.MemberEntity;
import dong.mirae.one.Entity.PK.MemberPK;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private MemberService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity entity = service.checkPasswd(new MemberPK(username));
        if(entity.getMemberPK().getId() != null){
            return new User(entity.getMemberPK().getId(), entity.getPasswd1(), new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not found with username:");
        }
    }
}
