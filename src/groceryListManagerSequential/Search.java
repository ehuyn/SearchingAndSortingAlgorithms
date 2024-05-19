package groceryListManagerSequential;

import java.util.Scanner;

public class Search{
    private String searchItem;

    public Search(String searchItem, GroceryList listManager){
        this.searchItem = searchItem;
        search(listManager);
    }

    // Search for item in grocery list, sequentially (one by one)
    private void search(GroceryList listManager){
        for(Item item : listManager.getGroceryList()){
            if (searchItem.equalsIgnoreCase(item.getItemName())){
                System.out.println(searchItem + " is on your grocery list.\n");
                return;
            }
        }
        System.out.println(searchItem + " is not on your grocery list.");
        askIfAdd(listManager);
    }

    // Ask if user wants to add item to grocery list, given that the item does not already exist in the list
    private void askIfAdd(GroceryList listManager){
        String response = validateConfirmationInput();

        if(response.equals("Y")){
            listManager.addToGroceryList(searchItem);
        }
        else{
            System.out.println();
        }
    }

    // Confirm if user wants to add item to grocery list
    private String validateConfirmationInput(){
        Scanner in = new Scanner(System.in);
        String input;
        boolean isInvalid;

        do{
            isInvalid = false;
            System.out.print("Would you like to add this item to your grocery list (Y/N)? ");
            input = in.nextLine().trim();

            if(!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N")){
                System.out.println("Please confirm or deny by entering 'Y' or 'N'.");
                isInvalid = true;
            }
        } while(isInvalid);
        return input.toUpperCase();
    }
}