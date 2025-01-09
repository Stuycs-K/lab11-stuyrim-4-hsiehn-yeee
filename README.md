[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/KprAwj1n)
# APCS - Stuyrim

## Features

Make a clear list of features that work/dont work

:white_check_mark: This feature works.

:question: This feature works partially.

:ballot_box_with_check: This extra (beyond the things that the lab was supposed to do) feature works.

:x: This required feature does not work.

:beetle: This is a bug that affects the game.


## Adventurer Subclasses

| **Attributes** | **Sunflowers (30 HP)** | **Chomper (changed from CodeWarrior) (25 HP)** | **Zombie (35 HP)** |
| :--------- | :----------------: | :--------------------------------: |---------------:|
| Special    | sunExposure (15)   | ph (14) (note that less pH means better special)| | brains (10) |
| Attack     | "Flamethrower": Sunflowers launch themselves at you, dealing 1-3 damage and losing 1-3 HP.|"Fly Trap": The Chomper snatches its enemies in their acidic trap, dealing 5-7 HP and losing 2 pH.|"Devour": The Zombie eats at an opponent, dealing 3 damage and gaining 1 brain.|
| Special Attack | "Sunburn": Sunflowers emanate a bright light, decreasing the opponent’s special by 25% while also dropping their own by 10%.|"Withering": The Chomper spits out acid around it, dealing 14-its current pH level and gaining 3 pH (pH must be lower than 7)|"Horde": The Zombie sets out 2-4 brains, luring its zombie friends to come and help it. The zombies attack the opponents, dealing 3 * 2 to 3 * 4 damage (# of zombies lured* 3) |
| Support | "Vitamin D": The ally eats two sunflowers, shielding them from the next attack, while the sunflowers take 2 damage.|"Blooming":The Chomper spits out a glob of acid, restoring 1 special or 1 HP depending on which one is lower.|"CS Test": *If ally is a Zombie:* The Zombie proctors a computer science test for its ally, forcing it to get smarter and gain 3 brains.<\n>*If ally is not a Zombie:* The Zombie uses the test as compost, giving its ally 1 special.|
| Support-Self | "Plant":Sunflowers cover the ground with seeds, sprouting new sunflowers and regaining 5-7 HP.| N/A. If called, no action will be taken. |"Scavenge": The Zombie is constantly on the lookout for brains. There is a 75% chance it will spot some and eat them, gaining 4 brains.|

