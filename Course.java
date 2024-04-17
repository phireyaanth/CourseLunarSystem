import java.io.Serializable;


public class Course implements Serializable {
    private String department;
    private int number;
    private String semester;

    public Course(String department, int number, String semester){
        this.department = department;
        this.number = number;
        this.semester = semester;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getDepartment() {
        return department;
    }

    public String getDepartmentAndNumber(){
        return this.department + " " + this.number;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
