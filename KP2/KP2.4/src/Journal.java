import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Journal {

    private static final int studNum = 15;
    private static final int groupNum = 3;

    int enteredGrades;
    private List<Group> groups;

    public Journal() {
        groups = new ArrayList<>();
        for (int i = 0; i < groupNum; i++) {
            groups.add(new Group(studNum));
        }
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void enterGradesFromTeacher(Teacher teacher) {
        int groupIndex;
        String position = teacher.getPosition();
        if (position.equals("Lecturer")) {
            for (int i = 0; i < teacher.getGroupNum().length; i++) {
                groupIndex = i;
                enterGrades(teacher, this.getGroups().get(groupIndex), i + 1);
            }
        } else if (position.contains("Assistant")) {
            groupIndex = teacher.getGroupNum()[0] - 1;
            enterGrades(teacher, this.getGroups().get(groupIndex), teacher.getGroupNum()[0]);
        } else System.out.println("This teacher is not allowed to enter grades in this journal!");

    }

    private void enterGrades(Teacher teacher, Group group, int groupNum) {
        int grade;
        for (int i = 0; i < group.getGrades().length; i++) {
            grade = randomGrade();
            if (group.getGrades()[i] == 0) {
                group.setGrade(i, grade);
                System.out.println("Teacher " + teacher.getPosition() + " have entered grade " + grade +
                        " to student No " + (i + 1) + " in group No " + groupNum);
            } else
                System.out.println("Teacher " + teacher.getPosition() + " cannot enter grade " + grade +
                        " to student No " + (i + 1) + " in group No " + groupNum+" , because the grade is already in journal.");
        }

    }

    private int randomGrade() {
        int max = 100;
        int min = 60;
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "enteredGrades=" + enteredGrades +
                ", groups=" + groups +
                '}';
    }
}
