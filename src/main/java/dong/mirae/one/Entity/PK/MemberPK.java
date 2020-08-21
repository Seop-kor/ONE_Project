package dong.mirae.one.Entity.PK;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Embeddable
public class MemberPK implements Serializable {
    @Column(nullable = false)
    private String id;

    public MemberPK(String id){
        this.id = id;
    }
}
