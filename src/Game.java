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
	int userAnswer;
	
	public Game() {
		setBounds(0, 0, 500, 300);
		setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		
		
		findQuestion();
		

		
		JButton btnTrue = new JButton("True");
		btnTrue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userAnswer = 1;
				checkAnswer();
			}
		});
		GridBagConstraints gbc_btnTrue = new GridBagConstraints();
		gbc_btnTrue.insets = new Insets(0, 0, 5, 0);
		gbc_btnTrue.gridx = 5;
		gbc_btnTrue.gridy = 2;
		getContentPane().add(btnTrue, gbc_btnTrue);
		
		
		
		JButton btnFalse = new JButton("False");
		btnFalse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userAnswer = 0;
				checkAnswer();
			}
		});
		GridBagConstraints gbc_btnFalse = new GridBagConstraints();
		gbc_btnFalse.insets = new Insets(0, 0, 5, 0);
		gbc_btnFalse.gridx = 5;
		gbc_btnFalse.gridy = 4;
		getContentPane().add(btnFalse, gbc_btnFalse);
		
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenu mainMenuWindow = new MainMenu();
				mainMenuWindow.setVisible(true);
				System.out.println("Open Main Menu");
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.gridx = 5;
		gbc_btnBack.gridy = 7;
		getContentPane().add(btnBack, gbc_btnBack);
		
		
	}
	
	private void checkAnswer() {
		// String query = "SELECT answer FROM question WHERE id = count";
		// String result;
		int answer = 1;
		if (userAnswer == answer) {
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
	
	public void findQuestion() {
		String query = "SELECT id FROM question WHERE id=MAX";
		int result = 10;
		int cap = result;
		if (count <= cap){
			JPanel list = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 5;
			gbc_panel.gridy = 1;
			getContentPane().add(list, gbc_panel);
			
	
			// String query = "SELECT statement FROM question WHERE id="+count;
			// String result = query;
			
			JLabel lblStatement = new JLabel("SELECT statement FROM question WHERE id=" + count);
			list.add(lblStatement);
			
			list.revalidate();
			list.repaint();
		} else {
			System.out.println("Game over.");
			JOptionPane.showMessageDialog(null, "Game Over!\nYour total score was: "+points, "Info", JOptionPane.INFORMATION_MESSAGE);
			MainMenu mainMenuWindow = new MainMenu();
			mainMenuWindow.setVisible(true);
			System.out.println("Open Main Menu");
			setVisible(false);
		}
	}

}
