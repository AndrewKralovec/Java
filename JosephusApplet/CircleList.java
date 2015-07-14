
public class CircleList<T> {
	
private int nodeCount;    // total items in the list
private Node firstNode;   // fist node link 
private Node lastNode;    // last node link 
private Node currentNode; //current node link 
public String toText ;


public CircleList currentCirlce;

	    public CircleList() {
	    	firstNode = null;
	    	lastNode = null;
	    	currentNode = null;
	    	nodeCount = 0;
	    }// end circle 
	    
	    public void clearCir(){
	    	firstNode = null;
	    	lastNode = null;
	    	currentNode = null;
	    	nodeCount = 0;
	    } // end clear 

	    public boolean isEmpty() {            // if empty return
	        return firstNode == null;
	    }
	    
	    // we technically are not adding anyone, We are just making sure the first node is the first entry  and the last node is last entry 
	    public void addPerson(T people) {
	        Node tempNode = new Node(people); // the node added by the user 
	        
	        if (isEmpty()) {     
	            firstNode = tempNode;         // if empty set first node to 1, aka the first node    
	            currentNode = firstNode;      // the current node will also be the first nod e
	        } else {
	            currentNode.next = tempNode;  // if its not empty , current node is equal to the node ( of the people ) ; 
	        }
	        
	        tempNode.next = firstNode;       // this way tempNode.next will always be equal to 1, aka the first Node 
	        lastNode = tempNode;             // this will make the last node in the list equal to the most current/ last node added      
	        skip();                          // resetting current node to 1, aka the first node 
	        nodeCount++;                     // adding to the node count  
	    }
	    
	    public void removePoeple(T nodeData) {
	    	// if removing, Initalzie them to the first two nodes 
	        Node previous = firstNode;
	        Node current = firstNode.next;
 
	        while (current.nodeData != nodeData) { // set the previous to be the one before the person removed and the current to the person remvoed 
	        	previous = current;              
	        	current = current.next;
	        } 

	        if (nodeCount == 1) {              // if we have only one left we have an empty list 
	        	isEmpty();            
	        } else if (current == firstNode) { // When the current is equal to the first we are back at the start  of the list, 
	        	previous.next = current.next;
	            firstNode = current.next;      // set new first node of the list 
	        } else {                           // if not then we are just going to the next node 
	        	previous.next = current.next;	        	
	        }
	        nodeCount--; // we removed someone , remove them from the count . 

	    }
	    
	    public Node getFirstNode() {                   // becuase java wont let me just access it  it from the class 
	        return firstNode;
	    }
	    
	    public String toString() {
	        Node temp = firstNode;
	        toText = "" ; 
	        
	        for (int i = 0; i < nodeCount;i++) {
	        	toText = toText + temp.toString() + "\n" ;
	        	temp = temp.next;                       // increase node count 
	        }
	        return toText; 

	    }
	    
	    public void skip() {
	    	currentNode = currentNode.next;
	    }
	    
}
