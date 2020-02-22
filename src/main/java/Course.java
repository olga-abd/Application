import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int courseId;

    private String name; // название курса
    private Date dateStart; // дата начала
    private Date dateEnd; // дата окончания

    private String traningCenter; // учебный центр
    private String duration; // продолжительность
    private float price; // цена

    private int maxCount; // максимальное количество обучающихся

    @OneToMany (mappedBy = "course", fetch = FetchType.EAGER)
    private Set<EmployeeCourse> employeeCourses;

    @OneToMany (mappedBy = "course")
    private Set<Application> applications;

    /////////
    public int getCourseId(){return courseId;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public Date getDateStart(){return dateStart;}
    public void setDateStart(Date dateStart) {this.dateStart = dateStart;}

    public Date getDateEnd(){return dateEnd;}
    public void setDateEnd(Date dateEnd){this.dateEnd = dateEnd;}

    public int getMaxCount(){return maxCount;}
    public void setMaxCount(int maxCount){this.maxCount = maxCount;}

    public Set<EmployeeCourse> getEmployeeCourses() {return employeeCourses;}
    public void setEmployeeCourses(Set<EmployeeCourse> employeeCourses) {this.employeeCourses = employeeCourses;}

    public Set<Application> getApplications(){return applications;}
    public void setApplications(Set<Application> applications){this.applications = applications;}

    public String getTraningCenter(){return traningCenter;}
    public void setTraningCenter(String traningCenter){this.traningCenter = traningCenter;}

    public String getDuration(){return duration;}
    public void setDuration(String duration){this.duration = duration;}

    public float getPrice(){return price;}
    public void setPrice(float price){this.price = price;}


    @Override
    public boolean equals (Object object){
        return courseId == ((Course) object).getCourseId();
    }

}
