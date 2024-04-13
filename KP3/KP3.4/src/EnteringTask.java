import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class EnteringTask extends RecursiveAction {

    static int threshold = 3;
    Journal journal;

    Teacher lecturer;

    List<Teacher> teachers;

    int start;

    int end;

    public EnteringTask(Journal journal, int start, int end) {
        this.journal = journal;
        this.start = start;
        this.end = end;
        //this.lecturer = new Teacher("Lecturer", new int[]{1, 2, 3});
        int groupNum = journal.getGroupNum();
        int groupNo;
        int[] lecturerGroups = new int[groupNum];
        this.teachers = new ArrayList<>();
        for (int i = 0; i < groupNum; i++) {
            String position = "Assistant " + (i + 1);
            groupNo = i + 1;
            teachers.add(new Teacher(position, new int[]{groupNo}));
            lecturerGroups[i] = groupNo;
        }
        this.teachers.add(new Teacher("Lecturer", lecturerGroups));

    }

    @Override
    protected void compute() {
        if (end - start < threshold) {
            computeDirectly();
        } else {
            int middle = (end + start) / 2;

            EnteringTask subTask1 = new EnteringTask(journal, start, middle);
            EnteringTask subTask2 = new EnteringTask(journal, middle, end);

            invokeAll(subTask1, subTask2);

        }
    }

    private void computeDirectly(){
        for (Teacher teacher : teachers) {
            journal.enterGradesFromTeacher(teacher, start, end);
        }
    }

}
