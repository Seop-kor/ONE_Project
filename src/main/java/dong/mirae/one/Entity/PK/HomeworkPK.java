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
public class HomeworkPK implements Serializable {
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int homework_id;

    public HomeworkPK(int homework_id){
        this.homework_id = homework_id;
    }
}
