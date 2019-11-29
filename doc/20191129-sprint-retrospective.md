# Sprint Retrospective, Iteration #1
_The task number refers to the issue number on GitLab._

Miscellaneous tasks:

|Task #   |Assigned to   |Estimated effort (in hours)   |Actual effort (in hours)   |Done   |Notes   |
|---|---|---|---|---|---|
| 13  | Roman  |4   |2   |yes   |CI was configured by default, just added some additional logics for tracking code coverage.   |
|  1 | Everyone  |5   |5   |  yes |   |
|  2 | Everyone  | 5  |5   | yes  | We might change the libraries we choose to use in the future.  |
| 14  | Mihai  |2   | 3  | yes  |   |
| 15  | Everyone  | 1  |1   |yes   |We had to define what exactly the scrum master would be responsible for.   |
| 16  |Sanjay, Mirijam, Gabriele   |  4 |  5 | yes  | Took us a bit longer, since we were waiting for some feedback from Sara.  |
|  18 | Mihai, Sanjay, Mirijam, Gabriele  | 4 | 5  |yes   | UML took us a bit longer than expected, also adding use case descriptions took us a bit longer than expected.  |
| 20  |Roman   | 1  |  1 |  yes | Created a README file to display the most crucial information about the project.  |
|  21 |  Sanjay, Mirijam, Gabriele |  2 | 2  |  yes |  We didn't realize that once you created a file you could not delete it, so we had to be a bit careful and reuse created files. Decided that we write assignments and notes on the drive and then write it over to the wiki. Gabriele: created page about workload, edited definition of done |
| 22  |  Gabriele | 3  | 3.5  | yes  |   |
|  23 | Mihai, Sanjay, Gabriele  | 0.5  | 1  | yes  |   |
| 24  | Everyone  | 2.5  | 1  |   |   |
| 25  | Sanjay, Mirijam  | 1  |  1 |  yes | We worked on making sure the requirements we selected made sense for the first sprint or were quite important. |
|  26 | Roman  | 1/30  | 0.25  | no  | The integration of the badges requires minor changes in the project's Settings that only Sara has access to.  |
| 28  | Mirijam  | 1  | 1  | yes  | We at first had one big UML with 6 requirements, but after Sara's feedback we realized we had to split it into 6 seperate requirements and divide up the tasks once more.   |
|  19 | Everyone  | 1/3  | 3  | yes  | Wrote the requirements we completed in this sprint.  |

Requirements: 
- The game shall start with an empty game map
- The game map shall be of at least 11 by 11 tiles

|Task #   |Assigned to   |Estimated effort   |Actual effort   |Done   |Notes   |
|---|---|---|---|---|---|
| 8  | Sanjay  | 4  | 6  |yes   |Map gets rendered using a tmx file.   |
| 29  | Sanjay  | 3  | 0  | no  |Decided to use this for the next sprint when we'll further develop the game. We still have some issues figuring out how libGDX works.   |
|  30 | Sanjay  | 3  | 3.5  | no  |Since we decided to use all of libGDX's functionalities for the next sprint, this task isn't done yet   |


Requirements:
- The player shall be able to move the snake in a square pattern (left, right, up and down) using the keyboard.
- The player shall be able to launch the game.
- The initial snake shall start moving when game starts

|Task #   |Assigned to   |Estimated effort   |Actual effort   |Done   |Notes   |
|---|---|---|---|---|---|
| 9  |  Mirijam |  4 | 4  | yes  | After rendering snake movement of the head was quite easy, but after adding bodyparts a few problems were encountered but also solved quickly.  |
|  10 |  Mirijam |  4 | 25  |  yes | Easy task, but bc of no experience took longer than expected, also integration with the map was quite confusing, because I assumed the map would be an array.  |

Requirements:
- The game shall start with a snake of starting size on the screen.
- The game shall not allow the snake to go outside the map borders.
- The snake shall be able to grow.

|Task #   |Assigned to   |Estimated effort   |Actual effort   |Done   |Notes   |
|---|---|---|---|---|---|
| 12  | Roman  | 24  | 6  | yes  | Testing needed some extra effort, but the task was completed on time.  |
| 11  | Mihai  | 4  | 5  |   yes|  Testing the play screen took more time than expected since the library we chose uses static methods. Also some of the methods could not be tested but we will try to find a solution in the next sprint. |
| 17  |  Gabriele | 4  | 2  | yes  | Task smaller than expected because part of the work was done by Mirijam in prerequisites. |
| 27  | Mihai  | 1  |  1 |yes   | Score calculator should have been added to a future sprint since this sprint we did not have all the other necessary classes to fully implement it.  |

<!-- 
Requirements:

|Task #   |Assigned to   |Estimated effort   |Actual effort   |Done   |Notes   |
|---|---|---|---|---|---|
|   |   |   |   |   |   |
|   |   |   |   |   |   |
|   |   |   |   |   |   |
|   |   |   |   |   |   |
|   |   |   |   |   |   |
|   |   |   |   |   |   |
|   |   |   |   |   |   | -->
<!-- copy-paste and add another user story with its own table -->

Project: Snake
Group: 10

___

## Main problems encountered

#### Problem 1
Description: Formatted GitLab issues chaotically.
Reaction: Reacted to Sara’s feedback and created templates, added time estimates.

#### Problem 2
Description: A lot of time spent on code that we cannot use yet.
Reaction: Code kept on separate branch in case we will need it later.

#### Problem 3
Description: Did not cross out requirements from the list when they were implemented.
Reaction: Crossed out completed requirements at the end of the sprint.

#### Problem 4
Description: Not being able to test some methods because of the static classes being used inside them.
Reaction: Research how to test them using mocks or other techniques.

#### Problem 5
Description: Started coding for scalability and for the future code to be implemented, which made the classes a bit too complex, instead of coding for the first sprint's simplicity.
Reaction: Did not delete the code, instead changed it for sprint 2 where we'll go much more in-depth and add more features.

## Adjustments for next sprint
- Stick to issue templates.
- Discuss more before coding to make sure everyone has a similar vision of the implementation.
- Start testing a bit earlier to avoid eventual problems/difficulties we might run into.