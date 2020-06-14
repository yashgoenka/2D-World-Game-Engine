# BYoG-Game-Engine

<img src="https://github.com/yashgoenka/BYoG-Game-Engine/blob/master/2dworld.gif" height="240" width="480">

An engine for generating unique explorable 2D worlds using deterministic algorithms in Java with aditional features such as interactivity.

Specifications for the world:
<ul>
  <li>The world is a 2D grid, drawn using Princeton TETile engine.</li>
  <li>The world is pseudorandomly generated.</li>
  <li>The generated world always includes rooms and hallways, though it may also include outdoor spaces.</li>
  <li>At least some rooms will be rectangular, though they may be other shapes as well.</li>
  <li>The game is capable of generating hallways that include turns (or equivalently, straight hallways that intersect).</li>
  <li>The world contains a random number of rooms and hallways.</li>
  <li>The locations of the rooms and hallways is random.</li>
  <li>The width and height of rooms is random.</li>
  <li>The length of hallways is random.</li>
  <li>Rooms and hallways have walls that are visually distinct from floors. Walls and floors are visually distinct from unused spaces.</li>
  <li>Rooms and hallways are connected, i.e. there are no gaps in the floor between adjacent rooms or hallways.</li>
  <li>The world is substantially different each time, i.e. it will not have the same basic layout with easily predictable features</li>
</ul>

Try out the game built using this engine!
