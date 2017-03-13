import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Game extends JFrame {
	
	int count = 1;	// This value corresponds to the question table ID in the database, starting at 1.
	int points = 0;	// This is the number of correct answers. Setting it 0 every time the game starts.
	boolean userAnswer; // Variable for user's input "true/false".
	private QuestionQueries questionQueries;  // Enables the usage of QuestionQueries.java.
	
	public Game() {
		super("Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null); 
		setBounds(0, 0, 500, 300);
		setLocationRelativeTo(null);
		
		questionQueries = new QuestionQueries();  // Enables the usage of QuestionQueries.java.
		
		findQuestion(); // Finds the next question from the database.

		// The following creates the button for the user input "True".
		JButton btnTrue = new JButton("True");
		btnTrue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userAnswer = true;
				checkAnswer();	// Checks if the user input matches the correct answer.
			}
		});
		btnTrue.setBounds(200, 100, 100, 30);
		getContentPane().add(btnTrue);
		
		
		// The following creates the button for the user input "False".
		JButton btnFalse = new JButton("False");
		btnFalse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userAnswer = false;
				checkAnswer();
			}
		});
		btnFalse.setBounds(200, 140, 100, 30);
		getContentPane().add(btnFalse);
		
		
		// The following creates the button for quickly returning back to the Main Menu.
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenu mainMenuWindow = new MainMenu();
				mainMenuWindow.setVisible(true);
				System.out.println("Open Main Menu");
				setVisible(false);	// Closes the game window.
			}
		});
		btnBack.setBounds(200, 200, 100, 30);
		getContentPane().add(btnBack);
		
		
	} // Closes "public Game" method.
	
	
	private void checkAnswer() {
		boolean correctAnswer = questionQueries.getAnswer(count);	// Reads the correct answer from the database.
		if (userAnswer == correctAnswer) {	// Checks if the user input matches the correct answer.
			points++;	// Correct answer provides points.
			System.out.println("Correct. Your score is now: " + points);
		} else {	// Otherwise, no points are given.
			System.out.println("Wrong. Your score is now: " + points);
		}
		count++;	// Variable count corresponds to the question IDs in the database, this moves us to the next question.
		findQuestion();
	}
	
	public void findQuestion() {
		int cap = questionQueries.getMaxId();	// There has to be a cap, otherwise it would crash after we run out of questions.
		if (count <= cap){
			JPanel infoPanel = new JPanel();	// Creates the panel in which the game progress is displayed.
			infoPanel.setBounds(10, 0, 480, 50);
			getContentPane().add(infoPanel);
			String labelContent2 = "Statement "+count+"/"+cap;	// For example, 3rd question displays as: "Statement 3/10" (assuming 10 questions in database).
			JLabel lblInfo = new JLabel(labelContent2);
			infoPanel.add(lblInfo);
			infoPanel.revalidate();	// Updates the visual side.
			infoPanel.repaint();
			
			JPanel statementPanel = new JPanel();	// Creates the panel in which the statements are displayed.
			statementPanel.setBounds(10, 50, 480, 50);
			getContentPane().add(statementPanel);
			
			String labelContent = questionQueries.getQuestion(count);	// Queries the content for the statementPanel.
			JLabel lblStatement = new JLabel(labelContent);
			statementPanel.add(lblStatement);
			
			statementPanel.revalidate();	// Updates the visual side.
			statementPanel.repaint();
		} else {
			// The following opens a message dialog box and displays points to the user.
			System.out.println("Game over.");
			JOptionPane.showMessageDialog(null, "You have answered to all questions!\nCorrect Answers: "+points+"/"+cap, "Game Over", JOptionPane.INFORMATION_MESSAGE);
			MainMenu mainMenuWindow = new MainMenu();
			mainMenuWindow.setVisible(true);	// Opens the MainMenu.java.
			System.out.println("Open Main Menu");
			setVisible(false);	// Closes the Game.java.
		}
	}

} // End of this .java file.


