
public class Question {
	
	private String statement;
	private boolean answer; 
	
	Question (String aStatement, boolean aAnswer)
	{
		this.statement = aStatement;
		this.answer = aAnswer;
	}

	// Getters will be needed in file JdbcExample.java
	public String getStatement()
	{
		return this.statement;
	}
	
	public boolean getAnswer()
	{
		return this.answer;
	}
}