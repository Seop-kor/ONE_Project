package dong.mirae.one.DTO;

import dong.mirae.one.Entity.GroupEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class GroupDTO {
    private int pk;
    private String id;
    private String name;
    private String phone;
    private int student_id;
    private String motive;
    private String wanted;

    public GroupEntity toEntity(){
        return GroupEntity.builder().pk(pk).id(id).name(name).phone(phone).student_id(student_id).motive(motive).wanted(wanted).build();
    }
}
