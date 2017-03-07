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
	
	int count = 1;
	int points = 0;
	boolean userAnswer;
	private QuestionQueries questionQueries;
	
	public Game() {
		super("Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null); 
		setBounds(0, 0, 500, 300);
		setLocationRelativeTo(null);
		
		questionQueries = new QuestionQueries();
		
		findQuestion();
		howManyQuestions();

		
		JButton btnTrue = new JButton("True");
		btnTrue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userAnswer = true;
				checkAnswer();
			}
		});
		btnTrue.setBounds(200, 100, 100, 30);
		getContentPane().add(btnTrue);
		
		
		
		JButton btnFalse = new JButton("False");
		btnFalse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userAnswer = false;
				checkAnswer();
			}
		});
		btnFalse.setBounds(200, 140, 100, 30);
		getContentPane().add(btnFalse);
		
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenu mainMenuWindow = new MainMenu();
				mainMenuWindow.setVisible(true);
				System.out.println("Open Main Menu");
				setVisible(false);
			}
		});
		btnBack.setBounds(200, 200, 100, 30);
		getContentPane().add(btnBack);
		
		
	}
	
	private void checkAnswer() {
		boolean correctAnswer = questionQueries.getAnswer(count);
		if (userAnswer == correctAnswer) {
			points++;
			count++;
			System.out.println("Correct. Your score is now: " + points);
			findQuestion();
		} else {
			count++;
			System.out.println("Wrong. Your score is now: " + points);
			findQuestion();
		}
	}
	
	public void howManyQuestions() {
		int cap = questionQueries.getMaxId();
		System.out.println(cap+" questions found in the database");	
	}
	
	public void findQuestion() {
		int cap = questionQueries.getMaxId();
		if (count <= cap){
			JPanel infoPanel = new JPanel();
			infoPanel.setBounds(10, 0, 480, 50);
			getContentPane().add(infoPanel);
			String labelContent2 = "Statement "+count+"/"+cap;
			JLabel lblInfo = new JLabel(labelContent2);
			infoPanel.add(lblInfo);
			infoPanel.revalidate();
			infoPanel.repaint();
			
			JPanel statementPanel = new JPanel();
			statementPanel.setBounds(10, 50, 480, 50);
			getContentPane().add(statementPanel);
			
			String labelContent = questionQueries.getQuestion(count);
			JLabel lblStatement = new JLabel(labelContent);
			statementPanel.add(lblStatement);
			
			statementPanel.revalidate();
			statementPanel.repaint();
		} else {
			System.out.println("Game over.");
			JOptionPane.showMessageDialog(null, "You have answered to all questions!\nCorrect Answers: "+points+"/"+cap, "Game Over", JOptionPane.INFORMATION_MESSAGE);
			MainMenu mainMenuWindow = new MainMenu();
			mainMenuWindow.setVisible(true);
			System.out.println("Open Main Menu");
			setVisible(false);
		}
	}

}
