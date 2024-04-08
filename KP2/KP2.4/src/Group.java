import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Group {
    private int studNo;
    private int[] grades;

    public Group(int studNo) {
        this.studNo = studNo;
        grades = new int[15];
    }

    public synchronized void setGrade(int studNo, int grade){
        grades[studNo] = grade;
    };

    public int[] getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        return "Group{" +
                "studNo=" + studNo +
                ", grades=" + Arrays.toString(grades) +
                '}';
    }
}
