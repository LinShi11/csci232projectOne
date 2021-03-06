/**
 * @author Lin Shi
 * Last Modified: May 19, 2019
 *
 * Overview:
 * This is the driver of the tree class which actually builds the tree
 * This class will read in from a file and split by ","
 * Then I will call the inorder, preorder, and postorder
 * Additionally, there is a user interface of search and delete.  The program will print out
 * a in order list to show after all has been deleted.
 *
 * Instruction:
 * For all user input, I made the assumption that 0 will not be included
 */

/*
import what is needed, such as file and file exception for read in the file
and scanner for both read in the file and used for user interface.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//the driver class
public class TreeDemo {

    // the main function
    public static void main(String[] args){

        //create a instance of the binaryTree class
        BinaryTree tree = new BinaryTree();

        //a try catch to make sure the program knows what to do when input.txt is not found
        try{
            //read in with a scanner from the input.txt
            Scanner input = new Scanner(new File("input.txt"));

            //a while loop to catch it
            while (input.hasNext()){
                // make the input a string array first so it could be split by ","
                String[] line = input.next().split(",");

                //run through this to make all int and insert into the tree
                for(int i = 0; i< line.length; i++) {
                    tree.insert(Integer.parseInt(line[i]));
                }
            }
            // close the file
            input.close();

            //catch the exception and print to tell user
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        // format
        System.out.println("Tree:");
        //print tree in console
        tree.print();
        System.out.println();

        //print the three orders by calling them
        System.out.println("In-order: " + tree.inOrderPrint());
        System.out.println("Pre-order: " + tree.preOrderPrint());
        System.out.println("Post-order: " + tree.postOrderPrint());
        System.out.println();

        //create a scanner for user
        Scanner user = new Scanner(System.in);
        //a continuous loop
        while(true) {
            //ask the user
            System.out.println("Enter the number to search: (0 to quit) ");
            //will catch all non-int and ask the user to reenter
            while (!user.hasNextInt()) {
                user.next();
                user.nextLine();
                System.out.println("Enter the number to search");
            }

            //catch the int
            int num = user.nextInt();
            // check to make sure it is not 0, which will exit
            if(num == 0){
                break;
            }
            //search for the root
            tree.searchRoots(num);
        }

        //continuous loop for delete
        while(true) {
            //ask
            System.out.println("Enter the number to delete (0 to quit): ");
            while(!user.hasNextInt()){
                user.next();
                user.nextLine();
                System.out.println("Enter number to delete:");
            }
            int deleteNum = user.nextInt();
            if(deleteNum == 0){
                break;
            }
            // call the delete
            tree.delete(deleteNum);
        }
        //will print the in-order again in the end
        System.out.println("In-order: " + tree.inOrderPrint());
        tree.print();
    }
}
