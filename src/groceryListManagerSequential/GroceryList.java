package groceryListManagerSequential;

import java.util.ArrayList;

public class GroceryList{
    private static ArrayList<Item> groceryList = new ArrayList<Item>();

    public void addToGroceryList(String itemName){
        Item item = new Item(itemName);
        groceryList.add(item);
        System.out.println(itemName + " was added to your grocery list.\n");
    }

    public void removeItem(String itemName){
        for(Item item : groceryList){
            if (item.getItemName().equalsIgnoreCase(itemName)){
                groceryList.remove(item);
                System.out.println(itemName + " was removed from your grocery list.\n");
                return;
            }
        }
        System.out.println(itemName + " is not on your grocery list.\n");
    }

    public void displayList(String sortType){
        if(groceryList.isEmpty()){
            System.out.println("Your grocery list is now empty.\n");
        }
        else{
            System.out.println(sortType + " Grocery List:");
            for(Item item : groceryList){
                System.out.println("- "+ item.getItemName());
            }
            System.out.println();
        }
    }

    public void sortGroceryList(){
        quickSort(groceryList, 0, groceryList.size()-1);
    }

    // Quick sort algorithm to sort grocery list alphabetically
    private void quickSort(ArrayList<Item> arrayList, int left, int right){
        // Check if array list needs sorting
        if(left < right){
            int pivotIndex = partition(arrayList, left, right);
            // Sort elements before and after pivot
            quickSort(arrayList, left, pivotIndex-1);
            quickSort(arrayList, pivotIndex+1, right);
        }
    }

    private int partition(ArrayList<Item> arrayList, int left, int right){
        // Initially select pivot item from right end of list section
        String pivot = arrayList.get(right).getItemName().toLowerCase();

        // Marks the boundary between elements less than or equal to the pivot and elements greater than the pivot
        int i = left-1;

        // Iterate through the array from left to right and check position of each item
        for(int j = left; j < right; j++){
            // If current item is "less than or equal" to the pivot item
            if(arrayList.get(j).getItemName().toLowerCase().compareTo(pivot) <= 0){
                i++;
                // move to the left partition
                swap(arrayList, i, j);
            }
        }
        // Place the pivot element at the correct position (after items that are "less than or equal" to pivot)
        swap(arrayList, i+1, right);
        return i+1;
    }

    private void swap(ArrayList<Item> arrayList, int i, int j){
        Item temp = arrayList.get(i);
        arrayList.set(i, arrayList.get(j));
        arrayList.set(j, temp);
    }

    public ArrayList<Item> getGroceryList(){
        return new ArrayList<Item>(groceryList);
    }
}
