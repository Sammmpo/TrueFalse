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
	
	final static int MAX_QTY = 10;		// This is the maximum number of statements allowed in the database.
	
	private QuestionQueries questionQueries;	// Enables the use of QuestionQueries.java.
	private ArrayList<Question> allQuestions;
	private Question currentQuestion;
	
	static JTable tableQuestion;
	static JButton btnAddQuestion; 
	static JButton btnClear;
	static JButton btnBack;
	
	static DefaultTableModel myQuestionTableModel;


	public DisplayQuestions(){
		super("Database");	// Header text for the window.

		questionQueries = new QuestionQueries();	// Enables the use of QuestionQueries.java.
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null); 
		setBounds(0,0,500,300); 
		setLocationRelativeTo(null); 

		JLabel lblTheseAreMy = new JLabel("Statements in the Database:");	// Text area for guiding the user.
		lblTheseAreMy.setBounds(10, 11, 187, 14);
		getContentPane().add(lblTheseAreMy);
		
		
		// The following creates the table in which the statements are displayed in database format.
		tableQuestion = new JTable();
		tableQuestion.setRowSelectionAllowed(false);
		tableQuestion.setModel(createQuestionTableModel()); // Applying a custom model on top of JTable.
		tableQuestion.setBounds(10, 36, 240, 200);
		getContentPane().add(tableQuestion);
		
		// The following creates the button for adding Statements to the database.
		btnAddQuestion = new JButton("Add Statement");
		btnAddQuestion.setBounds(270, 35, 150, 30);
		getContentPane().add(btnAddQuestion);
		MyEventHandler commandHandler = new MyEventHandler();
		btnAddQuestion.addActionListener(commandHandler);
		
		// The following creates the button for clearing (emptying) the database.
		btnClear = new JButton("Clear");
		btnClear.setBounds(270, 100, 150, 30);
		getContentPane().add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clear Database");
				questionQueries.deleteAllQuestions();	// Self-explanatory, yet relevant.
				tableQuestion.setModel(createQuestionTableModel()); // No need for revalidate/repaint because this method includes recounting.
				populateTable();	// Update changes to the table.
			}
		});
		
		// The following creates the button for returning back to the MainMenu.
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

		populateTable();	// Update changes to the table.

	} // End of DisplayQuestions method.
	
	
	private DefaultTableModel createQuestionTableModel()
	{
		allQuestions = questionQueries.getAllQuestions();

		Object[][] data = new Object[10][2];	// The table size in cells.
		String[] columns = new String[] {"Statement", "Answer"};
		
		for (int row=0; row<allQuestions.size(); row++){	// For loop to fill data cells one by one.
			currentQuestion = allQuestions.get(row);
			data[row][0] = currentQuestion.getStatement();  
			data[row][1] = currentQuestion.getAnswer();  
		}

		myQuestionTableModel = new DefaultTableModel(data, columns)
				{
					@Override
					public boolean isCellEditable(int row, int column)  // To disable cell editing
					{
						return false;
					}
				};
		
		return myQuestionTableModel;
	}
	
	
	private class MyEventHandler implements ActionListener
	{
		public void actionPerformed (ActionEvent myEvent)
		{
			if (myEvent.getSource() == btnAddQuestion){		// This happens when "Add Statements" is clicked.
				if (allQuestions.size() < MAX_QTY){ 		// If there is room for more questions(=statements).
					getNewQuestionFromUser();				// Opens the form pop-up.
					tableQuestion.setModel(createQuestionTableModel());
					populateTable();						// To make sure the table never shows out-dated information.
				}
				else{
					JOptionPane.showMessageDialog(null, "Database is full.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}			
			}
		}
	}
	
	
	private void getNewQuestionFromUser (){		// This is the form used by the user to add custom statements.
		JTextField statementField = new JTextField(10);
		JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Statement:"));		// Guidance for the user.
	    myPanel.add(statementField);
	    myPanel.add(new JLabel("Answer:"));
		
	    JRadioButton trueRadioButton = new JRadioButton("True", true);
	    trueRadioButton.setSelected(true);
	    JRadioButton falseRadioButton = new JRadioButton("False", false);
	    ButtonGroup group = new ButtonGroup();	// Group to prevent selection of both radio buttons simultaneously.
	    group.add(trueRadioButton);
	    group.add(falseRadioButton);
	    myPanel.add(trueRadioButton);
	    myPanel.add(falseRadioButton);
	    
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Add Statement", JOptionPane.OK_CANCEL_OPTION);
	    
	    
	    if (result == JOptionPane.OK_OPTION) {
	    	boolean isTrueSelected = trueRadioButton.isSelected();	// To get a simple value for the query.
		
	    	if (statementField.getText().length() != 0 && statementField.getText().length() <= 10){	// To make sure the field is not left empty or too long.
	    	questionQueries.addQuestion(statementField.getText(), isTrueSelected);	// This adds the user input to the database.
	    	} else {
	    		System.out.println("Invalid Statement Input");
	    		JLabel lblNotification = new JLabel("Has to be 1-10 characters");	// For user guidance.
	    		lblNotification.setBounds(280, 62, 150, 30);
	    		getContentPane().add(lblNotification);
	    		lblNotification.setFont(lblNotification.getFont().deriveFont(10.0f));
	    	}
	    }
	    
	}
	
	private void populateTable()	// This draws the content of the table.
	{		
		allQuestions = questionQueries.getAllQuestions();	// This is an array.
		
		for (int row=0; row<allQuestions.size(); row++){	// Doing it times equal to the array size. Basically drawing all questions in the database.
			currentQuestion = allQuestions.get(row);
			tableQuestion.setValueAt(currentQuestion.getStatement(), row, 0);  // Inputs the column data of current question.
			tableQuestion.setValueAt(currentQuestion.getAnswer(), row, 1);  
		}
	}

} // End of this .java file.


