package dong.mirae.one.Entity;

import dong.mirae.one.Entity.PK.HomeworkPK;
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
@Table(name = "homework")
public class HomeworkEntity extends DateEntity{
    @EmbeddedId
    private HomeworkPK homeworkPK;

    @Column(nullable = false)
    private String homework_title;

    @Column(columnDefinition = "TEXT")
    private String homework_content;

    @Column(nullable = false)
    private String homework_start;

    @Column(nullable = false)
    private String homework_end;

    @Column(columnDefinition = "TEXT")
    private String receive_mail;

    @Builder
    public HomeworkEntity(int homework_id, String homework_title, String homework_content, String homework_start, String homework_end, String receive_mail){
        this.homeworkPK = new HomeworkPK(homework_id);
        this.homework_title = homework_title;
        this.homework_content = homework_content;
        this.homework_start = homework_start;
        this.homework_end = homework_end;
        this.receive_mail = receive_mail;
    }
}
