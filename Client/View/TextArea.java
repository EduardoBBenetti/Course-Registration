package Client.View;



import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Text Area class that will be used to display search results and other data.
 * @author Gurmukh Singh, Dillon Sahadevan, Eduardo Benetti
 *
 */
public class TextArea extends JFrame{

	/**
	 * text area of the GUI
	 */
	private JTextArea textArea = new JTextArea();
	
	/**
	 * Constructor for class TextArea.
	 * @param content The data to be displayed
	 * @param name The name of the frame.
	 */
    public TextArea(String content,String name) {
    	super(name);
    	this.setSize(500, 400);
        JPanel textAreaPanel = new JPanel();         // Panel to pack everything together
        
        textArea = new JTextArea(15, 40);		     // text area to print the actual information
        textArea.setText(content);
        textArea.setSize(400, 400);
        textArea.setEditable(false);
       
        JScrollPane scrollPane = new JScrollPane(textArea);  

        this.getContentPane().add(scrollPane);
        textAreaPanel.add(scrollPane, BorderLayout.CENTER);
        
        //adds panel to the CENTER of the frame.
        this.add(textAreaPanel,BorderLayout.CENTER);
        this.setVisible(true);
    }
    
    /**
     * appends given string to the text area
     * @param st String to be displayed
     */
    public void updateText(String st) {
    	textArea.append(st);
    }		
}