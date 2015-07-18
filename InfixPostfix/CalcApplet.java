package SnQ;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JToolBar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Image;
import java.util.Scanner;

public class CalcApplet<T> extends JApplet {
	// Applet content 
	private JTextField inputField;
	private JScrollPane scrollPane = new JScrollPane();
	private JButton pushStack = new JButton("PUSH");
	private JButton popStack = new JButton("POP");
	private JButton enqueueQueue = new JButton("Enqueue");
	private JButton dequeueQueue = new JButton("Dequeue");
	JButton clearButton = new JButton("Clear");
	JTextArea outputText = new JTextArea();
	JLabel prefixLable = new JLabel("Please enter your in-fix number");

	private static TheQueue queue = new TheQueue();
	private static TheStack stack = new TheStack(); 
	
	private boolean showing = false ;                           // a boolean switch for the mode button
	private final JToolBar toolBar = new JToolBar();            // a toolbar container  for the mode button 
	private final JButton modeButton = new JButton("Stack On"); // mode button to switch back and forth between stack and queue 
	/**
	 * Create the applet.
	 */
	public CalcApplet() {
		
		// Mode Button background 
		modeButton.setIcon(new ImageIcon("Background_stack.png"));
		modeButton.setToolTipText("This Icon shows what mode your are on. Stack or Queue"); // tool tip to show what the mode button  is 
		modeButton.setForeground(Color.BLACK);
		toolBar.add(modeButton);                                                            // attach mode button to toolbar and not the applet panel 
		
		// Stretch the input label for show 
		inputField = new JTextField();
		inputField.setColumns(10);
		
		//start off the layout with right buttons showing 
		enqueueQueue.hide();
		dequeueQueue.hide();
		pushStack.show();
		popStack.show();
 
		// Layout Component 
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
								.addComponent(inputField, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
								.addComponent(prefixLable, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(pushStack)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(popStack)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(enqueueQueue)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(dequeueQueue)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(clearButton))))
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(prefixLable)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(inputField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pushStack)
						.addComponent(popStack)
						.addComponent(enqueueQueue)
						.addComponent(dequeueQueue)
						.addComponent(clearButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
					.addGap(1))
		);
		
		// Add text area 
		scrollPane.setViewportView(outputText);
		getContentPane().setLayout(groupLayout);
		
		// Mode button action listener , to switch between que and stack 
		modeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				outputText.setText("");
				
				if(!showing){
					modeButton.setText("Queue on");
					modeButton.setIcon(new ImageIcon("Background_queue.png"));
					pushStack.hide();
					popStack.hide();
					enqueueQueue.show();
					dequeueQueue.show();
				}
				else {
					modeButton.setText("Stack on");
					modeButton.setIcon(new ImageIcon("Background_stack.png"));
					enqueueQueue.hide();
					dequeueQueue.hide();
					pushStack.show();
					popStack.show();
				}
				showing = !showing ; // set it to the opposite   
			}
		});
	
		// PUSH button action listener 
		pushStack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				getPostFix(); //get postfix  ; 
				outputText.setText(""); // clear the output text area 
				outputText.append("Expression as Infix ");
				outputText.append("\n");
				outputText.append(inputField.getText());  // show express as infix 
				outputText.append("\n");
				outputText.append("Expression as PostFix "); 
				outputText.append("\n");
				outputText.append(stack.getPostfix());    // show express as postfix 
				outputText.append("\n");
				outputText.append("Answer: "+getFinal()); // show Final answer
				
			}
		});
		
		// POP button action listener 
		popStack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				stack.pop();
				outputText.setText(stack.toString());
			}
		});
		
		// Enqueue button action listener 
		enqueueQueue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String [] arrayString = inputField.getText().split("(?!^)"); // Split up the string in case they enter multiple characters 
					for(int i = 0; i < arrayString.length; i++){
						queue.enqueue(arrayString[i]);
					}
				outputText.setText(queue.toString());
			}
		});
		
		// Dequeue button action listener 
		dequeueQueue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				queue.dequeue();
				outputText.setText(queue.toString());
			}
		});
		
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				outputText.setText("");
				stack = new TheStack(); 
			}
		});

	} // END APPLET 
	
	public void getPostFix(){
		String [] arrayString = inputField.getText().split("(?!^)"); // .split("(?!^)") Split up the string in case they enter multiple characters 
		
			for(int i = 0; i < arrayString.length; i++){             // For each character
				if(arrayString[i].toString().equals(".")){           //<----- If its a  float, if it is, make sure to add the decimal  
					stack.StackArrayString(arrayString[i]) ; 
				}
				else if(arrayString[i].matches("[+-/*()^]")){        // <---- If it has an operator , PUSH to stack
					
					if(arrayString[i].toString().equals(")")){       // If ')', pop everything until we get to the beginning (
						while(!stack.peek().toString().equals("(")){ 
							stack.pop();                             // pop it
						}
					}
					while( stack.CheckOperator(stack.peek()) >= stack.CheckOperator(arrayString[i]) ) {  // If the operator ... Check the operator math thing 
						if(stack.peek().toString().equals("(") || arrayString[i].equals("(") ){  // if its ) , we dont care about this, and keep going 
							break ;
						}
						stack.pop();	// pop the previous operator 
					}
					stack.push(arrayString[i]);  //Push the operator on the stack 
					
				} // end if e 
				else {                                    // <--------else  not an operator, then add it to the prefix string 
					stack.StackArrayString(arrayString[i]) ; 
				}
			}// END FOR LOOP
			stack.popAll(); // pop the rest of the stuff 
	}
	
	// Final math method 
	public T getFinal(){
		
		String[]  numberString = new String [10] ; 
		T answer = null ; 
		
		String str = stack.getPostfix();            // Set string to the postfix string 
		String[] splited = str.split("\\s+");       // Split up the string by spaces, to determine the value of each postfix character
		for(int  i = 0 ; i < splited.length ; i++){ // Loop for each postfix character . 
			if(splited[i].matches("[+-/*()^]")){    // If character entered is a operator , do that operator math 
				for(int j = 1 ; j <= 2 ; j++){  
					numberString[j] = stack.pop()+"" ; // Get the two numbers your are doing math with 
				}
				stack.push(doMath(numberString[2],splited[i],numberString[1]));  // The do math method with the two numbers you got out of the loop 
				
			}
			else{ // else push it to the stack 
			stack.push(splited[i]); 
			}
		}
		answer = (T) stack.peek();
		return answer;
	}
			
	public double doMath(String fistPop, String operator, String secondPop){
		//float f = Float.parseFloat("25");
		double firstInt = Double.parseDouble(fistPop) ;
		double secondInt = Double.parseDouble(secondPop);
		
		if(operator.equals("^")){
			return Math.pow(firstInt,secondInt) ;
		}
		else if(operator.equals(")") ){
			return 3 ; 
		}
		else if(operator.equals("(") ){
			return 3 ; 
		}
		else if(operator.equals("*") ){
			return firstInt * secondInt ; 
		}
		else if(operator.equals("/") ){
			return firstInt / secondInt  ; 
		}
		else if(operator.equals("-")){
			return firstInt - secondInt  ; 
		}
		else if(operator.equals("+")){
			return firstInt + secondInt  ; 
		}
		else {
			return 0 ; 
		}
	}
}
