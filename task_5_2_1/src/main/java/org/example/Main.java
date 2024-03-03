package org.example;

/*
    Создать классы, спецификации которых приведены ниже.
    Определить конструкторы и методы setТип(), getТип(), toString().
    Определить дополнительно методы в классе, создающем массив объектов.
    Задать критерий выбора данных и вывести эти данные на консоль.

    1. Student: id, Фамилия, Имя, Отчество, Дата рождения, Адрес, Телефон, Факультет, Курс, Группа.
    Создать массив объектов. Вывести:
    a) список студентов заданного факультета;
    b) списки студентов для каждого факультета и курса;
    c) список студентов, родившихся после заданного года;
    d) список учебной группы.

*/


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException {

        System.out.println("=== StudentCollection class example work ===");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        Scanner sc = new Scanner(System.in);

        int m = 0;
        String buf;
        boolean retry_fl = true;

        while(retry_fl) {
            try {
                System.out.println("Enter number of students:");
                buf = sc.next();
                m = Integer.parseInt(buf);
                retry_fl = false;
            } catch (NumberFormatException e) {
                System.out.println("Exception: num format is incorrect!");
            }
        }

        Student[] students = new Student[m];

        for(int i = 0; i < m; i++) {

            boolean global_retry_fl = true;

            while(global_retry_fl) {

                System.out.printf("Enter name of student[%d]:\n", i);
                String name = sc.next();

                System.out.printf("Enter surnmame of student[%d]:\n", i);
                String surname = sc.next();

                System.out.printf("Enter thirdname of student[%d]:\n", i);
                String thirdname = sc.next();

                Date birthdate = dateFormat.parse("1900-01-01");

                retry_fl = true;
                while (retry_fl) {
                    try {
                        System.out.printf("Enter birthdate of student[%d]:\n", i);
                        buf = sc.next();
                        birthdate = dateFormat.parse(buf);
                        retry_fl = false;
                    } catch (ParseException e) {
                        System.out.println("Exception: date format is incorrect!");
                    }
                }

                System.out.printf("Enter address of student[%d]:\n", i);
                String address = sc.next();

                System.out.printf("Enter phone of student[%d]:\n", i);
                String phone = sc.next();

                System.out.printf("Enter faculty of student[%d]:\n", i);
                String faculty = sc.next();

                int course = 1;
                int group = 1;

                retry_fl = true;
                while (retry_fl) {
                    try {
                        System.out.printf("Enter course of student[%d]:\n", i);
                        buf = sc.next();
                        course = Integer.parseInt(buf);
                        retry_fl = false;
                    } catch (NumberFormatException e) {
                        System.out.println("Exception: date format is incorrect!");
                    }
                }

                retry_fl = true;
                while (retry_fl) {
                    try {
                        System.out.printf("Enter group of student[%d]:\n", i);
                        buf = sc.next();
                        group = Integer.parseInt(buf);
                        retry_fl = false;
                    } catch (NumberFormatException e) {
                        System.out.println("Exception: date format is incorrect!");
                    }
                }

                try {
                    students[i] = new Student(i, name, surname, thirdname, birthdate, address, phone, faculty, course, group);
                    global_retry_fl = false;
                } catch (StudentException e) {
                    System.out.printf("Student Exception: %s \n", e);
                }
            }
        }

        StudentCollection st_collection = new StudentCollection(students);

        System.out.println("=== Filter by faculty (SM) ===");

        try {
            System.out.println(st_collection.filterByFaculty("SM"));
        } catch (StudentException e) {
            System.out.printf("Student Exception: %s \n", e);
        }

        System.out.println("=== Filter by faculty (guhihkj) ===");

        try {
            System.out.println(st_collection.filterByFaculty("guhihkj"));
        } catch (StudentException e) {
            System.out.printf("Student Exception: %s \n", e);
        }

        System.out.println("=== Filter by birthyear (1999) ===");

        try {
            System.out.println(st_collection.filterByBirthYear(1999));
        } catch (StudentException e) {
            System.out.printf("Student Exception: %s \n", e);
        }

        System.out.println("=== Filter by birthyear (-1) ===");

        try {
            System.out.println(st_collection.filterByBirthYear(1999));
        } catch (StudentException e) {
            System.out.printf("Student Exception: %s \n", e);
        }

        System.out.println("=== Filter by group (ICS, 5, 2) ===");

        try {
            System.out.println(st_collection.filterByGroup("ICS", 5, 2));
        } catch (StudentException e) {
            System.out.printf("Student Exception: %s \n", e);
        }

        System.out.println("=== Filter by group (biuio, 5, 2) ===");

        try {
            System.out.println(st_collection.filterByGroup("biuio", 5, 2));
        } catch (StudentException e) {
            System.out.printf("Student Exception: %s \n", e);
        }

        System.out.println("=== All by faculty and course ===");

        System.out.println(st_collection.toStringByFacultyAndCourse());
    }
}

