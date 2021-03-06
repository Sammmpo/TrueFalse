TrueFalse Game

This project was created for Object-Oriented Java course in Laurea University of Applied Sciences.

What is TrueFalse?
- The idea of the application is to let the user create a custom quiz with two possible answers, True and False.
- In the database menu, the user can see the list of all statements (with their correct answers) in the database. Database management, including creation and deletion of custom statements is also a feature.
- By clicking 'Play' in the Main Menu, the game will start asking whether the statements are True or False one by one (reading them from the database). After the user has answered to all questions, the application displays the number of correct answers and returns to the Main Menu.

How to Quick Setup and Run:
- Launch MySQL (with XAMPP for example)
- Run the TrueFalseGame.jar (executable) file.
- Enjoy the App experience!

Alternative Setup:
- You need to have Eclipse (with JDBC library) installed on your computer and have access to MySQL.
- Download the .java files from the GitHub src-folder OR just simply download everything as .ZIP.
- Import these files into your Eclipse (if you downloaded everything as .ZIP, Eclipse should recognize the files as one project).
- Launch MySQL (with XAMPP for example).
- (OPTIONAL) Use the .SQL file (in src-folder) with phpMyAdmin to import 5 default quiz questions. This is not mandatory as the application lets the user create custom quizzes and creates database/tables automatically. You will need Apache for this.
- Run the java classes with your Eclipse.
- Enjoy the App experience!

Commonly known bugs:
- Editing the database during a play-session may result in a crash. However, this should require the user having two copies of the application running at the same time. This could be fixed by making the application run a new instance of the database when entering the 'play mode'.

You can find a screenshot of the GUI in the files folder.

_____

List of Variables and Objects in each class:

MainMenu.java
- boolean playing
- multiple button objects
- instance of object QuestionQueries "questionQueries"

Game.java
- int count
- int points
- boolean userAnswer
- instance of QuestionQueries
- multiple button objects

DisplayQuestions.java
- JTable tableQuestion
- JButton btnAddQuestion
- JButton btnClear
- JButton btnBack
- instance of object DefaultTableModel "myQuestionTableModel"
- ArrayList<Question> allQuestions
- instance of object Question "currentQuestion"

QuestionQueries.java
- Prepared Statements

Question.java
- Constructor for Question objects
- int id
- String statement
- boolean answer
