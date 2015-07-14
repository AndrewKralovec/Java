import javax.swing.JApplet;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class JoeApplet extends JApplet {
	private JTextField numberOfPeople_field;
	private JTextField skipCount_field;
	private JTextArea outputText ; 
    private static Josephus joe = new Josephus();         // initialize instance of the Josephus methods 
    private static CircleList circle = new CircleList();  // initialize instance of the circelList class  
    private JScrollPane scrollPane;

	/**
	 * Create the applet.
	 */
	public JoeApplet() {
		getContentPane().setBackground(Color.LIGHT_GRAY); // panel , light gry so i can see the scroll pane 
		
		JLabel numberOfPeople_label = new JLabel("How many people need to be killed?");
		
		numberOfPeople_field = new JTextField();
		numberOfPeople_field.setForeground(Color.BLACK);
		numberOfPeople_field.setColumns(10);
		
		JLabel skipCount_label = new JLabel("How many people do we skip each time?");
		
		skipCount_field = new JTextField();
		skipCount_field.setColumns(10);
		
		outputText = new JTextArea();

		
		JButton sortButton = new JButton("start");
		
		sortButton.addActionListener(new ActionListener() {
		    // This is the new ActionPerformed Method.
		    // It catches any events with an ActionListener attached.
		    // Using an if statement, we can determine which button was pressed
		    // and change the appropriate values in our GUI.
			public void actionPerformed(ActionEvent arg0) {
	            String numberOfPeople_string = numberOfPeople_field.getText();
	            String skipCount_string = skipCount_field.getText();
	            
	            if(numberOfPeople_field.getText().equals("")){
	            	 outputText.setText("First field is empty");
	            }
	            if(skipCount_field.getText().equals("")){
	            	outputText.setText("Second field is empty");
	            }
	            if(numberOfPeople_field.getText().equals("") && skipCount_field.getText().equals("")){
	            	outputText.setText("First and second field is empty");
	            }

	            int numberOfPeople = Integer.parseInt(numberOfPeople_string);
	            int skipCount = Integer.parseInt(skipCount_string);
	            
	            outputText.setText("");              // we are all set to start the program 
	            joe.numberOfPeople = numberOfPeople; //set the number of people to the player input
	            joe.addPeople();                     //add the people method 
	            skipCount -= 1 ;                     // minus one because its inclusive counting . Minus -1, to make it start counting from 0 
	       	    joe.skipCount = skipCount ;          //set the number of people that we skip to the player input  
	       	    outputText.append(joe.toString());   //Show the current nodes 
	       	    outputText.append(joe.kill());       // kill method 
	       	    joe.Clear();
	       	    
	       	    

			}
		});
		
		scrollPane = new JScrollPane(); 
		scrollPane.setViewportView(outputText); // so it scrolls 
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(numberOfPeople_field)
							.addComponent(numberOfPeople_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(skipCount_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(skipCount_field))
						.addComponent(sortButton))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(numberOfPeople_label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(numberOfPeople_field, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(skipCount_label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(skipCount_field, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sortButton)
					.addGap(28)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		getContentPane().setLayout(groupLayout);  // set pane layout to our group layout 

	}
}
