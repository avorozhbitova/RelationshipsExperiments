package core;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchase {
    @EmbeddedId
    private LinkedPurchaseKey key;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

    @Override
    public String toString() {
        return "Студент с id " + studentId +
                " обучается на курсе id " + courseId;
    }
}
