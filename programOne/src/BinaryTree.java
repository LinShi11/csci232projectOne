/**
 * @author Lin Shi
 * Last Modified: May 19, 2019
 *
 * Overview:
 * This is the class which the tree is created
 * it includes inserting the nodes, deleting nodes, search for node, and print the tree
 * had to include linkedlist and queue to use for the print tree
 *
 */

//import everything that is needed
import java.util.LinkedList;
import java.util.Queue;

// the class for binary tree
public class BinaryTree {

    // create a private class of nodes
    private class Node {

        //declare the types of them
        private Integer node;
        private Node leftChild;
        private Node rightChild;

        //constructor
        public Node(Integer n){
            this.node = n;
            this.leftChild = null;
            this.rightChild = null;
        }

        //have a method to set node
        public void setNode(Integer n){
            this.node = n;
        }

        //set left child
        public void setLeftChild(Node l){
            this.leftChild = l;
        }

        // set right child
        public void setRightChild(Node r){
            this.rightChild = r;
        }

        // will return the node
        public Integer getNode(){
            return this.node;
        }

        // return the left child
        public Node getLeftChild() {
            return this.leftChild;
        }

        // return the right child
        public Node getRightChild() {
            return this.rightChild;
        }
    }

    // create a private type of node roots
    private Node roots;

    //construct the binary tree when first called
    public BinaryTree(){
        this.roots = null;
    }

    //get the roots
    public Node getRoots() {
        return this.roots;
    }

    //set the roots
    public void setRoots(Node roots){
        this.roots = roots;
    }

    // a insert method that could be called from the demo class
    public void insert(int value){
        //will call the insert method in this class
        this.roots = this.recInsertion(roots, value);
    }

    //will do the actual insertion
    public Node recInsertion(Node node, int value) {
        // simply return with the type of node of value if the tree is blank
        if (node == null) {
            return new Node(value);
        }
        // compare the node and the value you want to insert
        if (node.getNode() > value) {
            // call the left child when the node you want insert is less than the node you compare it with
            node.setLeftChild(this.recInsertion(node.getLeftChild(), value));
            return node;
        }
        // since we could assume that there is no duplicate of nodes, the other ones will be greater than and falls under this
        else{
            node.setRightChild(this.recInsertion(node.getRightChild(), value));
            return node;
        }
    }

    // the search method which could be called from driver
    public void searchRoots(int num){
        // format so user could see what they tried to search
        System.out.println("The number " + num + " is " + search(roots, num));
    }

    // called in this class
    public boolean search(Node node, int num){

        /*
        compare the number and the node, and whether or not they have a child towards that direction
        if they do, it will continue running
        once it finds a node that is the same as the number, it will return true
        otherwise, the default is to return false
         */
        if(num > node.node && node.rightChild != null){
            return search(node.getRightChild(), num);
        }
        else if(num < node.node && node.leftChild != null){
            return search(node.getLeftChild(), num);
        }
        if(num == node.node){
            return true;
        }
        else {
            return false;
        }
    }

    // the delete method that is called from the driver
    public void delete(int num){
        // call the delete method
        this.roots = deleteNode(roots, num);
    }

    // the method to delete the nodes
    public Node deleteNode(Node node, int num){
        // check using the search method to make sure the node exist, if not it will print a statement
        if(search(roots,num) == false){
            System.out.println("The node does not exit");
            return null;
        }
        //when the number is equal to the node
        if(num == node.getNode()) {
            // check the right and left child
            if (node.getRightChild() == null && node.getLeftChild() == null) {
                //when both of them does not exit, make the node null
                node = null;
            }
            else if(node.getLeftChild() == null && node.getRightChild() != null){
                //when right child is not empty but the left is, make the node equal to the right child
                node = node.getRightChild();
            }
            else if(node.getRightChild() == null && node.getLeftChild() != null){
                //when left is not empty but the right is, make the node equal to the left
                node = node.getLeftChild();
            }
            else{ // everything else falls under two children
                int parent =0 ; // a temper holder
                String[] successor = inOrderPrint().split(" "); // since inorder returns a string, split it and make it string array
                int[] successorInt = new int[successor.length]; // create a int array with the same size
                //a for loop to get all elements from string to int
                for (int i = 0; i < successor.length; i ++ ) {
                    successorInt[i] = Integer.parseInt(successor[i]);
                }
                //another for loop to check for the node
                for(int i = 0; i < successor.length; i++){
                    if(num == successorInt[i]){
                        parent = successorInt[i+1]; // get the successor
                    }
                }
                //run the delete again but delete the place of the successor
                this.deleteNode(roots, parent);
                node.node = parent; // make the original node the value of the successor
            }
            return node;
        }
        // when the number is not equal, it will compare and move right or left
        else if(num > node.getNode()){
            node.setRightChild(this.deleteNode(node.getRightChild(), num));
            return node;
        }
        else{
            node.setLeftChild(this.deleteNode(node.getLeftChild(), num));
            return node;
        }
    }

    // declare a string
    public String list;

    //method that is called from driver
    public String inOrderPrint(){
        list = ""; //make the list blank
        return(inOrder(roots)); // calls another method
    }

    public String inOrder(Node node){
        //make sure it is not empty
        if(node == null ){
            return "";
        }
        // do all the left first and add to the string list
        inOrder(node.leftChild);
        list = (list + node.node + " ");
        inOrder(node.rightChild);
        return list;
    }

    // the pre-order method called from the driver
    public String preOrderPrint(){
        list = ""; // make it blank
        return(preOrder(roots));
    }

    public String preOrder(Node node){
        if(node == null){
            return "";
        }
        //add all to the string list and then do left and right
        list = (list + node.node + " ");
        preOrder(node.leftChild);
        preOrder(node.rightChild);
        return list;
    }

    // called from the driver
    public String postOrderPrint(){
        list = "";//make it empty
        return(postOrder(roots));
    }

    public String postOrder(Node node){
        if(node == null){
            return "";
        }
        //do both left and right then add to string list
        postOrder(node.leftChild);
        postOrder(node.rightChild);
        list = (list +node.node + " ");
        return list;
    }

    // create a depth
    public int depth;

    public void print() {
        depth = 0; // initialize the depth to be 0
        printLine(roots);
    }

    public void printLine(Node node){
        //simply return when it is empty
        if(node == null){
            return;
        }
        // create a queue that will add then use it to print
        Queue<Node> queue = new LinkedList<>();
        queue.add(node); // add the node
        while(!queue.isEmpty()){ // while it is not empty
            final int size = queue.size(); // find the size of it
            for(int i =0; i < size; i++){
                Node n = queue.remove();
                // format based on the level of leaf
                if(depth == 0) {
                    System.out.print("\t\t\t\t\t\t(" + n.node + ")");
                }
                else if(depth == 1){
                    System.out.print("\t\t\t\t\t(" + n.node+ ")");
                }
                else if(depth == 2){
                    System.out.print("\t\t\t(" + n.node + ")");
                }
                else if(depth == 3){
                    System.out.print("\t\t(" + n.node + ")");
                }
                else if(depth == 4){
                    System.out.print("\t(" + n.node + ")");
                }
                else{
                    System.out.print("(" + n.node + ")");
                }
                //add depth when either left or right is not empty
                if(n.leftChild != null || n.rightChild != null){
                    depth += 1;
                }

                // call left or right when it is not empty
                if(n.leftChild != null) {
                    queue.add(n.leftChild);
                }
                if(n.rightChild != null){
                    queue.add(n.rightChild);
                }
            }
            System.out.println();
        }
    }

}
