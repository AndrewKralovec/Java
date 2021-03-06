import javax.swing.Spring;

public class Node<T> {

    public T nodeData;  // entry in list 
    public Node next;   // link to next node

    public Node(T tempNode) {
    	this.nodeData = tempNode;
    }
    
    public String toString(){
    	return "node value = " +  this.nodeData;
    }

}
