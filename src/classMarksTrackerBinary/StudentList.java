package classMarksTrackerBinary;

import java.util.ArrayList;

public class StudentList{
    private ArrayList<Student> students;
    private boolean addedFiftyForSearch;
    private double markSum;

    public StudentList(){
        this.students = new ArrayList<Student>();
        this.markSum = 0;
        this.addedFiftyForSearch = false;
    }

    public void addStudent(String name, int mark){
        Student student = new Student(name, mark);
        students.add(student);
        markSum += mark;
        if(name.equals("")){
            addedFiftyForSearch = true;
        }
    }

    public void displayAllStudents(){
        System.out.println("Student Data (name: mark):");
        for(Student student : students){
            if(!student.getName().equals("")){
                System.out.println(student.getName() + ": " + student.getMark());
            }
        }
        System.out.println();
    }

    // Sort students in array list by mark (decreasing to increasing)
    public void bubbleSort(){
        int studentNum = students.size();
        for(int x = 0; x<studentNum-1; x++){
            for(int y = 0; y<studentNum-x-1; y++){
                if(students.get(y).getMark() > students.get(y+1).getMark()){
                    // Bubble swap student positions
                    Student student = students.get(y);
                    students.set(y, students.get(y+1));
                    students.set(y+1, student);
                }
            }
        }
    }

    // Search for marks in array list
    public int binarySearch(int markForSearch){
        int low = 0, high = students.size() - 1;
        int mid;

        while (low <= high) {
            mid = low + (high - low) / 2;

            // Check if searched mark is present at middle
            if (students.get(mid).getMark() == markForSearch){
                return mid;
            }
            // If searched mark is greater, ignore left half
            if (students.get(mid).getMark() < markForSearch){
                low = mid + 1;
            }
            // If searched mark is smaller, ignore right half
            else{
                high = mid - 1;
            }
        }
        // At this point, element was not found in the array list
        return -1;
    }

    public double classAverage(){
        int subtractVal = 0;
        int subtractIndex = 0;

        // If empty reference student (with a mark of 50) was created, remove it from calculations
        if(addedFiftyForSearch){
            subtractVal = 50;
            subtractIndex = 1;
        }

        return (double)((markSum-subtractVal)/(students.size()-subtractIndex));
    }

    public ArrayList<Student> getList(){
        return new ArrayList<Student>(students);
    }
}