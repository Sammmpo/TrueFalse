((( WORK IN PROGRESS! )))

TrueFalse Game

This project was created for Object-Oriented Java course in Laurea University of Applied Sciences.

How to setup / requirements
- You will need to have Eclipse (with JDBC library) and XAMPP installed on your computer.
- Download the .java files from the GitHub src-folder OR just simply download everything as .ZIP.
- Import these files into your Eclipse (if you downloaded everything as .ZIP, Eclipse should recognize the files as one project)
- Open XAMPP and launch Apache and MySQL.
- Use the .SQL file (you can find it in the GitHub folder) in phpMyAdmin to create the database and tables.
- Run the java classes with your Eclipse.
- Enjoy the app experience!

Troubleshooting:
- I have used 8080 as my port for XAMPP.


What is TrueFalse and how to play it?
- The idea of the application is to let the user create a custom quiz with two possible answers, True and False.
- In the database menu, the user is allowed to see the list of statements (and the correct answers) in the database, as well as add and delete custom statements.
- By clicking 'Play' in the Main Menu, the game will ask whether the statements are True or False one by one (reading them from the database). After the user has answered to all questions, the application displays the number of correct answers.

Commonly known bugs:
- Editing the database during a play-session may result in a crash. This should however require the user having two copies of the application running at the same time.
