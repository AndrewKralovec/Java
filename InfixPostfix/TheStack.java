package SnQ;


import java.util.Arrays;

public class TheStack<T> {
	private int stackSize ; 
    public Node top ;   // link to the Node that is at the top 
    private String stackArray="" ;  

	// push/add method
	public void push(T input){

	      Node newTop = new Node(input);     // A Node to hold the new item.
	      newTop.nodeData = input;           // Store input in the new Node.
	      newTop.next = top;                 // The new Node points to the old top.
	      top = newTop;                      // The new item is now on top.
	      stackSize++ ;   
	      stackArray += " " ;                // Add a space, to keep track of the numbers that should be next to each other 
		} 
	
	public void StackArrayString(T input){
		stackArray += input.toString() ; 
	}
	//pop/remove method
	public T pop(){
		if ( top == null ){
	         System.out.println("Can't pop from an empty stack.");
	         return null ; 
		}
		else{
	      T topItem = (T) top.nodeData;  // The item that is being popped.
	   
	      if(topItem.toString().equals(")") || topItem.toString().equals("(")){
	    	  stackArray += " " ; 
	      }
	      else{
	      stackArray += " "+topItem.toString() ; 
	      }
	      top = top.next;                // The previous second item is now on top.
	      stackSize-- ; 
	      return topItem;
	      
		}
	}
	
	 public boolean isEmpty() {
	      return top == null;
	   }
	 public boolean isNull() {
	      return pop() == null;
	   }
	 
	// check operator method 
		 public int CheckOperator(T input){
			 
			 if(input == null){
				 
				 return 0 ; 
			 }
			if(input.toString().equals("^")){
				return 5 ;
			}
			else if(input.toString().equals(")")){
				return 7 ; 
			}
			else if(input.equals("/")){
				return 3 ; 
			}
			else if(input.toString().equals("*")  ){
				return 4 ; 
			}
			else if(input.equals("-")){
				return 1 ; 
			}
			else if(input.toString().equals("+") ){
				return 2 ; 
			}
			else if(input.toString().equals("(") ){
				return 6 ; 
			}
			else {
				return 10 ; // there will be a null at first start, 
			}
		 }
	 
	public T peek(){
		return (T) top ; 
	}
	
	public void popAll(){
		 for(int i = -1; i <= stackSize; i++){ // Start at -1 
			 pop();
		 }
	}
	
	public String toString() {
		 Node temp = top;
		 String stackString = ""; 
	        for (int i = 0; i < stackSize;i++) {
	        	stackString += (temp.toString()+ " | ");
	        	temp = temp.next;                       // increase node count 
	        }
	        stackString += "\n" ;
	        return stackString ;
	}
	
	public String getPostfix(){
		return stackArray; 
	}
	
	
}
