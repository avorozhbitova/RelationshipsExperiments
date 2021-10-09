package core;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

@Data
public class PurchaseKey implements Serializable {
    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseKey that = (PurchaseKey) o;
        return Objects.equals(studentName, that.studentName) && Objects.equals(courseName, that.courseName);
    }

    @Override
    public int hashCode() {
        int result = studentName.hashCode();
        result += 31 * courseName.hashCode();
        return result;
    }
}
