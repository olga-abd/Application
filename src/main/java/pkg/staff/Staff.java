package pkg.staff;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Entity
public abstract class Staff {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int tabNum;

    private String fio;
    private int age;
    private int grade;
    private String login;
    private String password;

    public abstract String print();

    public int getTabNum() {return tabNum;}
    public String getFio() {return fio;}
    public void setFio (String fio) {this.fio = fio;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public int getGrade() {return grade;}
    public void  setGrade(int grade) {this.grade = grade;}
    public void setLogin (String login){this.login = login;}
    public String getLogin (){return login;}
    public void setPassword (String password) throws Exception {
        this.password = byteArrayToHexString(computeHash(password));
    }
    public String getPassword () {return password;}

    public boolean checkPassword(String password) {
//        System.out.println(this.password);
//        System.out.println(byteArrayToHexString(computeHash(password)));
        return this.password.equals(byteArrayToHexString(computeHash(password)));
    }



    private static byte[] computeHash(String x)
            //throws Exception
    {
        java.security.MessageDigest d =null;
        try {
            d = java.security.MessageDigest.getInstance("SHA-1");
            d.reset();
            d.update(x.getBytes());
            //System.out.println(x);
//            System.out.println(x.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("error");
        }
        return  d.digest();
    }

    private static String byteArrayToHexString(byte[] b){
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++){
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
//        System.out.println(sb.toString().toUpperCase());
        return sb.toString().toUpperCase();
    }
}
