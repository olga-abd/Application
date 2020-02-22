import java.sql.Date;
import java.util.List;
import java.util.Set;

public class UpdateCourseStatus extends Thread{

    @Override
    public void run() {
        updateCycle();
    }

    private void updateCycle() {
        System.out.println("Cycle start");
        boolean show = true;
        while (true) {
            CourseDAO courseDAO = new CourseDAO();
            List<Course> courses = courseDAO.getAllCourses();

            Date currentDate = new Date(System.currentTimeMillis());

            for (Course course : courses) {
                Date startDate = course.getDateStart();
                Date endDate = course.getDateEnd();
                if(course.getCourseId() == 3 && show) {
                    System.out.println("cr: " + currentDate);
                    System.out.println("st: " + startDate);
                    System.out.println("ed: " + endDate);
                    System.out.println("st.comp: " + startDate.toLocalDate().compareTo(currentDate.toLocalDate()));
                    //System.out.println("st.eq: " + startDate.toLocalDate().equals(currentDate.toLocalDate()));
                    System.out.println("ed.comp: " + endDate.toLocalDate().compareTo(currentDate.toLocalDate()));
                    //System.out.println("ed.eq: " + endDate.equals(currentDate));
                }
                if (startDate.toLocalDate().compareTo(currentDate.toLocalDate()) <= 0 &&
                    endDate.toLocalDate().compareTo(currentDate.toLocalDate()) >= 0) {
                    Set<EmployeeCourse> employeeCourses = course.getEmployeeCourses();

                    for (EmployeeCourse employeeCourse : employeeCourses) {
                        if (employeeCourse.getStatus() != CourseStatus.INPROGRESS) {
                            employeeCourse.setStatus(CourseStatus.INPROGRESS);
                            EmployeeCourseDAO ecDAO = new EmployeeCourseDAO();
                            ecDAO.update(employeeCourse);
                        }
                    }
                }
                else if (endDate.toLocalDate().compareTo(currentDate.toLocalDate()) < 0) {
                    Set<EmployeeCourse> employeeCourses = course.getEmployeeCourses();
                    for (EmployeeCourse employeeCourse : employeeCourses) {
                        if (employeeCourse.getStatus() != CourseStatus.COMPLETED) {
                            employeeCourse.setStatus(CourseStatus.COMPLETED);
                            EmployeeCourseDAO ecDAO = new EmployeeCourseDAO();
                            ecDAO.update(employeeCourse);
                        }
                    }
                }
            }

            show = false;

        }
    }
}
