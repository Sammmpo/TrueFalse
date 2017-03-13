import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainMenu extends JFrame {
	
	boolean playing = false;	// We are not playing because we are in the MainMenu.
	private QuestionQueries questionQueries; // Enables the usage of QuestionQueries.java.
	private JPanel contentPane;

	public static void main(String[] args) {	// MAIN METHOD. EVERYTHING STARTS HERE.
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu mainMenuWindow = new MainMenu();	// Create new instance of Main Menu.
					mainMenuWindow.setVisible(true);	// Draw Main Menu Window.
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MainMenu() {
		super("Main Menu");	// Header of the window.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null); 
		setBounds(0, 0, 500, 300);	// Size of the window.
		setLocationRelativeTo(null); // Centralize the location.
		
		questionQueries = new QuestionQueries();	// Enables the usage of QuestionQueries.java.
		
		// The following creates the title text.
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(10, 25, 480, 50);
		getContentPane().add(titlePanel);
		String labelContent = "TrueFalse";
		JLabel lblTitle = new JLabel(labelContent);
		lblTitle.setFont(lblTitle.getFont().deriveFont(20.0f));
		titlePanel.add(lblTitle);
		
		// The following creates the space for an error message (not shown by default).
		JPanel errorPanel = new JPanel();
		errorPanel.setBounds(300, 90, 190, 30);
		getContentPane().add(errorPanel);
		String labelContent3 = "No statements in the database.";
		JLabel lblError = new JLabel(labelContent3);
		lblError.setFont(lblError.getFont().deriveFont(10.0f));
		
		// The following creates the Play button.
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (allowPlay() == true){ // Disallows entering Play Mode without statements.
					System.out.println("Open Game");
					Game gameWindow = new Game();
					gameWindow.setVisible(true);	// Opens the Game window.
					setVisible(false);				// Closes the Main Menu window.
				} else {
					System.out.println("No statements in the database");
					errorPanel.add(lblError); // Draws the error message that was left hidden earlier.
					errorPanel.revalidate(); // To update the visual side.
					errorPanel.repaint();
				}
			}
		});
		btnPlay.setBounds(200, 90, 100, 30);
		getContentPane().add(btnPlay);


		
		// The following creates the Database button.
		JButton btnQuestions = new JButton("Database");
		btnQuestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (playing == false) {		// Does not let the user change the database while playing to prevent crashing.
					System.out.println("Open Database");
					DisplayQuestions questionsWindow = new DisplayQuestions();
					questionsWindow.setVisible(true);	// Opens the Database window.
					setVisible(false);
				}
			}
		});
		btnQuestions.setBounds(200, 130, 100, 30);
		getContentPane().add(btnQuestions);
		
		
		// The following creates the Quit button.
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Exit Program");
				System.exit(0);
			}
		});
		btnQuit.setBounds(200, 190, 100, 30);
		getContentPane().add(btnQuit);
	} // Closing bracket for the public MainMenu method.
	
	
	public boolean allowPlay(){	// Checks if the player is allowed to play. Anti-crash mechanism.
		boolean canPlay = false;
		int numberOfQuestions = questionQueries.getMaxId(); // Checks how many questions(=statements) are in the database.
		if (numberOfQuestions > 0) { canPlay = true; } // If there are none, canPlay remains false.
		return canPlay;
	}

} // End of this .java file.


