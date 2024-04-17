import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.Serializable;
public class CourseNameComparator {

    // Method to sort a list of course strings
    public void sortCourses(ArrayList<String> courses) {
        Collections.sort(courses, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // Split the strings into parts: department code and number
                String[] parts1 = s1.split(" ");
                String[] parts2 = s2.split(" ");

                // First compare the department codes
                int deptCompare = parts1[0].compareTo(parts2[0]);
                if (deptCompare != 0) {
                    return deptCompare;
                }

                // If department codes are the same, compare the numbers
                return Integer.compare(Integer.parseInt(parts1[1]), Integer.parseInt(parts2[1]));
            }
        });
    }
}