import java.util.Random;

public class Teacher {
    private String position;

    private int[] groupNum;

    public Teacher(String pos, int[] groupNum) {
        this.position = pos;
        this.groupNum = groupNum;
    }

    public int[] getGroupNum() {
        return groupNum;
    }

    public String getPosition() {
        return position;
    }
}
