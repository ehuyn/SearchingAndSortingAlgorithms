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

    // Quick sort algorithm to sort grocery list alphabetically
    public void sortGroceryList(){
        quickSort(groceryList, 0, groceryList.size()-1);
    }

    private void quickSort(ArrayList<Item> arrayList, int left, int right){
        if(left < right){
            int pivotIndex = partition(arrayList, left, right);
            quickSort(arrayList, left, pivotIndex-1);
            quickSort(arrayList, pivotIndex+1, right);
        }
    }

    private int partition(ArrayList<Item> arrayList, int left, int right){
        String pivot = arrayList.get(right).getItemName().toLowerCase();
        int i = left-1;

        for(int j = left; j < right; j++){
            if(arrayList.get(j).getItemName().toLowerCase().compareTo(pivot) <= 0){
                i++;
                swap(arrayList, i, j);
            }
        }
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
