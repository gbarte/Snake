# Sprint Retrospective, Iteration #3
_The task number refers to the issue number on GitLab._


## Assignment
| Task   | Assigned to                             | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                 |
|--------|-----------------------------------------|-----------------------------|--------------------------|------|-----------------------|
| #52    | Gabriele                                | 5                           | 8                        | yes  |
| #52    | Mihai                                   | 5                           | 4                        | yes  |
| #53    | Roman                                   | 3                           | 3                        | yes  | 
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

## In Game
| Task   | Assigned to     | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                 |
|--------|-----------------|-----------------------------|--------------------------|------|-----------------------|
| #37    | Gabriele        | 3                           |                          |      |                                                                                                        
| #55    | Mihai           | 4                           | 5                        | yes  |
| #56    | Mirijam         | 1                           | 1                        | yes  |
| #57    | Mihai, Gabriele | 2                           | 2                        | yes  |
| #61    | Mirijam         | 0.5                         | 0.5                      | yes  |
| #63    | Sanjay          | 2                           | 2.7                      | no   | This couldn't be done until we refactored all the food and powerup elements. Now we have to pass on the information of everything on the map to accurately place food. | 
| #64    | Sanjay          | 2                           | 1                        | yes  | A big bug presented itself while testing. Wasn't too hard to fix eventually. |

Requirements:
- Implement a power up system in the game.
- The username is visible when navigating the main menu.
- Player can pause the game, when pressing the P button.
- Player can quit the game,when pressing the Q button.
- When the snake's head touches its body, the GameOverState should be invoked.

## Snake
| Task   | Assigned to     | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                 |
|--------|-----------------|-----------------------------|--------------------------|------|-----------------------|
| #58    | Sanjay, Mihai   | 3                           |
| #59    | Sanjay          | 2                           |

## Miscellaneous
| Task   | Assigned to | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                 |
|--------|-------------|-----------------------------|--------------------------|------|-----------------------|
| #40    | Mirijam     | 2                           | 2                        | yes  | I had already finished the gameOverBoard, but after that I got to hear that we needed to give the user the possibility to use a nickname. |
| #60    | Mirijam     | 1                           | 1                        | yes  |
| #62    | Roman       | 0.25                        | 0.25                     | yes  | 
| #74    | Mirijam     | 0.75                        | 0.25                     | yes  | 
| #77    | Roman       | 1                           | 1                        | 

Requirements:
- When starting a new game, a pop up will show the rules of the game.
- Hide the characters when typed in the password field.
## Refactoring
| Task   | Assigned to     | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                 |
|--------|-----------------|-----------------------------|--------------------------|------|-----------------------|
| #68    | Roman           | 2                           | 2                        | yes  |
| #72    | Gabriele, Mihai | 0.5                         | 0.5                      | yes  |

## Testing
| Task   | Assigned to | Estimated effort (in hours) | Actual effort (in hours) | Done | Notes                 |
|--------|-------------|-----------------------------|--------------------------|------|-----------------------|
| #71    | Mihai       | 2                           |


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

## Adjustments for next sprint

