# Sprint Retrospective, Iteration #2
_The task number refers to the issue number on GitLab._


## Assignment
| Task # | Assigned to | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                                                                                                                       |
|--------|-------------|-----------------------------|--------------------------|------|-----------------------------------------------------------------------------------------------------------------------------|
| 34     | Sanjay      | 8                           | 9                        | yes  | Made this exercise and asked for feedback from our TA, after that implemented the feedback into the assignment.             |
| 35     | Sanjay      | 8                           | 7                        | yes  | Even after asking for feedback before the deadline, the assignment wasn't clear, but I did implement the received feedback. |


## Map System
| Task # | Assigned to | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                                                                                           |
|--------|-------------|-----------------------------|--------------------------|------|-------------------------------------------------------------------------------------------------|
| 30     | Sanjay      | 3                           | 3.5                      | no   | Tests and review required more time than expected due to slightly underestimated amount of work.|
| 45     | Sanjay      | 6                           | 0                        | no   | Wasn't able to do this due to the close deadlines and midterms, instead focused on assignments. |

## Authentication and Database
| Task # | Assigned to | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                                                                                                                                                                                                                                                     |
|--------|-------------|-----------------------------|--------------------------|------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 33     | Roman       | 3                           | 5                        | yes  | The database implementation didn't require as much work as expected. For example, there is no need to run a local server, everything is done in the main thread. After received feedback from TA, fixed the naming unclarity for the corresponding class. |
| 3      | Roman       | 5                           | 4                        | yes  | The implementation of the feature was clear & flawless, but the testing took a little bit more time; just more time consuming to cover most of the parts.                                                                                                                                                      |
| 6      | Mirijam     | 2                           | 3                        | yes  | Took a while, figuring out how to integrate new code from the State class and how to add buttons, labels and textfields to the screen.                                                                                                                    |

Requirements:
- The game shall show a login screen when the game is opened
- The player shall be able to login using their account details
- The player shall be able to start the game only if authentication is successful


## Score System & Leaderboard
| Task # | Assigned to | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                                                                                                                                                                                                                  |
|--------|-------------|-----------------------------|--------------------------|------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 27     | Mihai       | 1                           | 1.5                      | yes  | Since every prerequisite for this issue was already there, it was quite easy to add the functionality of calculating the score. According to the feedback we got, we should improve naming of the corresponding class. |
| 36     | Mihai       | 0.7                         | 1                        | yes  | For now the score is being rendered in the lower left corner of the screen, but this will be changed in the future when we will have a better spot on the screen to place it.                                          |
| 46     | Mirijam     | 2                           | 1                        | yes  | No particular challenges were in implementing this issue. Leaderboard UI is ready for use.                                                                                                                                                             |
| 42     | Roman       | 3                           | 4                        | yes  | Required a little bit more effort due to need to refactor overall database connection architecture.                                                                                                                    |

Requirements:
- The game shall set the player’s score to 0 when starting the game
- The game shall update the user score throughout gameplay
- The game shall show the user score throughout gameplay
- The player shall be able to see the top 5 leaderboard of the scores of all players at the end of each game
- The score shall increase by 10 when the snake eats one food object

## Branding
| Task # | Assigned to | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                                                                                                                                                            |
|--------|-------------|-----------------------------|--------------------------|------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 4      | Everyone    | 0.25                        | 0.25                     | yes  | Overall was a little bit more difficult than expected due to variety of options.                                                                                 |
| 5      | Everyone    | 0.5                         | 1                        | yes  | Took a little bit more time, because we were uncertain about the best choice and amount of work the one would need in the future. The choice, however, was made. |
| 38     | Mirijam     | 4                           | 3                        | yes  | Main menu was pretty easy to do, since I had already figured out how buttons worked during the login screen creation. Only trouble was finding a good theme.     |

Requirements:
- The game shall have a name
- The game shall have a theme


## In Game
| Task # | Assigned to | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                                                                                                                                                                                                                                          |
|--------|-------------|-----------------------------|--------------------------|------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 31     | Mihai       | 1                           | 1                        | yes  | We rendered an apple with random coordinates on the screen. This was needed to satisfy the requirements for assignment 2. This issue did not include the collision between the snake and the apple. For the collision, another issue was made. |
| 32     | Mihai       | 3                           | 4                        | yes  | This issue is a continuation of issue 31. For now we have just an apple as a food element which the snake can interact with, but later on there will be other elements as well.                                                                |
| 47     | Gabriele    | 1                           | 1                        | yes  | No particular challenges encountered.                                                                                                                                                                                                          |
| 41     | Gabriele    | 6                           | 7                        | yes  | Got stuck on testing GameOverState in PlayStateTest. Mihai helped me in finding that I had made bugs when setting up GameOverState and this was making testing harder. He also helped me in finishing checkOutOfMap tests.                     |

Requirements:
- The snake shall be able to eat food or other objects
- The snake shall be able to grow
- The snake shall grow by one cell when it eats one food object

## Miscellaneous
| Task # | Assigned to | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                                                                                                           |
|--------|-------------|-----------------------------|--------------------------|------|-----------------------------------------------------------------------------------------------------------------|
| 49     | Roman       | 1                           | 1                        | yes  | No particular challenges encountered. This issue is implemented to comply with the feedback on code quality.    |
| 43     | Gabriele    | 2                           | 2.5                      | yes  | Took longer than expected as it was my first time dealing with checkStyle warnings.                             |


Project: Snake

Group: 10

___

## Main problems encountered

#### Problem 1
Testing is still a problem as it was in the previous sprint. The reason for this is related to the framework we are using. We create new objects provided by the framework inside the methods we are tesing and this throws a NullPointerException. We haven't managed to tackle this problem entirely yet. 

Reaction: More effort needs to be dedicated to this part. The discussion for the next sprint is arranged to learn a better approach. Will also discuss it with others on Mattermost as advised by TA.

#### Problem 2
The TA meetings on Thursday are sometimes inconvenient, because the deadlines are the next day, so the feedback may require unexpected changes.

Reaction: Make more effort to get relevant feedback implemented by the meeting a week before the deadline, and on the meeting a day before deadline make only critical changes.

#### Problem 3
Miscommunication resulted in some members doing the same work twice.

Reaction: Agreed on more regular team updates and more clear issue assignment.


## Adjustments for next sprint
- Discuss better testing approaches.
- Be more involved into reviewing the work of others.

