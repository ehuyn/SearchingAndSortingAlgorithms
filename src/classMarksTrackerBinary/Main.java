package classMarksTrackerBinary;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String studentName;
        int studentMark;
        int action;
        boolean studentWith50Mark = false;
        int fiftyIndex = -1;
        StudentList studentList = new StudentList();

        // Get the number of students
        int studentNum = getValidIntInput("Enter the number of students in your class: ", 1, Integer.MAX_VALUE);
        System.out.println();

        // Get student data (name and mark)
        for(int x = 0; x < studentNum; x++){
            studentName = getValidStrInput("Student " + (x+1) + " Name: ", studentList);
            studentMark = getValidIntInput("Student " + (x+1) + " Mark: ", 0, 100);

            if(studentMark == 50){
                studentWith50Mark = true;
            }

            System.out.println();
            studentList.addStudent(studentName, studentMark);
        }

        // If no students scored a mark of 50
        if(!studentWith50Mark){
            // add an empty student with a mark of 50
            // this will be a reference value for finding struggling students later on
            studentList.addStudent("", 50);
            // Sort array list
            studentList.bubbleSort();
            fiftyIndex = studentList.binarySearch(50);
        }
        // If at least one student scored a mark of 50, sort array list
        else{
            studentList.bubbleSort();
        }

        // Display menu and perform selected actions
        do{
            Menu.print();
            action = getValidIntInput("Select an action (0 to quit): ", Integer.MIN_VALUE, Integer.MAX_VALUE);
            System.out.println();
            Menu.performAction(action, fiftyIndex, studentList);
            // until user selects to quit program
        } while(action != 0);
    }

    // Get valid integer input
    private static int getValidIntInput(String prompt, int min, int max){
        Scanner in = new Scanner(System.in);
        int input = -1;
        boolean isInvalid;

        do{
            isInvalid = false;

            try{
                System.out.print(prompt);
                input = Integer.parseInt(in.nextLine());

                if(input < min && max == Integer.MAX_VALUE){
                    isInvalid = true;
                    System.out.println("Input must be greater than " + min + ".\n");
                }
                else if(input < min || input > max){
                    isInvalid = true;
                    System.out.println("Mark must be in between " + min + " and " + max + ", inclusive.\n");
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

    // Get valid string input
    private static String getValidStrInput(String prompt, StudentList studentList){
        Scanner in = new Scanner(System.in);
        String input;
        boolean isInvalid;

        do{
            isInvalid = false;
            System.out.print(prompt);
            input = in.nextLine().trim();

            if(input.isEmpty()){
                isInvalid = true;
                System.out.println("Please enter the student's name.\n");
            }
            else if(!input.matches("[a-zA-Z ]+")){
                isInvalid = true;
                System.out.println("Student's name cannot contain characters other than letters or spaces.\n");
            }
            else{
                for(Student student : studentList.getList()){
                    if(student.getName().equals(input)){
                        isInvalid = true;
                        System.out.println("Student already exists.\n");
                        break;
                    }
                }
            }

        }while(isInvalid);
        return input;
    }
}