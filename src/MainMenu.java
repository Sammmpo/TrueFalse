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

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 1};
		gbl_contentPane.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 1};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		

		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Open Game");
				Game gameWindow = new Game();
				gameWindow.setVisible(true);
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnPlay = new GridBagConstraints();
		gbc_btnPlay.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlay.gridx = 3;
		gbc_btnPlay.gridy = 3;
		contentPane.add(btnPlay, gbc_btnPlay);

		
		
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
		GridBagConstraints gbc_btnQuestions = new GridBagConstraints();
		gbc_btnQuestions.insets = new Insets(0, 0, 5, 5);
		gbc_btnQuestions.gridx = 3;
		gbc_btnQuestions.gridy = 5;
		contentPane.add(btnQuestions, gbc_btnQuestions);
		
		
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Close the App");
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnQuit = new GridBagConstraints();
		gbc_btnQuit.insets = new Insets(0, 0, 0, 5);
		gbc_btnQuit.gridx = 3;
		gbc_btnQuit.gridy = 7;
		contentPane.add(btnQuit, gbc_btnQuit);
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

}
