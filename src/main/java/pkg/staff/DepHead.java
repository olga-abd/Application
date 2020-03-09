package pkg.staff;

import pkg.application.Application;
import pkg.application.ApplicationStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
public class DepHead extends Staff {

    @OneToMany (mappedBy = "head")
    private Set<Employee> employees;

    @OneToMany (mappedBy = "head")
    private Set<Application> applications;

    /////
    public String print() {
        return getTabNum() + "\t" + getFio() + "\t" + "возраст: " + getAge() + "\t" + "грейд: " + getGrade().getGradeId() ;
    }

    /////
    public Set<Employee> getEmployees() {return employees;}
    public void setEmployees(Set<Employee> employees) {this.employees = employees;}

    public Set<Application> getApplications(){return applications;}
    public void setApplications(Set<Application> applications){this.applications = applications;}


    // Согласовать заявку
    public void approveApp (Application application, boolean decision) {
        System.out.println("class: " + this);
        //System.out.println(dh.print());
        application.setHead(this);
        application.setStatus(decision ? ApplicationStatus.AGREED : ApplicationStatus.DISAGREED);
    }


}
