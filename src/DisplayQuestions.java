import javax.swing.JFrame;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class DisplayQuestions extends JFrame {
	
	final static int MAX_QTY = 10;
	private QuestionQueries questionQueries;
	private ArrayList<Question> allQuestions;
	private Question currentQuestion;
	static JTable tableQuestion;
	static JButton btnAddQuestion; 
	static JButton btnClear;
	static JButton btnBack;
	

	
	public DisplayQuestions(){
		super("Database");

		questionQueries = new QuestionQueries();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null); 
		setBounds(0,0,500,300); 
		setLocationRelativeTo(null); 

		JLabel lblTheseAreMy = new JLabel("Statements in the Database:");
		lblTheseAreMy.setBounds(10, 11, 187, 14);
		getContentPane().add(lblTheseAreMy);
		
		tableQuestion = new JTable();
		tableQuestion.setRowSelectionAllowed(false);
		tableQuestion.setModel(new DefaultTableModel(
			new Object[MAX_QTY][2],
			new String[] {"Statement", "Answer"}
		));
		tableQuestion.setBounds(10, 35, 240, 200);
		getContentPane().add(tableQuestion);
		
		
		btnAddQuestion = new JButton("Add Statement");
		btnAddQuestion.setBounds(270, 35, 150, 30);
		getContentPane().add(btnAddQuestion);
		MyEventHandler commandHandler = new MyEventHandler();
		btnAddQuestion.addActionListener(commandHandler);
		
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(270, 100, 150, 30);
		getContentPane().add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clear Database");
				questionQueries.deleteAllQuestions();
				tableQuestion.setModel(new DefaultTableModel(
					new Object[MAX_QTY][2],
					new String[] {"Statement", "Answer"}
				));
				tableQuestion.revalidate();
				tableQuestion.repaint();
			}
		});
		
		
		btnBack = new JButton("Back");
		btnBack.setBounds(270, 200, 150, 30);
		getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Open Main Menu");
				MainMenu mainMenuWindow = new MainMenu();
				mainMenuWindow.setVisible(true);
				setVisible(false);
			}
		});

		populateTable();

	}
	
	
	
	
	
	private class MyEventHandler implements ActionListener
	{
		public void actionPerformed (ActionEvent myEvent)
		{
			if (myEvent.getSource() == btnAddQuestion){
				if (allQuestions.size() < MAX_QTY){ 
					getNewQuestionFromUser();
					populateTable();
				}
				else{
					JOptionPane.showMessageDialog(null, "Database is full.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}			
			}
		}
	}
	
	
	private void getNewQuestionFromUser (){
		JTextField statementField = new JTextField(10);
		JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Statement:"));
	    myPanel.add(statementField);
	    myPanel.add(new JLabel("Answer:"));
		

	    JRadioButton trueRadioButton = new JRadioButton("True", true);
	    trueRadioButton.setSelected(true);
	    JRadioButton falseRadioButton = new JRadioButton("False", false);

	    ButtonGroup group = new ButtonGroup();
	    group.add(trueRadioButton);
	    group.add(falseRadioButton);

	    myPanel.add(trueRadioButton);
	    myPanel.add(falseRadioButton);
	    
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Add Statement", JOptionPane.OK_CANCEL_OPTION);
	    
	    if (result == JOptionPane.OK_OPTION) {
	    	boolean isTrueSelected = trueRadioButton.isSelected();
	    	
	    	if (statementField.getText().length() != 0 && statementField.getText().length() <= 10){
	    	questionQueries.addQuestion(statementField.getText(), isTrueSelected);
	    	} else {
	    		System.out.println("Invalid Statement Input");
	    		JLabel lblNotification = new JLabel("Has to be 1-10 characters");
	    		lblNotification.setBounds(280, 62, 150, 30);
	    		getContentPane().add(lblNotification);
	    		lblNotification.setFont(lblNotification.getFont().deriveFont(10.0f));
	    	}
	    }
	    
	}
	
	private void populateTable()
	{		
		allQuestions = questionQueries.getAllQuestions();
		
		for (int row=0; row<allQuestions.size(); row++){ 
			
			currentQuestion = allQuestions.get(row);
			
			tableQuestion.setValueAt(currentQuestion.getStatement(), row, 0);  
			tableQuestion.setValueAt(currentQuestion.getAnswer(), row, 1);  
		}
	}

}
