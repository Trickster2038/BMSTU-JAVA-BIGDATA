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

        StudentCollection st_collection = new StudentCollection(new Student[]{
                new Student("Sergey", "Astakhov", "Victorovich", dateFormat.parse("2001-01-01"), "Balashiha" , "+7-900-000-00-00", "ICS", 5, 2),
                new Student("Mark", "Khabarov", "X.", dateFormat.parse("2001-05-01"), "Fryazino" , "+7-900-000-00-01", "ICS", 5, 2),
                new Student("Nina", "Gendina", "X.", dateFormat.parse("2000-07-01"), "Moscow" , "+7-900-000-00-02", "ICS", 5, 2),
                new Student("Ivan", "Ivanov", "X.", dateFormat.parse("1999-05-01"), "Moscow" , "+7-900-000-00-03", "SM", 3, 1),
                new Student("Boris", "Ivanov", "X.", dateFormat.parse("2001-05-02"), "Moscow" , "+7-900-000-00-04", "SM", 3, 1)
        });

        System.out.println("=== Filter by faculty (SM) ===");

        System.out.println(st_collection.filterByFaculty("SM"));

        System.out.println("=== Filter by birthyear (1999) ===");

        System.out.println(st_collection.filterByBirthYear(1999));

        System.out.println("=== Filter by group (ICS, 5, 2) ===");

        System.out.println(st_collection.filterByGroup("ICS", 5, 2));

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

    public StudentCollection filterByFaculty(String p_faculty) {
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

    public StudentCollection filterByBirthYear(int year) throws ParseException {
        Student[] result;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = dateFormat.parse(String.format("%d", year + 1)); // Because we need end of year
        Student[] filtered = Arrays.stream(this.data)
                .filter(x -> x.getBirthdate().after(date))
                .toArray(Student[]::new);
        return new StudentCollection(filtered);
    }

    public StudentCollection filterByGroup(String faculty, int course, int group) throws ParseException {
        Student[] result;
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
    private String name;
    private String surname;
    private String thirdname;
    private Date birthdate;
    private String address;
    private String phone;
    private String faculty;
    private int course;
    private int group;

    public Student(String name, String surname, String thirdname, Date birthdate, String address, String phone, String faculty, int course, int group) {
        this.name = name;
        this.surname = surname;
        this.thirdname = thirdname;
        this.birthdate = birthdate;
        this.address = address;
        this.phone = phone;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("Student: %s %s %s (%s course: %d groud: %d)\n", name, surname, thirdname, faculty, course, group) +
                String.format("Birthdate: %s Phone: %s\n", sdf.format(birthdate), phone) +
                String.format("Address: %s\n", address);
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