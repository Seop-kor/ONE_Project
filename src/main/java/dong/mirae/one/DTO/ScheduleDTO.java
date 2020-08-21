package dong.mirae.one.DTO;

import dong.mirae.one.Entity.ScheduleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ScheduleDTO {
    private int no;
    private String date;
    private String title;

    public ScheduleEntity toEntity(){
        return ScheduleEntity.builder().no(no).date(date).title(title).build();
    }
}
