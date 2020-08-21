package dong.mirae.one.Entity;

import dong.mirae.one.Entity.PK.DataPK;
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
@Table(name = "data")
public class DataEntity extends DateEntity{
    @EmbeddedId
    private DataPK dataPK;
    @Column(nullable = false)
    private String data_title;
    @Column(columnDefinition = "LONGTEXT")
    private String data_content;
    private String data_path;

    public void setData_title(String data_title){
        this.data_title = data_title;
    }

    @Builder
    public DataEntity(int data_id, String data_title, String data_content, String data_path){
        this.dataPK = new DataPK(data_id);
        this.data_title = data_title;
        this.data_content = data_content;
        this.data_path = data_path;
    }
}
