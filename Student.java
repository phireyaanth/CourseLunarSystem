import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    private String webID;
    private List<Course> courses;



    public Student(String webID){

        this.webID = webID;
        this.courses = new ArrayList<>();
    }

    public String getWebID(){
        return this.webID;
    }

    public void setWebID(String webID) {
        this.webID = webID;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

}
