import java.io.*;
import java.util.*;

public class LunarSystem {
    private static HashMap<String, Student> database;
    public static void main (String[]args){
        try (FileInputStream fileIn = new FileInputStream("database.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            database = (HashMap<String, Student>) in.readObject();
            //System.out.println("Database loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            //System.out.println("Error loading the database, initializing new database.");
            database = new HashMap<>(); // Initialize if file not found or other error
        }
        System.out.println("Welcome to the Lunar System, a second place course registration system for second rate courses at a second class school.");
        print();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().toUpperCase();
        while(!input.equals("Q") || !input.equals("X")){
            switch(input){
                case "L":
                    System.out.println("Please enter webid: ");
                    String webid = sc.nextLine();
                    if(webid.toUpperCase().equals("REGISTRAR")){
                        System.out.println("Welcome Registrar");
                        System.out.println("");
                        print_registrar();
                        String res_input = sc.nextLine().toUpperCase();
                        while(!res_input.equals("L")){
                            switch(res_input){
                                case "R":
                                    System.out.println("Please enter a webid for the new student: ");
                                    String name = sc.nextLine();
                                    Student newstd = new Student(name);
                                    database.put(newstd.getWebID(), newstd);
                                    System.out.println(name + " registered");
                                    print_registrar();
                                    //sc.nextLine();
                                    break;
                                case "D":
                                    System.out.println("Please enter a webid for the student to be deregistered: ");
                                    String name2 = sc.nextLine();
                                    if(database.containsKey(name2)) {
                                        database.remove(name2);
                                        System.out.println(name2 + " deregistered");
                                    }
                                    else{
                                        System.out.println("Error: Could not find student in database");
                                    }

                                    print_registrar();
                                    //sc.nextLine();
                                    break;
                                case "E":
                                    System.out.println("Please enter course: ");
                                    String course = sc.nextLine().toUpperCase();
                                    Set<String> webids= database.keySet();
                                    //List<Student> names = new ArrayList<Student>();
                                    System.out.println("Students registered in " + course);
                                    System.out.println("Student    Semester");
                                    System.out.println("-------------------");
                                    for(String i: webids){
                                        for(int j = 0; j < database.get(i).getCourses().size(); j++){
                                            if(database.get(i).getCourses().get(j).getDepartmentAndNumber().equals(course)){
                                                System.out.println(database.get(i).getWebID() + " " + database.get(i).getCourses().get(j).getDepartmentAndNumber());
                                            }
                                        }
                                    }
                                    print_registrar();
                                    //sc.nextLine();
                                    break;
                                case "L":
                                    System.out.println("Registrar logged out.");
                                    print();
                                    //sc.nextLine();
                                    break;
                            }

                            res_input = sc.nextLine().toUpperCase();
                        }
                        System.out.println("Registrar logged out");



                    }
                    else{
                        if(!database.containsKey(webid)){
                            System.out.println("Could not find User");
                        }
                        else {
                            System.out.println("Welcome " + webid);
                            print_student();
                            String std_input = sc.nextLine().toUpperCase();
                            while (!std_input.equals("L")) {
                                switch (std_input) {
                                    case "A":
                                        System.out.println("Please enter course name:");
                                        String course = sc.nextLine();
                                        int space_idx = 0;
                                        for (int i = 0; i < course.length(); i++) {
                                            if (course.charAt(i) == ' ') {
                                                space_idx = i;
                                            }
                                        }
                                        String depart = course.substring(0, space_idx);
                                        int num = Integer.parseInt(course.substring(space_idx + 1));
                                        System.out.println("Please select a semester: ");
                                        String sem = sc.nextLine();
                                        Course newcourse = new Course(depart, num, sem);
                                        //System.out.print(webid);
                                        database.get(webid).getCourses().add(newcourse);
                                        //System.out.print(webid);
                                        System.out.println(course + " added in " + sem + ".");

                                        print_student();
                                        //sc.nextLine();
                                        break;
                                    case "D":
                                        System.out.println("Please enter course name:");
                                        String course2 = sc.nextLine();
                                        Course rem_course = null;
                                        for (int i = 0; i < database.get(webid).getCourses().size(); i++) {
                                            if (course2.equals(database.get(webid).getCourses().get(i).getDepartmentAndNumber())) {
                                                rem_course = database.get(webid).getCourses().get(i);
                                            }
                                        }
                                        database.get(webid).getCourses().remove(rem_course);
                                        System.out.println(course2 + " dropped from " + rem_course.getSemester());
                                        print_student();
                                        //sc.nextLine();
                                        break;
                                    case "C":
                                        System.out.println("Dept. Course Semester");
                                        System.out.println("-------------------------------");
                                        ArrayList<String> classes = new ArrayList<>();
                                        for (int i = 0; i < database.get(webid).getCourses().size(); i++) {
                                            classes.add(database.get(webid).getCourses().get(i).getDepartmentAndNumber() + " " + database.get(webid).getCourses().get(i).getSemester());
                                        }
                                        CourseNameComparator sorter = new CourseNameComparator();
                                        sorter.sortCourses(classes);
                                        for (int i = 0; i < database.get(webid).getCourses().size(); i++) {
                                            System.out.println(classes.get(i));

                                        }
                                        print_student();
                                        //sc.nextLine();
                                        break;
                                    case "S":
                                        System.out.println("Dept. Course Semester");
                                        System.out.println("-------------------------------");
                                        ArrayList<String> classes2 = new ArrayList<>();
                                        for (int i = 0; i < database.get(webid).getCourses().size(); i++) {
                                            classes2.add(database.get(webid).getCourses().get(i).getDepartmentAndNumber() + " " + database.get(webid).getCourses().get(i).getSemester());
                                        }
                                        SemsterComparator sorter2 = new SemsterComparator();
                                        sorter2.sortCourses(classes2);
                                        for (int i = 0; i < database.get(webid).getCourses().size(); i++) {
                                            System.out.println(classes2.get(i));

                                        }
                                        print_student();
                                        //sc.nextLine();
                                        break;
                                    case "L":
                                        print();
                                        //sc.nextLine();
                                        break;
                                }
                                std_input = sc.nextLine().toUpperCase();
                            }
                            System.out.println(webid + " logged out");
                        }
                    }
                    print();
                    break;
                case "X":
                    try (FileOutputStream fileOut = new FileOutputStream("database.ser");
                         ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                        out.writeObject(database);
                        System.out.println("Serialized data is saved in database.ser");
                    } catch (IOException i) {
                        System.out.println("Error saving the database.");
                        i.printStackTrace();
                    }
                    System.out.println("System state saved, system shut down for maintenance.");
                    print();
                    //sc.nextLine();
                    break;
                case "Q":
                    System.out.println("Good bye, please pick the right SUNY next time!");
                    break;
            }
            input = sc.nextLine().toUpperCase();
        }





        // Load existing data from file

        // Add new student if database is empty
        /*
        if (database.isEmpty()) {
            Student chan = new Student("JChan");
            database.put(chan.getWebID(), chan);
            System.out.println("Added default student: " + chan);
        }

         */
        /*
        // Add new student
        Student sam = new Student("Rsam");
        database.put(sam.getWebID(), sam);
        System.out.println("Added new student: " + sam);

        Student SN = new Student("SNL");
        database.put(SN.getWebID(), SN);

        Student Johnson = new Student("SJohnson");
        database.put(Johnson.getWebID(), Johnson);
        */
        /*
        Student Matthew = new Student("LMatthew");
        database.put(Matthew.getWebID(), Matthew);
        // Serialize the updated database
        try (FileOutputStream fileOut = new FileOutputStream("database.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(database);
            System.out.println("Serialized data is saved in database.ser");
        } catch (IOException i) {
            System.out.println("Error saving the database.");
            i.printStackTrace();
        }

         */



        // Print all students in the database
        //database.forEach((key, value) -> System.out.println(key + " => " + value));
    }
    public static void print(){

        System.out.println("Menu:\n" +
                "L)Login\n" +
                "X)Save state and quit\n" +
                "Q)Quit without saving state.");


    }

    public static void print_registrar(){

        System.out.println(
                "Options:\n" +
                "R) Register a student\n" +
                "D) De-register a student\n" +
                "E) View course enrollment\n"+
                "L) Logout"
        );


    }

    public static void print_student(){

        System.out.println(
                "Options:\n" +
                        "A)Add a class\n" +
                        "D)Drop a class\n" +
                        "C)View your classes sorted by course name/department\n" +
                        "S)View your courses sorted by semester"
        );


    }

}

