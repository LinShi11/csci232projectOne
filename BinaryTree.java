import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    private class Node {

        private Integer node;
        private Node leftChild;
        private Node rightChild;

        public Node(Integer n){
            this.node = n;
            this.leftChild = null;
            this.rightChild = null;
        }

        public void setNode(Integer n){
            this.node = n;
        }

        public void setLeftChild(Node l){
            this.leftChild = l;
        }
        public void setRightChild(Node r){
            this.rightChild = r;
        }

        public Integer getNode(){
            return this.node;
        }

        public Node getLeftChild() {
            return this.leftChild;
        }

        public Node getRightChild() {
            return this.rightChild;
        }
    }

    private Node roots;

    public BinaryTree(){
        this.roots = null;
    }
    public Node getRoots() {
        return this.roots;
    }

    public void setRoots(Node roots){
        this.roots = roots;
    }

    public void insert(int value){
        this.roots = this.recInsertion(roots, value);
    }

    public Node recInsertion(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (node.getNode() > value) {
            node.setLeftChild(this.recInsertion(node.getLeftChild(), value));
            return node;
        }
        else{
            node.setRightChild(this.recInsertion(node.getRightChild(), value));
            return node;
        }
    }

    public void searchRoots(int num){
        System.out.println("The number " + num + " is " + search(roots, num));
    }

    public boolean search(Node node, int num){
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

    public void delete(int num){
        this.roots = deleteNode(roots, num);
    }

    public Node deleteNode(Node node, int num){
        if(search(roots,num) == false){
            System.out.println("The node does not exit");
            return null;
        }
        if(num == node.getNode()) {
            if (node.getRightChild() == null && node.getLeftChild() == null) {
                node = null;
            }
            else if(node.getLeftChild() == null && node.getRightChild() != null){
                node = node.getRightChild();
            }
            else if(node.getRightChild() == null && node.getLeftChild() != null){
                node = node.getLeftChild();
            }
            else{
                int parent =0 ;
                String[] successor = inOrderPrint().split(" ");
                int[] successorInt = new int[successor.length];
                for (int i = 0; i < successor.length; i ++ ) {
                    successorInt[i] = Integer.parseInt(successor[i]);
                }
                for(int i = 0; i < successor.length; i++){
                    if(num == successorInt[i]){
                        parent = successorInt[i+1];
                    }
                }
                this.deleteNode(roots, parent);
                node.node = parent;
            }
            return node;
        }
        else if(num > node.getNode()){
            node.setRightChild(this.deleteNode(node.getRightChild(), num));
            return node;
        }
        else{
            node.setLeftChild(this.deleteNode(node.getLeftChild(), num));
            return node;
        }
    }

    public String list;
    public String inOrderPrint(){
        list = "";
        return(inOrder(roots));
    }


    public String inOrder(Node node){
        if(node == null ){
            return "";
        }
        inOrder(node.leftChild);
        list = (list + node.node + " ");
        inOrder(node.rightChild);
        return list;

    }

    public String preOrderPrint(){
        list = "";
        return(preOrder(roots));
    }

    public String preOrder(Node node){
        if(node == null){
            return "";
        }
        list = (list + node.node + " ");
        preOrder(node.leftChild);
        preOrder(node.rightChild);
        return list;
    }

    public String postOrderPrint(){
        list = "";
        return(postOrder(roots));
    }


    public String postOrder(Node node){
        if(node == null){
            return "";
        }
        postOrder(node.leftChild);
        postOrder(node.rightChild);
        list = (list +node.node + " ");
        return list;
    }

    public int depth;
    public void print() {
        depth = 0;
        printLine(roots);
    }

    public void printLine(Node node){
        if(node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            final int size = queue.size();
            for(int i =0; i < size; i++){
                Node n = queue.remove();
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
                if(n.leftChild != null || n.rightChild != null){
                    depth += 1;
                }
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
