package dong.mirae.one.Entity;

import dong.mirae.one.Entity.PK.MemberPK;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class MemberEntity {
    @EmbeddedId
    private MemberPK memberPK;

    @Column(nullable = false)
    private String passwd1;

    private String name;

    private String age;

    private String email;

    private String phone;

    private String student_id;

    private int admin;

    @Builder
    public MemberEntity(String id, String passwd1, String name, String age, String email, String phone, String student_id, int admin){
        this.memberPK = new MemberPK(id);
        this.passwd1 = passwd1;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.student_id = student_id;
        this.admin = admin;
    }
}
