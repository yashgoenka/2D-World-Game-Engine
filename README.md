# BYoG-Game-Engine
An engine for generating unique explorable worlds.

Specifications for the world:
<ul>
  <li>The world must be a 2D grid, drawn using our tile engine.</li>
  <li>The world must be pseudorandomly generated.</li>
  <li>The generated world must include rooms and hallways, though it may also include outdoor spaces.</li>
  <li>At least some rooms should be rectangular, though you may support other shapes as well.</li>
  <li>Your game must be capable of generating hallways that include turns (or equivalently, straight hallways that intersect).</li>
  <li>The world should contain a random number of rooms and hallways.</li>
  <li>The locations of the rooms and hallways should be random.</li>
  <li>The width and height of rooms should be random.</li>
  <li>The length of hallways should be random.</li>
  <li>Rooms and hallways must have walls that are visually distinct from floors. Walls and floors should be visually distinct from unused spaces.</li>
  <li>Rooms and hallways should be connected, i.e. there should not be gaps in the floor between adjacent rooms or hallways.</li>
  <li>The world should be substantially different each time, i.e. you should not have the same basic layout with easily predictable features</li>
</ul>

Try out the game built using this engine!
