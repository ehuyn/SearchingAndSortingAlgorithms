package classMarksTrackerBinary;

public class Student{
    private String name;
    private int mark;

    public Student(String name, int mark){
        this.name = name;
        this.mark = mark;
    }

    public String getName(){
        return name;
    }

    public int getMark(){
        return mark;
    }
}