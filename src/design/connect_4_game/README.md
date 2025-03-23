## Connect 4 Game:

### Design with Design Pattern:
1. **Board class:** Will still represent the game grid but we will use the **Singleton Pattern** to ensure only one board instance exists.
2. **Player Class:** Will remain simple but will use **Factory Pattern** to create players.
3. **Game Class:** Will implement the **Strategy Pattern** for handling with conditions, allowing flexible win-condition checks (horizontal, vertical, diagonal).
4. **Turn Management:** Will implement the **Observer Pattern** to notify the game of the player's move.
5. **Game Controller:** Implement the **Facade Pattern** to simplify the interface for starting and managing the game.
