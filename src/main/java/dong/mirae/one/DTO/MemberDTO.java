package dong.mirae.one.DTO;

import dong.mirae.one.Entity.MemberEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class MemberDTO {
    private String id, passwd1, name, age,email,phone,student_id;
    private int admin;

    public MemberEntity toEntity(){
        return MemberEntity.builder().id(id).passwd1(passwd1).name(name).age(age).email(email).phone(phone).student_id(student_id)
                .admin(admin).build();
    }
}
