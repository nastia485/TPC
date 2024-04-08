import java.util.Random;

public class TeacherThread extends Thread {
    private Teacher teacher;

    private Journal journal;

    public TeacherThread(Teacher teacher, Journal journal) {
        this.teacher = teacher;
        this.journal = journal;
    }

    @Override
    public void run() {
        journal.enterGradesFromTeacher(teacher);
    }
}
