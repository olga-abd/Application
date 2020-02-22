import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
//        Employee employee = new Employee();
//        employee.setAge(54);
//        employee.setFio("работник Иванов");
//        employee.setGrade(5);
//        employee.setLogin("ivanov");
//        try {
//            employee.setPassword("12345");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        DepHead head = new DepHead();
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
//        HR hr = new HR();
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
//        StaffDAO staffDAODAO = new StaffDAO();
//        staffDAODAO.save(hr);
//        staffDAODAO.save(employee);

//        System.out.print("Для авторизации введите табельный номер: ");
//        Scanner scanner = new Scanner(System.in);
//
//        int tn = scanner.nextInt();
//
//        StaffDAO staffDAO = new StaffDAO();
//        //staffDAODAO.testConnection();
//        Staff user = staffDAO.findById(tn);
//        System.out.println(user.print());
//        System.out.println(user.getClass());
//        if (user instanceof Employee){
//            EmployeeInterface((Employee) user);
//        }
//
//        System.out.println(staffDAO.getSubordinatesById(2));

//        new UpdateCourseStatus().start();
        new MainForm2();

    }

    public static void EmployeeInterface(Employee user) {
        System.out.println("Hello, world");
    }
}
