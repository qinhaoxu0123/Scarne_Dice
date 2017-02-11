# Scarne_Dice
Scarne's dice

Scarne’s Dice is a turn-based dice game where players score points by rolling a die and then:

if they roll a 1, score no points and lose their turn
if they roll a 2 to 6:
add the rolled value to their points
choose to either reroll or keep their score and end their turn
The winner is the first player that reaches (or exceeds) 100 points.

For example, if a player starts their turn and rolls a 6, they can choose to either ‘hold’ and end their turn, in which case they can add the 6 to their score, or to reroll and potentially score more points.

Let’s say they decide to roll again, and they get a 4. They now have the option to end their turn and add 10 points (6 + 4) to their score, or to roll again to get even more points.

They decide to roll again, but get a 1. Getting a 1 makes the player lose all the points from their turn (so their score is the same as before their turn), and finishes their turn, allowing the second player to begin their turn.

This goes on until one of the players reaches 100 points or more.

Implementing the UI

As mentioned in the preparation for this workshop, there is no starter code for this activity but we do provide you with some some images for the dice faces.

If you finished creating the UI in the preparation activity, you can skip this next step. Start by creating a blank activity and create the UI shown in the image below using either the visual editor or the XML editor (or probably a combination of both). The UI is composed of:

A TextView to display the score and status of the game
An ImageView to display the current die (default to the image of your choice)
Three buttons to either roll the die, end your turn or start over
Screen shot
Implementing the game

All the game logic for this app will be implemented in the Activity class (the file will be called MainActivity.java if you accepted the default name). The Activity template has some default methods to which you will add:

Four global variables to store:
the user's overall score state
the user's turn score
the computer's overall score
the computer's turn score
A click handler for the "Roll" button that will:
randomly select a dice value
update the display to reflect the rolled value
Use getResources().getDrawable in order to programmatically access other images. This functionality will also be needed for the computer turn so a helper function to roll the die may be useful to implement.

Then start creating game logic. If the roll is not a 1, update the user's turn score by the value of the roll and update the label to "Your score: 0 computer score: 0 your turn score: X". If the roll is a 1, reset the turn score to 0 and update the label accordingly. TextView can be edited programmatically by calling findViewById to get the TextView object.

Having written the basic "Roll" functionality, you can tackle the other two button handlers:

When ResetButton is clicked, reset the 4 global variables to 0 and update the label text
When HoldButton is clicked, updating the user's overall score, reset the user round score and update the label.
At this stage, the basic user turn functionality is in place. Now, you can implement the computer turn. Start off with a very simple strategy for the computer: if the computer's round score is less than 20, re-roll, otherwise hold.

Create a helper method called computerTurn, it will need to:

Disable the roll and hold buttons
Create a while loop that loops over each of the computer's turn. During each iteration of the loop:
pick a random die value and display it (hopefully using the helper you created earlier)
follow the game rules depending on the value of the roll.
Be sure to update the label with the computer's round score or "Computer holds" or "Computer rolled a one" as appropriate.

Finally, invoke the computerTurn procedure from the both the HoldButton handler and the RollButton handler (if the user rolled a 1).

You may find again that a helper procedure is useful in doing the house cleaning that concludes the computer's turn (updating the computer's overall score, reset its turn score and reenabling the buttons).

The game should now be functional so try playing a few rounds against the computer. Remember to use the logging library that you read about in last unit's preparation to help diagnose what is happening when your program doesn't behave as expected.

Although the game (hopefully) works roughly as intended you may find the computer turn to be quite hard to follow as it happens so quickly that you can hardly see the die rolls and the label updates. Let's address that by refactoring the computer turn:

Get rid of the while loop (but not its contents!) and make the computerTurn method handle a single roll of the computer's turn
If the computer can and does decide to roll again create a timed event that will do so after an appropriate delay (say 500 ms). To accomplish this, you can use Handler.postDelayed, an example of which can be seen on StackOverflow.
Enjoy the wonders of a fully functional game of Scarne's dice!

Extensions

This unit's extension suggestions are:

Two-dice version. In this version, two standard dice are rolled:
if neither shows a 1, their sum is added to the turn total
if a single 1 is rolled, the player scores nothing and the turn ends
if two 1s are rolled, the player’s entire score is lost, and the turn ends
if a double is rolled, the point total is added to the turn total as with any roll but the player is obligated to roll again.
Fast mode: Rather than rolling the dice a variable number of times, the user picks a number of dice to roll then rolls those dice all at once. If a 1 is shown, the user gets nothing. If no 1 is shown, the user gets the sum of the dice. In either case, the turn is over and the other player takes a turn. You can read more about this game in this research paper.
Implement a smarter computer player. I wouldn't recommend attempting the optimal player (which you can read about here ) but take into account the difference between the computer score and user score in deciding when to hold
Write another dice game of your choice
Add support for playing the game to 100 points, declaring a winner and starting a new game
Create an animation representing each die roll. This will avoid confusion when rolling the same value twice in a row.
