package home.pb.springwebmvc;

import java.util.Date;

public class Student {

    private String name;

    private Date date;

    private static Integer age;

    private Student student;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static Integer getAge() {
        return age;
    }

    public static void setAge(Integer age) {
        Student.age = age;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
