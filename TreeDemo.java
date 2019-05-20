/**
 * @author Lin Shi
 *
 * This is the driver of the tree class which actually builds the tree
 * This class will read in from a file and split by ","
 * Then I will call the inorder, preorder, and postorder
 * Additionally, there is a user interface of search and delete.  The program will print out
 * a in order list to show after all has been deleted.
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

                //run through this
                for(int i = 0; i< line.length; i++) {
                    tree.insert(Integer.parseInt(line[i]));
                }
            }
            input.close();

        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        System.out.println("Tree:");
        tree.print();
        System.out.println();
        System.out.println("In-order: " + tree.inOrderPrint());
        System.out.println("Pre-order: " + tree.preOrderPrint());
        System.out.println("Post-order: " + tree.postOrderPrint());

        System.out.println();
        Scanner user = new Scanner(System.in);
        while(true) {
            System.out.println("Enter the number to search: (0 to quit) ");
            while (!user.hasNextInt()) {
                System.out.println("Enter the number to search");
                user.nextLine();
            }
            int num = user.nextInt();
            if(num == 0){
                break;
            }
            tree.searchRoots(num);
        }
        while(true) {
            System.out.println("Enter the number to delete (0 to quit): ");
            while(!user.hasNextInt()){
                System.out.println("Enter number to delete:");
                user.nextLine();
            }
            int deleteNum = user.nextInt();
            if(deleteNum == 0){
                break;
            }
            tree.delete(deleteNum);
        }
        System.out.println("In-order: " + tree.inOrderPrint());
    }
}
