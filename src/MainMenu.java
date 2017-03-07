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
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainMenu extends JFrame {
	
	boolean playing = false;
	int count = 1;
	int points = 0;
	private QuestionQueries questionQueries;

	private JPanel contentPane;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu mainMenuWindow = new MainMenu();
					mainMenuWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MainMenu() {
		super("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null); 
		setBounds(0, 0, 500, 300);
		setLocationRelativeTo(null);
		
		questionQueries = new QuestionQueries();

		
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(10, 25, 480, 50);
		getContentPane().add(titlePanel);
		String labelContent = "TrueFalse";
		JLabel lblTitle = new JLabel(labelContent);
		lblTitle.setFont(lblTitle.getFont().deriveFont(20.0f));
		titlePanel.add(lblTitle);
		
		JPanel errorPanel = new JPanel();
		errorPanel.setBounds(300, 90, 190, 30);
		getContentPane().add(errorPanel);
		String labelContent3 = "No statements in the database.";
		JLabel lblError = new JLabel(labelContent3);
		lblError.setFont(lblError.getFont().deriveFont(10.0f));
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (allowPlay() == true){
					System.out.println("Open Game");
					Game gameWindow = new Game();
					gameWindow.setVisible(true);
					setVisible(false);
				} else {
					System.out.println("No statements in the database");
					errorPanel.add(lblError);
					errorPanel.revalidate();
					errorPanel.repaint();
				}
			}
		});
		btnPlay.setBounds(200, 90, 100, 30);
		getContentPane().add(btnPlay);


		
		
		JButton btnQuestions = new JButton("Database");
		btnQuestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (playing == false) {
					System.out.println("Open Database");
					DisplayQuestions questionsWindow = new DisplayQuestions();
					questionsWindow.setVisible(true);
					setVisible(false);
				}
			}
		});
		btnQuestions.setBounds(200, 130, 100, 30);
		getContentPane().add(btnQuestions);
		
		
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Exit Program");
				System.exit(0);
			}
		});
		btnQuit.setBounds(200, 190, 100, 30);
		getContentPane().add(btnQuit);
	}
	
	
	
	
	private void getNewQuestionFromUser (){
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
	}
	
	public boolean allowPlay(){
		boolean canPlay = false;
		int numberOfQuestions = questionQueries.getMaxId();
		if (numberOfQuestions > 0) { canPlay = true; }
		return canPlay;
	}

}
