package core;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;

@Data
@Entity
@Table(name = "PurchaseList")
public class Purchase {
    @EmbeddedId
    private PurchaseKey key;

    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;

    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private Calendar subscriptionDate;
}
