package org.example;

/*
        истема Факультатив. Преподаватель объявляет запись на Курс.
        Студент записывается на Курс, обучается и по окончании
        Преподаватель выставляет Оценку, которая сохраняется в Архиве. Студентов,
        Преподавателей и Курсов при обучении может быть несколько.
 */

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("=== Facultative class work example ===\n");

        Facultative facultative = new Facultative();

        Professor popov = new Professor("Popov");
        Professor gurenko = new Professor("Gurenko");

        Student mark = new Student("Mark");
        Student danil = new Student("Danil");
        Student vlad = new Student("Vlad");
        Student pavel = new Student("Pavel");

        Course math = new Course("Math");
        Course logic = new Course("Logic");
        Course electronics = new Course("Electronics");

        facultative.registerCourse(popov, electronics);
        facultative.registerCourse(gurenko, math);
        facultative.registerCourse(gurenko, logic);

        facultative.assignToCourse(electronics, mark);
        facultative.assignToCourse(electronics, danil);
        facultative.assignToCourse(math, mark);
        facultative.assignToCourse(math, danil);
        facultative.assignToCourse(math, vlad);
        facultative.assignToCourse(math, pavel);
        facultative.assignToCourse(logic, vlad);
        facultative.assignToCourse(logic, pavel);

        facultative.setMark(gurenko, math, mark, 5);
        facultative.setMark(popov, electronics, danil, 4);

        System.out.println("=== Facultative class state ===\n");

        System.out.println(facultative);
    }
}

class Facultative {
    private HashMap<Professor, CourseSet> professorToCoursesLink;
    private HashMap<Course, StudentSet> courseToStudentsLink;
    private HashMap<Course, HashMap<Student, Integer>> archive;

    public Facultative() {
        this.professorToCoursesLink = new HashMap<>();
        this.courseToStudentsLink = new HashMap<>();
        this.archive = new HashMap<>();
    }

    public String toString(){
        String result = "=== Active ===\n";
        for(Professor pf: this.professorToCoursesLink.keySet()) {
            result = String.format("%s %n====> %s", result, pf);
            CourseSet cs_set = this.professorToCoursesLink.get(pf);
            for (NamedEntity cs: cs_set) {
                result = String.format("%s %n==> %s", result, (Course) cs);
                StudentSet ss = this.courseToStudentsLink.get((Course) cs);
                result = String.format("%s %s%n", result, ss);
            }
        }

        result = String.format("%s %n%n %s", result, "=== Archive ===");
        for(Course cs: this.archive.keySet()) {
            result = String.format("%s %n%n==> %s", result, cs);
            for (Student ss:  this.archive.get(cs).keySet()) {
                result = String.format("%s %n%s [%d]", result, ss, this.archive.get(cs).get(ss));
            }
        }
        return result;
    }

    public void registerCourse(Professor professor, Course course) throws Exception {
        CourseSet tmp_cs = this.professorToCoursesLink.getOrDefault(professor, new CourseSet());
        tmp_cs.add(course);
        this.professorToCoursesLink.put(professor, tmp_cs);
        this.courseToStudentsLink.put(course, new StudentSet());
    }

    public void assignToCourse(Course course, Student student) throws Exception {
        if (!this.courseToStudentsLink.containsKey(course)) {
            throw new Exception("Course doesn't exists");
        }
        StudentSet tmp_ss = this.courseToStudentsLink.getOrDefault(course, new StudentSet());
        tmp_ss.addToSet(student);
        this.courseToStudentsLink.put(course, tmp_ss);
    }

    public void setMark(Professor professor, Course course, Student student, Integer mark) throws Exception {
        if (!this.professorToCoursesLink.getOrDefault(professor, new CourseSet()).contains(course)) {
            throw new Exception("No such course assigned to professor!");
        }
        if (!this.courseToStudentsLink.getOrDefault(course, new StudentSet()).contains(student)) {
            throw new Exception("No such student assigned to course!");
        }
        StudentSet tmp_ss = this.courseToStudentsLink.getOrDefault(course, new StudentSet());
        tmp_ss.remove(student);
        this.courseToStudentsLink.put(course, tmp_ss);
        HashMap<Student, Integer> tmp_marks = this.archive.getOrDefault(course, new HashMap<>());
        tmp_marks.put(student, mark);
        this.archive.put(course, tmp_marks);
    }
}

class StudentSet extends NamedEntitySet {
    public StudentSet() {
        super();
    }
}

class ProfessorSet extends NamedEntitySet {
    public ProfessorSet() {
        super();
    }
}

class CourseSet extends NamedEntitySet {
    public CourseSet() {
        super();
    }
}
class NamedEntitySet extends HashSet<NamedEntity> {

    public NamedEntitySet() {
    }

    @Override
    public String toString() {
        String result = "";
        for(NamedEntity elem: this){
            result = String.format("%s %n%s", result, elem);
        }
        return result;
    }

    public boolean contains(NamedEntity entity) {
        return super.contains(entity);
    }

    public void addToSet(NamedEntity entity) throws Exception {
        if (this.contains(entity)) {
            throw new Exception("Object is already in collection");
        }
        super.add(entity);
    }

    public void remove(NamedEntity entity) throws Exception {
        if (!this.contains(entity)) {
            throw new Exception("Object not in collection");
        }
        super.remove(entity);
    }
}

class NamedEntity {

    private static Integer globalIdCounter = 0;
    protected final Integer id;
    protected String name;

    public NamedEntity(String name) {
        this.id = globalIdCounter;
        globalIdCounter++;
        this.name = name;
    }

    public String toString(){
        return String.format("id: %d name: %s", this.id, this.name);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Student extends NamedEntity {

    public Student(String name) {
        super(name);
    }

    @Override
    public String toString(){
        return String.format("<Student> id: %d name: %s", this.id, this.name);
    }
}

class Professor extends NamedEntity {

    public Professor(String name) {
        super(name);
    }

    @Override
    public String toString(){
        return String.format("<Professor> id: %d name: %s", this.id, this.name);
    }
}

class Course extends NamedEntity {

    public Course(String name) {
        super(name);
    }

    @Override
    public String toString(){
        return String.format("<Course> id: %d name: %s", this.id, this.name);
    }
}

