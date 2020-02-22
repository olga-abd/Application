import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
    private static final SessionFactory sesstionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            sesstionFactory = configuration.configure().buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Error in creating Session Factory object." + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSesstionFactory() {
        return sesstionFactory;
    }
}
