package pkg.staff;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Set;

@Entity
public class GradeSum {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int gradeId;

    private float maxSum;

    @OneToMany (mappedBy = "grade")
    private Set<Staff> staff;

    public Set<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public float getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(float maxSum) {
        this.maxSum = maxSum;
    }
}
