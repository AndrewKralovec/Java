package SnQ;

import java.util.Arrays;

public class TheQueue<T> {

	   private Node head = null;  // links to first Node in the queue.
	   private Node tail = null;  // links to last Node in the queue.
	   private int queSize = 0; 

	   public void enqueue(T input) {
	      Node newTail = new Node(input);  // A Node to hold the new item.
	      newTail.nodeData = input;
	      if (head == null) {              // If the queue is empty.  The new Node becomes the only node in the list.  
	         head = newTail;
	         tail = newTail;
	      }
	      else {                           //  The new node becomes the new tail of the list.
	         tail.next = newTail;
	         tail = newTail;
	      }
	      queSize++;
	     // System.out.println("enqueue : "+tail);
	   }
	   
	   public Node dequeue() {
	      if ( head == null){
	    System.out.println("Can't dequeue from an empty queue.");
	    	  return null ; 
	      }
	      else{
	      Node firstItem ;
	      firstItem = head;
	      head = head.next;     // The previous second item is now first. If we have just removed the last item, then head is null.
	      if (head == null) {   // The queue has become empty.  The Node that was deleted was the tail and head, so now there is no tail. 

	         tail = null;
	      } 
	      queSize--;
	      return firstItem;
	      }
	   }
	   
	   boolean isEmpty() {
	      return (head == null);
	   }
	   
	   public String toString() {
			 Node temp = head;
			 String queueString = ""; 
		        for (int i = 0; i < queSize;i++) {
		        	queueString += (temp.toString()+ " | ");
		        	temp = temp.next;    // increase node count 
		        }
		        queueString += "\n" ;
		        return queueString ;
		}
	   

	    

}
