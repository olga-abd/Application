import pkg.forms.MainForm2;
import pkg.staff.DepHead;
import pkg.staff.Employee;
import pkg.staff.GradeSum;
import pkg.staff.StaffDAO;

public class Main {
    public static void main (String[] args) {
//        pkg.staff.Employee employee = new pkg.staff.Employee();
//        employee.setAge(55);
//        employee.setFio("третий работник");
//        GradeSum gradeSum = new GradeSum();
//        gradeSum.setGradeId(5);
//        gradeSum.setMaxSum(35000);
//
//        employee.setGrade(gradeSum);
//        employee.setLogin("ivanov3");
//        try {
//            employee.setPassword("12345");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        StaffDAO staffDAO = new StaffDAO();
//        DepHead head = (DepHead) staffDAO.findById(2);
//        pkg.staff.DepHead head = new pkg.staff.DepHead();
//        head.setAge(46);
//        head.setFio("начальник Петров");
//        head.setGrade(23);
//        head.setLogin("petrov");
//        try {
//            head.setPassword("qwerty");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        employee.setHead(head);
//
//        pkg.staff.HR hr = new pkg.staff.HR();
//        hr.setAge(30);
//        hr.setFio("сотрудник hr");
//        hr.setGrade(5);
//        hr.setLogin("hr");
//        try {
//            hr.setPassword("Zz");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        pkg.staff.StaffDAO staffDAODAO = new pkg.staff.StaffDAO();
//        staffDAODAO.save(hr);
//        staffDAO.save(employee);

//        System.out.print("Для авторизации введите табельный номер: ");
//        Scanner scanner = new Scanner(System.in);
//
//        int tn = scanner.nextInt();
//
//        pkg.staff.StaffDAO staffDAO = new pkg.staff.StaffDAO();
//        //staffDAODAO.testConnection();
//        pkg.staff.Staff user = staffDAO.findById(tn);
//        System.out.println(user.print());
//        System.out.println(user.getClass());
//        if (user instanceof pkg.staff.Employee){
//            EmployeeInterface((pkg.staff.Employee) user);
//        }
//
//        System.out.println(staffDAO.getSubordinatesById(2));

//        new UpdateCourseStatus().start();
        new MainForm2();
//        System.out.println("Hello");

    }

    public static void EmployeeInterface(Employee user) {
        System.out.println("Hello, world");
    }
}