class StudentCollection {
    private Student[] data;

    public StudentCollection(Student[] data) {
        this.data = data;
    }

    public String toString() {
        String result = "";
        for(Student elem: this.data) {
            result = String.format("%s%n%n%s", result, elem.toString());
        }
        return result;
    }

    public StudentCollection filterByFaculty(String p_faculty) throws StudentException {
        if (!Student.faculties.contains(p_faculty)){
            throw new StudentException("invalid faculty!");
        }
        Student[] result;
        Student[] filtered = Arrays.stream(this.data)
                .filter(x -> Objects.equals(x.getFaculty(), p_faculty))
                .toArray(Student[]::new);
        return new StudentCollection(filtered);
    }

    public Student[] getData() {

        return this.data;
    }

    public Map<String, Map<Integer, StudentCollection>> getByFacultyAndCourse() {
        Map<String, Map<Integer, StudentCollection>> result = new HashMap<>();
        for (Student elem : this.data) {
            Map<Integer, StudentCollection> default_inner_map = new HashMap<>();

            Student[] tmp_data = result
                    .getOrDefault(
                            elem.getFaculty(),
                            default_inner_map
                    )
                    .getOrDefault(
                            elem.getCourse(),
                            new StudentCollection(new Student[]{})
                    )
                    .getData();

            Student[] new_data = Arrays.copyOf(tmp_data, tmp_data.length + 1);
            new_data[tmp_data.length] = elem;

            Map<Integer, StudentCollection> tmp_inner_map;
            tmp_inner_map = result.getOrDefault(
                    elem.getFaculty(),
                    default_inner_map
            );

            tmp_inner_map.put(elem.getCourse(), new StudentCollection(new_data));
            result.put(elem.getFaculty(), tmp_inner_map);
        }
        return result;
    }

    public String toStringByFacultyAndCourse() {
        String result = "";
        Map<String, Map<Integer, StudentCollection>> tmp = this.getByFacultyAndCourse();
        for(String faculty_key : tmp.keySet()){
            for(int course_key : tmp.get(faculty_key).keySet()){
                result = String.format("%s%n%n=== faculty: %s course: %d ===%n%n%s",
                        result, faculty_key, course_key, tmp.get(faculty_key).get(course_key).toString());
            }
        }
        return result;
    }

    public StudentCollection filterByBirthYear(int year) throws StudentException {
        Student[] result;
        Date date;

        if(year < 0) {
            throw new StudentException("Invalid date!");
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        try {
            date = dateFormat.parse(String.format("%d", year + 1)); // Because we need end of year
        } catch (ParseException e) {
            throw new StudentException("Invalid date!");
        }
        Student[] filtered = Arrays.stream(this.data)
                .filter(x -> x.getBirthdate().after(date))
                .toArray(Student[]::new);
        return new StudentCollection(filtered);
    }

    public StudentCollection filterByGroup(String faculty, int course, int group) throws StudentException {
        Student[] result;
        if (course < 1 || course > 6){
            throw new StudentException("invalid course!");
        }
        if (group < 1){
            throw new StudentException("invalid group!");
        }
        if (!Student.faculties.contains(faculty)){
            throw new StudentException("invalid faculty!");
        }
        Student[] filtered = Arrays.stream(this.data)
                .filter(x -> x.getFaculty().equals(faculty)
                        && x.getCourse() == course
                        && x.getGroup() == group
                )
                .toArray(Student[]::new);
        return new StudentCollection(filtered);
    }

}

class Student {

    private int id;
    private String name;
    private String surname;
    private String thirdname;
    private Date birthdate;
    private String address;
    private String phone;
    private String faculty;
    private int course;
    private int group;
    public static Set<String> faculties = Set.of("ICS", "SM", "MT");

    public Student(int id, String name, String surname, String thirdname, Date birthdate, String address, String phone, String faculty, int course, int group) throws StudentException {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.thirdname = thirdname;
        this.birthdate = birthdate;
        this.address = address;
        this.phone = phone;

        if (!faculties.contains(faculty)){
            throw new StudentException("Ivalid faculty!");
        }
        this.faculty = faculty;
        if (course < 1 || course > 6){
            throw new StudentException("Ivalid course!");
        }
        this.course = course;
        if (group < 1){
            throw new StudentException("Ivalid group!");
        }
        this.group = group;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("Student(id=%d): %s %s %s (%s course: %d groud: %d)\n", this.id, this.name, this.surname, this.thirdname, this.faculty, this.course, this.group) +
                String.format("Birthdate: %s Phone: %s\n", sdf.format(this.birthdate), this.phone) +
                String.format("Address: %s\n", this.address);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getThirdname() {
        return thirdname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getCourse() {
        return course;
    }

    public int getGroup() {
        return group;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setThirdname(String thirdname) {
        this.thirdname = thirdname;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}

class StudentException extends Exception {
    public StudentException(String message) {
        super(message);
    }
}