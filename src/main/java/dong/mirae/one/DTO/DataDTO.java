package dong.mirae.one.DTO;

import dong.mirae.one.Entity.DataEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class DataDTO {
    private int data_id;
    private String data_title;
    private String data_content;
    private String data_path;

    public DataEntity toEntity(){
        return DataEntity.builder().data_id(data_id).data_title(data_title).data_content(data_content).data_path(data_path).build();
    }
}
