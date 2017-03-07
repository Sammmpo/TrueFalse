
public class Question {
	
	private int id;
	private String statement;
	private boolean answer; 
	
	Question (int aId, String aStatement, boolean aAnswer)
	{
		this.id = aId;
		this.statement = aStatement;
		this.answer = aAnswer;
	}

	public int getId()
	{
		return this.id;
	}
	
	public String getStatement()
	{
		return this.statement;
	}
	
	public boolean getAnswer()
	{
		return this.answer;
	}


}