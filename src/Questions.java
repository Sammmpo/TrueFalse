import javax.swing.JFrame;
import java.awt.GridBagLayout;
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

public class Questions extends JFrame {
	public Questions() {
		setBounds(0, 0, 500, 300);
		setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 5;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		
		JLabel lblStatement = new JLabel("for (int i=0; i<maxID; i++) { SELECT statement, answer FROM question WHERE id=i }");
		panel.add(lblStatement);
		
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Add Question");
				addNewQuestionFromUser();
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 5;
		gbc_btnAdd.gridy = 4;
		getContentPane().add(btnAdd, gbc_btnAdd);
		
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Open Main Menu");
				MainMenu mainMenuWindow = new MainMenu();
				mainMenuWindow.setVisible(true);
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.gridx = 5;
		gbc_btnBack.gridy = 7;
		getContentPane().add(btnBack, gbc_btnBack);
	}
	
	
	
	private void addNewQuestionFromUser (){
		JTextField statementField = new JTextField(10);
	    JRadioButton trueRadioButton = new JRadioButton("True");
	    JRadioButton falseRadioButton = new JRadioButton("False");
	    JPanel myPanel = new JPanel();
	    
	    myPanel.add(new JLabel("Statement:"));
	    myPanel.add(statementField);
	    
	    myPanel.add(new JLabel("Answer:"));
	    myPanel.add(trueRadioButton);
	    myPanel.add(falseRadioButton);
	    
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Insert Question", JOptionPane.OK_CANCEL_OPTION);
	    /*
	    if (result == JOptionPane.OK_OPTION) {
	    	myDB[dbItems] = new SimpleCar2(plateNrField.getText(), colourField.getText(), modelField.getText(), modelYearField.getText()); // Code changed here  	
	    	++dbItems;
	    }
	    */
	}

}
