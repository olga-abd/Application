package pkg.dao;

import org.hibernate.Session;
import pkg.staff.GradeSum;
import pkg.utils.HibernateUtils;

public class GradeDAO {

    public GradeSum getGradeById (int id){
        Session session = HibernateUtils.getSesstionFactory().openSession();
        GradeSum gradeSum = session.get(GradeSum.class, id);
        session.close();
        return gradeSum;
    }
}
