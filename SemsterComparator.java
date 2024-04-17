import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.Serializable;

public class SemsterComparator implements Serializable {
    public void sortCourses(ArrayList<String> courses) {
        Collections.sort(courses, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // Extract semester and year from each string
                String semesterYear1 = s1.substring(s1.lastIndexOf(' ') + 1);
                String semesterYear2 = s2.substring(s2.lastIndexOf(' ') + 1);

                // Compare year first
                int year1 = Integer.parseInt(semesterYear1.substring(1));
                int year2 = Integer.parseInt(semesterYear2.substring(1));
                if (year1 != year2) {
                    return Integer.compare(year1, year2);
                }

                // If years are the same, compare semesters
                char semester1 = semesterYear1.charAt(0);
                char semester2 = semesterYear2.charAt(0);
                // Assuming 'F' for Fall comes before 'S' for Spring
                return Character.compare(semester1, semester2);
            }
        });
    }
}
