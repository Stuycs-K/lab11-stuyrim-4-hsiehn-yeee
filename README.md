[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/KprAwj1n)
# APCS - Stuyrim

## Features

Make a clear list of features that work/dont work

:white_check_mark: This feature works.

:question: This feature works partially.

:ballot_box_with_check: This extra (beyond the things that the lab was supposed to do) feature works.

:x: This required feature does not work.

:beetle: This is a bug that affects the game.

### Chomper:

:white_check_mark: Attack (Fly Trap)

:white_check_mark: Special Attack (Withering)

:white_check_mark: Support (Blooming)

:white_check_mark: Support Self (N/A)

:ballot_box_with_check: Special pH rule

### Sunflowers:

:white_check_mark: Attack (Flamethrower)

:white_check_mark: Special Attack (Sunburn)

:ballot_box_with_check: Support (Vitamin D --> Shielding Feature)

:white_check_mark: Support Self (Plant)
:white_check_mark: Support Self (Plant)

### Zombie:

:white_check_mark: Attack (Devour)
:white_check_mark: Attack (Devour)

:white_check_mark: Special Attack (Horde)
:white_check_mark: Special Attack (Horde)

:white_check_mark: Support (CS Test)
:white_check_mark: Support (CS Test)

:white_check_mark: Support Self (Scavenge)
:white_check_mark: Support Self (Scavenge)

### Zomboss:

:white_check_mark: Attack (Clobber)

:white_check_mark: Special Attack (Apocalypse)

:white_check_mark: Support (Shield)

### Game:

:white_check_mark: Window

:white_check_mark: Textbox

:white_check_mark: Color HP by Percent

:white_check_mark: Draw Party

:white_check_mark: Input

:white_check_mark: Create Random Adventurer

:white_check_mark: Default vs. Selection

:white_check_mark: Invalid Inputs

:white_check_mark: Random Enemy Action

:ballot_box_with_check: Favored Enemy Action

:white_check_mark: Death

:white_check_mark: Win/Lose Screen

:white_check_mark: Quit

:ballot_box_with_check: Name/Class Selection 

:ballot_box_with_check: Quits During Invalid Input Prompters 

:ballot_box_with_check: Death Marker

:ballot_box_with_check: Style Line

:ballot_box_with_check: Default Party

:ballot_box_with_check: Easy (Two Enemy), Medium (Reg Three Enemy), Hard (Boss) Modes


## Adventurer Subclasses

| **Attributes** | **Sunflowers (40 HP)** | **Chomper (changed from CodeWarrior) (25 HP)** | **Zombie (35 HP)** |
| :---------: | :----------------: | :--------------------------------: |:---------------:|
| Special    |sunExposure (15)|ph (14) (note that less pH means better special)|brains (10)|
| Attack     | "Flamethrower": Sunflowers launch themselves at the opponent, dealing 1-3 damage and losing 1-3 HP.|"Fly Trap": The Chomper snatches its enemies in their acidic trap, dealing 3-4 HP and losing 2 pH.|"Devour": The Zombie eats at an opponent, dealing 3 damage and gaining 1 brain.|
| Special Attack | "Sunburn": Sunflowers emanate a bright light, decreasing the opponent’s special by 50% while also dropping their own by 30%.|"Withering": The Chomper spits out acid around it, dealing 14-its current pH level and gaining back (damage-3) pH (pH must be lower than 7). Acid can burn through shields if it is 3 or lower. |"Horde": The Zombie sets out 2-4 brains, luring its zombie friends to come and help it. The zombies attack the opponents, dealing 2 * 2 to 2 * 4 damage (# of zombies lured * 2) |
| Support | "Vitamin D": The ally eats two sunflowers, shielding them from the next attack, while the sunflowers take 2 damage.|"Blooming": The Chomper spits out a glob of acid, restoring 1 special or 1 HP depending on which one is lower.|"CS Test": *If ally is a Zombie:* The Zombie proctors a computer science test for its ally, forcing it to get smarter and gain 3 brains. *If ally is not a Zombie:* The Zombie uses the test as compost, giving its ally 1 special.|
| Support-Self | "Plant":Sunflowers cover the ground with seeds, sprouting new sunflowers and regaining 5-7 HP while also gaining 2 sunExposure| N/A. If called, no action will be taken. |"Scavenge": The Zombie is constantly on the lookout for brains. There is a 75% chance it will spot some and eat them, gaining 4 brains.|

## Boss

| **Attributes** | **Zomboss (45 HP)** |
| :---------: | :----------------: |
| Special    |musclePower (30)|
| Attack     | "Clobber": The Zomboss clubs its opponents on the head, dealing 8-10 damage. This paralyzes the opponent, making them lose 2 special (for Chomper, gains 1 special). The Zomboss also gains 4 musclePower from the workout.|
| Special Attack | "Apocalypse": The Zomboss commands a horde of 8-10 zombies, dealing 2 damage per each zombie and losing 8-10 musclePower. Zombies are careless when they fight, so they damage the Zomboss as well, dealing 2-6 damage to the Zomboss. (musclePower must be greater than 18)|
| Support | "Shield": The Zomboss collects fallen zombie parts to form a shield, hiding behind them while healing itself, regaining 6-8 HP, with a 50% chance it will gain 5 musclePower from the workout of collecting zombies. HP is < 15, the Zomboss will be immune from the next attack.|
