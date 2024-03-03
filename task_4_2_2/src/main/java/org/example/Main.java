package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Students class example ===\n\n");

        Abiturient abiturient = new Zaochnik("Ivan", "IU", "IU-4");

        System.out.println(abiturient.toString());

        System.out.println("\n\n=== student after .changeCafedra(IU-6) ===\n\n");

        abiturient.changeCafedra("IU-6");

        System.out.println(abiturient.toString());
    }
}

interface Abiturient{
    abstract public String toString();

    abstract public void changeFaculty(String s);

    abstract public void changeCafedra(String s);
}

abstract class Student implements Abiturient{

    protected String name;

    protected String faculty;

    protected String cafedra;

    abstract public String toString();

    abstract public void changeFaculty(String s);

    abstract public void changeCafedra(String s);
}

class Zaochnik extends Student {
    public Zaochnik(String name, String faculty, String cafedra){
        this.name = name;
        this.faculty = faculty;
        this.cafedra = cafedra;
    }

    @Override
    public void changeFaculty(String s) {
        this.faculty = s;
    }

    @Override
    public void changeCafedra(String s) {
        this.cafedra = s;
    }

    @Override
    public String toString() {
        return String.format("<Zaochnik> %s %s %s", name, faculty, cafedra);
    }
}