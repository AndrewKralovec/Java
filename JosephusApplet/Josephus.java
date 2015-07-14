import java.util.Scanner;

public class Josephus {

    private Node head;          //initiate our head of the list 
    private Node tail;          //initiate our tail of the list 
    public String nodeToText ; 
    public int numberOfPeople; // number of people in a circle
    public static int skipCount; 	    // number used for counting off
    private static Josephus joe = new Josephus();         // initialize instance of the Josephus methods 
    private static CircleList circle = new CircleList();  // initialize instance of the circelList class  

    public Josephus() {
        numberOfPeople = 0;
        skipCount = 0;
    }// end constructor 
    
    public void Clear(){
    	head = null ; 
    	tail = null ;
    	numberOfPeople = 0 ; 
    	skipCount = 0 ;
    	circle.clearCir(); 
    }// end clear 
    
    public void addPeople() {        // populate the amount of people to kill 
        for (int i = 1; i <= numberOfPeople; i++) {
            circle.addPerson(i);
        }
    }// end addPeople
    
    public String kill() {
    	nodeToText = "" ; 
        tail = null;                  // initiate tail to be null, 
        head = circle.getFirstNode(); // head is the first node 
        while (numberOfPeople != 1) { // Run while until we have one person left 
        	
        	nodeToText = nodeToText + circulateHeadTail() ; 
            circle.removePoeple(head.nodeData);  //remove the node/person who should be killed
            if(tail != null){  // if the tail is null, then we are justing removing people one by one, and don't need the tail to be the next node  
            tail = tail.next;  // then set the tail to the next node ( the node that got removed ) 
            }
            head = head.next;  // same with the head 
            numberOfPeople--;  // minus number of people 
            nodeToText = nodeToText + joe.toString() +"\n " ; 
            
        }
        return nodeToText ; 

    }// end kill 
    
    public String circulateHeadTail() {
    	// loop to set the head and tail to the correct values 
        for (int i = 0; i < skipCount; i++) {
            tail = head;      // tail will be null, but then it will be the highest or last number 
            head = head.next; // head will be the highest or last number until, finally it will be at the end of the loop it will then be the lowest value aka 1. 
            }

        return "--------------------- removed : " + head.nodeData + "\n " ; // killed 

    }// end circulateHeadTail

    public String toString() {
       return "-------------Current people Nodes: ---------- \n" + circle.toString(); 
    } // end display

}