import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class QuestionQueries {

	private static final String URL = "jdbc:mysql://localhost:3306/truefalse";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	private Connection connection = null;
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
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			selectAllQuestions = connection.prepareStatement("SELECT * FROM question");
			insertQuestion = connection.prepareStatement("INSERT INTO question (statement, answer) VALUES (?, ?)");
			deleteAllQuestions = connection.prepareStatement("DELETE FROM question");
			// findMaxId = connection.prepareStatement("SELECT * FROM question WHERE id=(SELECT max(id) FROM question)");
			findMaxId = connection.prepareStatement("SELECT max(id) FROM question");
			resetIncr = connection.prepareStatement("ALTER TABLE question AUTO_INCREMENT = 1");
			
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	public boolean getAnswer(int count)
	{
		boolean result = false;
		ResultSet rs = null;
		try
		{
			findAnswer = connection.prepareStatement("SELECT answer FROM question WHERE id="+count);
			rs = findAnswer.executeQuery();
			rs.next();
			result = rs.getBoolean(1);
		} catch (SQLException sqlException) {	sqlException.printStackTrace(); }
		return result;
	}
	
	public String getQuestion(int count)
	{
		String statement = "statement"+count;
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
	
	public int getMaxId()
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
	
	/* LONGER METHOD!
	public int getMaxId()
	{
		ArrayList<Question> resultArray = null;
		ResultSet resultSet = null;
		try
		{
			resultSet = findMaxId.executeQuery();
			resultArray = new ArrayList<Question>();
			
			while (resultSet.next()) {
			resultArray.add(new Question(
				resultSet.getInt("id"),
				resultSet.getString("statement"),
				resultSet.getBoolean("answer")));
			}
		}
		
		catch (SQLException sqlException) {	sqlException.printStackTrace(); }
		Question test = resultArray.get(0);
		int result = test.getId();
		return result;
	}
	*/
	

	public ArrayList<Question> getAllQuestions()
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
		
		return results;
	}
	

	
	
	public void deleteAllQuestions()
	{
		int resultSet = 0;
		
		try
		{
			resultSet = deleteAllQuestions.executeUpdate();
			resultSet = resetIncr.executeUpdate();
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
						

			int result = insertQuestion.executeUpdate(); 
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}	
	}

	
	
}