Monty Hall game
---------------

This game I've started to play and practice TDD.
This is the first attempt for me to try TDD in a wild
MontyHall is a simple class attended to implement something
about I've understood about the legendary Monty Hall
paradoxon.
For details see this URL:
	http://en.wikipedia.org/wiki/Monty_Hall_problem

Game rules (excerpt from the URL above):
Player is on a game show, and she's given the choice 
of three doors: Behind one door is a car (Player wins); 
behind the others, goats (Player looses). 
Player picks a door identified by a number, say No. 1, 
and the host, who knows what's behind the doors, 
opens another door, say No. 3, which has a goat. 
He then says to you, "Do you want to pick door No. 2?" 
Is it to your advantage to switch your choice?

Implemented features:
 - select a door and mark it to be opened
 - open a door and show content behind
 - Host opens a door which leads to loose
 -- Q:what if there is two looser doors present?
 -- D:we will show the first found not selected looser door 
