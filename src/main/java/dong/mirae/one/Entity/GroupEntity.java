package dong.mirae.one.Entity;

import dong.mirae.one.Entity.PK.GroupPK;
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
@Table(name = "joingroup")
public class GroupEntity {
    @EmbeddedId
    private GroupPK groupPK;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private int student_id;

    @Column(nullable = false)
    private String motive;

    private String wanted;

    @Builder
    public GroupEntity(int pk, String id, String name, String phone, int student_id, String motive, String wanted){
        this.groupPK = new GroupPK(pk);
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.student_id = student_id;
        this.motive = motive;
        this.wanted = wanted;
    }

}
