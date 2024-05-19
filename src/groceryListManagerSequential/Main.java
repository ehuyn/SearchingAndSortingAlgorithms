package groceryListManagerSequential;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String itemName;
        int action;
        GroceryList listManager = new GroceryList();

        System.out.println("Welcome to the grocery list manager!\n");

        // Display menu
        do{
            System.out.println("Menu: " +
                    "\n1. Search/Add item" +
                    "\n2. Remove Item");
            System.out.println();
            action = getValidIntInput("Select an action (0 to quit): ");
            System.out.println();

            // Perform selected menu actions
            switch(action){
                case 1:
                    itemName = getValidStrInput("Item to search: ");
                    new Search(itemName, listManager);
                    break;
                case 2:
                    itemName = getValidStrInput("Item to remove: ");
                    listManager.removeItem(itemName);
                    break;
                default:
                    if(action!=0){
                        System.out.println("Invalid action. Input must be in between " + 0 + " and " + 2 + ", inclusive.\n");
                    }
            }
            // Until user enters 0 to quit
        } while(action != 0);

        // Display unsorted grocery list
        listManager.displayList("Unsorted");
        // Display sorted grocery list
        listManager.sortGroceryList();
        listManager.displayList("Sorted");
        System.out.println("Goodbye!");
    }

    // Get valid integer input
    private static int getValidIntInput(String prompt){
        Scanner in = new Scanner(System.in);
        int input = -1;
        boolean isInvalid;

        do{
            isInvalid = false;
            try{
                System.out.print(prompt);
                input = Integer.parseInt(in.nextLine());

                if(String.valueOf(input).equals(null)){
                    isInvalid = true;
                    System.out.println("Invalid action. Nothing entered.\n");
                }

            } catch (NumberFormatException e){
                System.out.println("Invalid action. Input must be an integer.\n");
                isInvalid = true;
            }
        } while(isInvalid);
        return input;
    }

    // Get valid string input
    private static String getValidStrInput(String prompt){
        Scanner in = new Scanner(System.in);
        String input;
        boolean isInvalid;

        do{
            isInvalid = false;
            System.out.print(prompt);
            input = in.nextLine().trim();

            if(input.isEmpty()){
                isInvalid = true;
                System.out.println("Invalid input. Nothing entered.\n");
            }

        } while(isInvalid);
        return input;
    }
}
