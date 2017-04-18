import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class QuestionQueries {
	
	private static final String URL = "jdbc:mysql://localhost:3306/truefalse";	// Database location.
	private static final String USERNAME = "root";								// Default UN.
	private static final String PASSWORD = ""; 									// Default PW is no PW.
	
	private java.sql.Connection  connection = null;
	private java.sql.Statement dbCreator = null;
	private java.sql.Statement tableCreator = null;	// Defining this variable here so that we can generate the database and table immediately when the app launches.
	
	private static String jdbcDriver = "com.mysql.jdbc.Driver";
	
	// The following sets up the statements so that they exist.
	private PreparedStatement selectAllQuestions = null;
	private PreparedStatement insertQuestion = null;
	private PreparedStatement deleteAllQuestions = null;
	private PreparedStatement findQuestion = null;
	private PreparedStatement findAnswer = null;
	private PreparedStatement findMaxId = null;
	private PreparedStatement resetIncr = null;

	
	public QuestionQueries()
	{

		try
		{
			// Class.forName(jdbcDriver);	Not necessary.
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=");	// Goes to phpMyAdmin without a database.
	        dbCreator = connection.createStatement();
	        dbCreator.executeUpdate("CREATE DATABASE IF NOT EXISTS truefalse");	// Creates the "truefalse" database, making the .SQL-script optional.
	        
	        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);	// Now that we have the database for sure, it's time to connect to it.
	        tableCreator = connection.createStatement(); // These are here to create mandatory tables just in case the database was created recently.
	        tableCreator.executeUpdate("CREATE TABLE IF NOT EXISTS question (id integer NOT NULL AUTO_INCREMENT, statement VARCHAR(30) NOT NULL, answer boolean NOT NULL, PRIMARY KEY (id ));");
	        
	        // List of queries needed by other methods. Mostly self-explanatory.
			selectAllQuestions = connection.prepareStatement("SELECT * FROM question");
			insertQuestion = connection.prepareStatement("INSERT INTO question (statement, answer) VALUES (?, ?)");
			deleteAllQuestions = connection.prepareStatement("DELETE FROM question");
			findMaxId = connection.prepareStatement("SELECT max(id) FROM question");	// used to find out the amount of Questions(=statements) in the database.
			resetIncr = connection.prepareStatement("ALTER TABLE question AUTO_INCREMENT = 1");

		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
			System.exit(1);
		}
	} // End of "public QuestionQueries()" method.
	
	
	public boolean getAnswer(int count)	// Using the Game.java specific "count" variable to get the answer to the question with corresponding ID.
	{
		boolean result = false;
		ResultSet rs = null;	// Using ResultSet as an alternative way for training purposes.
		try
		{
			findAnswer = connection.prepareStatement("SELECT answer FROM question WHERE id="+count);
			rs = findAnswer.executeQuery();
			rs.next();
			result = rs.getBoolean(1);
		} catch (SQLException sqlException) {	sqlException.printStackTrace(); }
		return result;
	}
	
	public String getQuestion(int count) // Using the Game.java specific "count" variable to get the statement from the question with corresponding ID.
	{
		String statement = "statement"+count;	// To make sure at least something is being returned in case an error occurs.
		ResultSet rs = null;
		try
		{
			findQuestion = connection.prepareStatement("SELECT statement FROM question WHERE id="+count);
			rs = findQuestion.executeQuery();
			rs.next();
			statement = rs.getString(1);
		} catch (SQLException sqlException) {	sqlException.printStackTrace(); }	
		return statement;
	}
	
	public int getMaxId()	// To get the number of statements in the database.
	{
		int result = 0;
		ResultSet resultSet = null;
		try
		{
			resultSet = findMaxId.executeQuery();
			resultSet.next();
			result = resultSet.getInt(1);
		} catch (SQLException sqlException) {	sqlException.printStackTrace(); }	
		return result;
	}
	


	public ArrayList<Question> getAllQuestions()	// Array used for populating the table in "DisplayQuestions.java".
	{
		ArrayList<Question> results = null;
		ResultSet resultSet = null;
		
		try
		{
			resultSet = selectAllQuestions.executeQuery();
			results = new ArrayList<Question>();
		
			while(resultSet.next())
			{
				results.add(new Question(
				resultSet.getInt("id"),
				resultSet.getString("statement"),
				resultSet.getBoolean("answer")));
			}
		}
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
		}
		
		return results;	// Returns an ArrayList.
	} // The end of "public ArrayList<Question> getAllQuestions()" method.
	

	
	
	public void deleteAllQuestions()
	{
		int resultSet = 0;		// Needs a value just in case queries fail to run.
		try
		{
			resultSet = deleteAllQuestions.executeUpdate();	// Empties the "question" table.
			resultSet = resetIncr.executeUpdate();	// Auto-increment has to be reset to properly work with Game.java specific "count" variable.
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
			insertQuestion.setString(1, statement);
			insertQuestion.setBoolean(2, answer);

			int result = insertQuestion.executeUpdate();  // This query has variables as an input. Hence "statement" and "answer".
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}	
	}

	
	
}	// End of this .java file.

