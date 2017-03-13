
public class Question {	// Constructor for the "Question" object.
	
	private int id;
	private String statement;
	private boolean answer; 
	
	Question (int aId, String aStatement, boolean aAnswer)
	{
		this.id = aId;
		this.statement = aStatement;
		this.answer = aAnswer;
	}
	
	// Getters to allow the other .java files access the object's attributes.

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


} // End of this .java file.