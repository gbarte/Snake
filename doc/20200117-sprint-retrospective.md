# Sprint Retrospective, Iteration #3
_The task number refers to the issue number on GitLab._


## Assignment
| Task   | Assigned to                             | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                 |
|--------|-----------------------------------------|-----------------------------|--------------------------|------|-----------------------|
| #52    | Gabriele                                | 5                           | 8                        | yes  |
| #52    | Mihai                                   | 5                           | 4                        | yes  | It took quite a lot to understand which design patterns we can use in our game. |
| #53    | Roman                                   | 3                           | 3                        | yes  |  I decided to slightly refactor the code for this assignment to have a more clear architecture (#68). I have some doubts about the diagram as we didn't have a lot of examples for it. |
| #53    | Roman, Mihai, Gabriele, Mirijam, Sanjay | 0.5                         | 0.5                      | yes  |
| #78    | Mirijam                                 | 2                           | 2.5                      | yes  | Making a retrospective takes more time than I thought it would. |

## Map System
| Task   | Assigned to | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                 |   
|--------|-------------|-----------------------------|--------------------------|------|-----------------------|
| #29    | Sanjay      | 3                           | 3                        | yes  | Was able to finish this after sprint 2 ended because that's when the snake's head and body were done |
| #45    | Sanjay      | 6                           | 18.5                     | yes  | This took a lot longer than expected, mainly because there was alot of refactoring involved to fit an entirely new improved system to existing code. And every time I'd fix something there'd be more things to fix since some time had passed.  |
| #69    | Sanjay      | 1                           | 0.7                      | yes  | This was a huge problem, because if the snake wouldn't move then this whole class would be obsolete. But thankfully it wasn't too hard to fix eventually.  |
| #70    | Sanjay      | 1                           | 0.7                      | yes  | Getting a tile on TiledMap was throwing an IndexOutOfBounds error. Wasn't too hard to fix eventually. |

Requirements: 
- Map system that allows instantiation of objects of various types and purposes in a way where it is possible to interact with them individually.
- More tiles, including tiles for the snake's head and body and integrate it with the existing classes.

## Score System & Leaderboard
| Task   | Assigned to     | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                 |
|--------|-----------------|-----------------------------|--------------------------|------|-----------------------|
| #51    | Roman           | 1                           | 1                        | yes  |
| #51    | Mirijam         | 1                           | 1                        | yes  | For us to be able to get the score from the PlayState, we had to refactor a bit and add a field to the constructor of the GameOverState and PausedState. |
| #66    | Mirijam         | 0.25                        | 0.1                      | yes  | I thought it would take me some time to figure this out, but it was really easy.
| #67    | Mirijam         | 0.5                         | 0.6                      | yes  |

Requirements:
- The player shall be able to see the top 10 leaderboard of the scores of all players at the end of each game.
- The game shall save the score of each game.
- The player shall be able to make their username be the nickname associated with their score by ticking a checkbox.

## In Game
| Task   | Assigned to     | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                 |
|--------|-----------------|-----------------------------|--------------------------|------|-----------------------|
| #37    | Gabriele        | 3                           | 3                        | yes  |                                                                                                        
| #55    | Mihai           | 4                           | 5                        | yes  | This one was quite hard to implement since it required understanding of the factory pattern and also think how it would fit in our game. This is why it took longer than anticipated.|
| #56    | Mirijam         | 1                           | 1                        | yes  |
| #57    | Mihai, Gabriele | 2                           | 2                        | yes  | This was done at the same time issue #55 was completed. |
| #61    | Mirijam         | 0.5                         | 0.5                      | yes  |
| #63    | Sanjay          | 2                           | 2.7                      | no   | This couldn't be done until we refactored all the food and powerup elements. Now we have to pass on the information of everything on the map to accurately place food. | 
| #64    | Sanjay          | 2                           | 1                        | yes  | A big bug presented itself while testing. Wasn't too hard to fix eventually. |

Requirements:
- The game shall place one food object on the arena.
- The player shall be able to pause a game that is currently in progress.
- The player shall be able to stop a game of Snake that is currently in progress.
- The game shall end when a snake crosses itself.
- The game shall increase the speed of the snake gradually during gameplay.
- The game shall contain the following special interactive elements:
    - a power-up that slows down the snake.
- The username is visible when navigating the main menu.

## Snake
| Task   | Assigned to     | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                 |
|--------|-----------------|-----------------------------|--------------------------|------|-----------------------|
| #58    | Sanjay, Mihai   | 3                           | 3                        | yes  |                       |
| #59    | Sanjay, Mihai   | 2                           |                          | no   | This issue was left for the next sprint because there were too many more important tasks to do this sprint. |

Requirements:
- Implement the created texture within the game.
- Create a texture (or multiple) for the snake.

## Miscellaneous
| Task   | Assigned to | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                 |
|--------|-------------|-----------------------------|--------------------------|------|-----------------------|
| #40    | Mirijam     | 2                           | 2                        | yes  | I had already finished the gameOverBoard, but after that I got to hear that we needed to give the user the possibility to use a nickname. |
| #60    | Mirijam     | 1                           | 1                        | yes  |
| #62    | Roman       | 0.25                        | 0.25                     | yes  | Minor bug with hiding the password's chars was fixed easily. |
| #74    | Mirijam     | 0.75                        | 0.25                     | yes  | 
| #77    | Roman       | 1                           | 1                        | yes  | Required minor refactoring to make the system work. |
|        | Roman       |                             | 1                        | yes  | Trying to replicate an issue TA had with running project; everything works fine from the moment project is freshly cloned or downloaded as ZIP from tag. |

Requirements:
- When starting a new game, a pop up will show the rules of the game.
- Hide the characters when typed in the password field.

## Refactoring
| Task   | Assigned to     | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                 |
|--------|-----------------|-----------------------------|--------------------------|------|-----------------------|
| #68    | Roman           | 2                           | 2                        | yes  | The modules were composed to have a more clear architecture. |
| #72    | Gabriele, Mihai | 0.5                         | 0.5                      | yes  | It was quite easy to refactor our state class to match the State design pattern (necessary for assignment 3) since we already had all the necessary game logic. |

## Testing
| Task   | Assigned to | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                 |
|--------|-------------|-----------------------------|--------------------------|------|-----------------------|
| #71    | Mihai       | 2                           | 3                        | no   | Issue left for the next spring, because there were unexpected issues with the tests passing on the pipeline. |


Project: Snake

Group: 10

___

## Main problems encountered

 #### Problem 1
 Description: Divide issues into smaller ones, because otherwise it seems like you are working on one issue only at all times.
Reaction: Next time the (huge) task/issue will be divided into smaller subtasks.

 #### Problem 2
 Description: Testing methods that use external libraries takes longer than expected. Sometimes they can't even be tested because they're final.
Reaction: Added a new resource package which has a mockito extension that helps with testing these.

 #### Problem 3
 Description: Coverage still not very high.
Reaction: Focus on testing now that (most) code is done and will likely not get refactored over and over again.

#### Problem 4 Roman
Description:  I found Exercise 2 and 3 for Assignment 3 very weird and confusing
I found Exercise 2 and 3 for Assignment 3 very weird.
Exercise 2 asked us to describe architecture that we used in the project. However, we did not have the task to implement one. So we ended up with something that worked best for our case, but didn't strictly convey practices of MVC or Server-Client architectures.
Exercise 3 was completely meaningless as it asked us to implement some features, which is something we had to anyway in the sprint.


## Adjustments for next sprint
- We could improve on making better use of issues by updating the progress on them more frequently.
