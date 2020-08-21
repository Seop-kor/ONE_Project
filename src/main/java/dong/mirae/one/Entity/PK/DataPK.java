package dong.mirae.one.Entity.PK;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Embeddable
public class DataPK implements Serializable {
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int data_id;

    public DataPK(int data_id){
        this.data_id = data_id;
    }
}
