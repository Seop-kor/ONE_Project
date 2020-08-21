package dong.mirae.one.Entity;

import dong.mirae.one.Entity.PK.SchedulePK;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "tdate")
public class ScheduleEntity {
    @EmbeddedId
    private SchedulePK schedulePK;

    private String date;

    private String title;

    @Builder
    public ScheduleEntity(int no, String title, String date){
        this.schedulePK = new SchedulePK(no);
        this.title = title;
        this.date = date;
    }
}
