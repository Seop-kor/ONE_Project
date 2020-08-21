package dong.mirae.one.DTO;

import dong.mirae.one.Entity.HomeworkEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class HomeworkDTO {
    private int homework_id;
    private String homework_title;
    private String homework_content;
    private String homework_start;
    private String homework_end;
    private String receive_mail;

    public HomeworkEntity toEntity(){
        return HomeworkEntity.builder().homework_id(homework_id).homework_title(homework_title).homework_content(homework_content)
                .homework_start(homework_start).homework_end(homework_end).receive_mail(receive_mail).build();
    }
}
