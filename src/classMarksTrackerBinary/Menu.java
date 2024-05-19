package classMarksTrackerBinary;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu{

    // Displays menu
    public static void print(){
        System.out.println("Menu: " +
                "\n1. Display all student data (sorted by mark)" +
                "\n2. Search if mark exists" +
                "\n3. See struggling students" +
                "\n4. Get class average\n");
    }

    // Performs menu actions that user selects
    public static void performAction(int action, int fiftyIndex, StudentList studentList){
        switch(action){
            case 0:
                System.out.println("Goodbye!");
                break;
            case 1:
                studentList.displayAllStudents();
                break;
            case 2:
                int searchedMark = getValidMarkInput();
                searchMark(fiftyIndex, studentList, searchedMark);
                break;
            case 3:
                findStrugglingStudents(fiftyIndex, studentList);
                break;
            case 4:
                System.out.format("Class average: %.1f\n\n", studentList.classAverage());
                break;
            default:
                System.out.println("Invalid action. Input must be in between " + 0 + " and " + 5 + ", inclusive.\n");
        }
    }

    // Searches for mark inputted by user
    private static void searchMark(int fiftyIndex, StudentList studentList, int searchedMark){
        int searchedMarkIndex = studentList.binarySearch(searchedMark);

        if(searchedMarkIndex == -1 || (fiftyIndex !=-1 && searchedMark == 50)){
            System.out.println("No student scored this mark.\n");
        }
        else{
            System.out.println("A student or multiple students scored this mark.\n");
        }
    }

    // Displays list of failing students, ordered by mark (decreasing to increasing)
    private static void findStrugglingStudents(int fiftyIndex, StudentList studentList){
        ArrayList<Student> students = studentList.getList();
        int failedMarkIndex;

        if(fiftyIndex == -1){
            // If a student scored a mark of 50, find index of student with mark of 50
            failedMarkIndex = studentList.binarySearch(50);
        }
        else{
            // If no students scored a mark of 50, use index of the reference student created by the program
            failedMarkIndex = fiftyIndex;
        }

        // If no students have a mark below 50, display that there are no failing students
        if (failedMarkIndex == 0){
            System.out.println("There are no struggling students.");
        }
        // If at least one student has a mark below 50, display failing students
        else{
            System.out.println("Struggling students (name: mark):");
            for(int x=0; x<failedMarkIndex; x++){
                if(students.get(x).getMark() != 50){
                    System.out.println(students.get(x).getName() + ": " + students.get(x).getMark());
                }
            }
        }

        System.out.println();
    }

    // Get valid mark input
    private static int getValidMarkInput(){
        Scanner in = new Scanner(System.in);
        int input = -1;
        boolean isInvalid;

        do{
            isInvalid = false;

            try{
                System.out.print("Mark to search: ");
                input = Integer.parseInt(in.nextLine());

                if(input < 0 || input > 100){
                    isInvalid = true;
                    System.out.println("Mark must be in between 0 and 100, inclusive.\n");
                }
                else if(String.valueOf(input).equals(null)){
                    isInvalid = true;
                    System.out.println("Invalid input. Nothing entered." + "\n");
                }

            } catch (NumberFormatException e){
                System.out.println("Invalid input. Only integers allowed." + "\n");
                isInvalid = true;
            }

        }while(isInvalid);
        return input;
    }
}