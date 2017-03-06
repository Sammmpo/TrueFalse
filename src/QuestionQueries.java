import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * This Class manages database operations associated to the Question Class
 * Don't worry yet about the try/catch/finally blocks in some methods. This will be explained later in the course 
 */
public class QuestionQueries {
	// DB connection details
	private static final String URL = "jdbc:mysql://localhost:3306/truefalse";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	private Connection connection = null;
	private PreparedStatement selectAllQuestions = null;
	private PreparedStatement insertQuestion = null;
	private PreparedStatement deleteAllQuestions = null;
	
	public QuestionQueries()
	{
		try
		{
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // Starts a connection to the database
			selectAllQuestions = connection.prepareStatement("SELECT * FROM question"); // Prepare the select query that gets all Questions from the database
			insertQuestion = connection.prepareStatement("INSERT INTO question (statement, answer) VALUES (?, ?)");
			deleteAllQuestions = connection.prepareStatement("DELETE FROM question");
			
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	/*
	 * This method will execute the select query that gets all Questions from the database. 
	 * It returns an ArrayList containing Question objects initialized with Question data from each row in the Questions table (database)
	 */
	public ArrayList<Question> getAllQuestions()
	{
		ArrayList<Question> results = null;
		ResultSet resultSet = null;
		
		try
		{
			resultSet = selectAllQuestions.executeQuery(); // Here is where we actually execute the select query. resultSet contains the rows returned by the query
			results = new ArrayList<Question>();
		
			while(resultSet.next()) // for each row returned by the select query...
			{
				// Initialize a new Question object with the row's data. Add the Question object to the results ArrayList
				results.add(new Question(
					resultSet.getString("statement"), // get the value associated to the platNr column
					resultSet.getBoolean("answer"))); // get the value associated to the colour column
			}
		} // end try
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		finally
		{
			try
			{
				resultSet.close();
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		} // end finally
		
		return results;
	} // end method getAllQuestions
	

	
	
	public void deleteAllQuestions()
	{
		int resultSet = 0;
		
		try
		{
			resultSet = deleteAllQuestions.executeUpdate();
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	
	
	
	public void addQuestion(String statement, boolean answer)
	{
		try
		{
			// Setting the values for the question marks '?' in the prepared statement
			insertQuestion.setString(1, statement);
			insertQuestion.setBoolean(2, answer);
						
			// result will contain the amount of updated rows. It should be 1. To simplify the code let's not verify this
			int result = insertQuestion.executeUpdate(); 
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}	
	}

	
	
}