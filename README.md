# Kotlin drawer

A quick project to lear kotlin basics


### Build and Run

- build a jar to be executed in jvm 1.8+
```shell script
// windows:
gradlew.bat clean build

// Linux:
gradlew clean build

```
<br>

- execute
```shell script

// with default max canvas size 100
java -jar build/libs/console-drawer-0.0.1-all.jar

// to change the max canvas size from 100 to desire value
java -jar build/libs/console-drawer-0.0.1-all.jar "50"

```

#Description

You're given the task of writing a simple console version of a drawing program. The functionality of the
program is quite limited but should be extensible. The program should work as follows:
1. create a new canvas.
2. start drawing on the canvas by issuing various commands.
3. quit.
   The program should support the following commands:
   Command Description
  

### Input: C w h 
Should create a new canvas of width w and height h.



### Input: L x1 y1 x2 y2
Should create a new line from (x1,y1) to (x2,y2) . Currently only
horizontal or vertical lines are supported. Horizontal and vertical lines will be
drawn using the x character.

### Input: R x1 y1 x2 y2

Should create a new rectangle, whose upper left corner is (x1,y1) and
lower right corner is (x2,y2) . Horizontal and vertical lines will be drawn
using the x character.


### Input: Q 
Should quit the program.


##Sample I/O


```shell

input> C 20 4
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------


input> L 1 2 6 2
----------------------
|                    |
|xxxxxx              |
|                    |
|                    |
----------------------


input> L 6 3 6 4
----------------------
|                    |
|xxxxxx              |
|     x              |
|     x              |
----------------------


input> R 16 1 20 3
----------------------
|               xxxxx|
|xxxxxx         x   x|
|     x         xxxxx|
|     x              |
----------------------


input> Q
```

# Implementation

### 1 Development process

#### 1.1 Languages, tools and frameworks

- Gradle
- Kotlin
- Junit5
- Mockk
- AssertJ

#### 1.2 Development workflow

"<i>write some failing tests => write code to pass the tests => refactor => add more tests ...</i>"

Following a TDD process, I start by writing tests to validate the specs.

##### 1.2.1 
- write tests and implement the code to validate user input
    - "C 20 4"
    - "L 1 2 6 2"
    - "L 6 3 6 4"
    - "R 16 1 20 3"
    - add tests and code to prevent invalid inputs

also provide error message to user if input is invalid

##### 1.2.2 
- write tests and implement the code to print canvas and shape to console
    - use a mutableMap to keep record of coordinates calculated from user input
    - print to console using loops, a common way to compute matrix's systems


```kotlin
// kotlin/drawing/ConsoleDrawer.kt
fun draw() {
        (0 .. rowLimit).asSequence()
            .forEach { line ->
                run {
                    (0 .. colLimit).asSequence()
                        .forEach { col -> print(points.getOrDefault(Pair(col, line), ' ')) }
                    println()
                }
            }
        println()
    }
```
<br/>


##### 1.2.3
 - Convert user input to coordinate points to be stored in a mutableMap

```kotlin
// Initial code to calculate all points of a string (horizontal)
fun line(input: String): Map<Pair<Int, Int>, Char> {
        val values = input.trim().splitToSequence(' ')
        val startX = values.elementAt(1).toInt()
        val startY = values.elementAt(2).toInt()
        val endX = values.elementAt(3).toInt()
        val endY = values.elementAt(4).toInt()

        return (startX..endX)
            .asSequence()
            .map { x -> Pair(Pair(x, startY), 'x') }
            .toMap()
    }
```
<br>
<br>
<i>canvas</i> and <i>rectangles</i> are shapes that can be build from lines.<br>
a class to build lines(V/H) has been created to support the calculation of all coordinates necessary to draw this shapes

```kotlin
package drawing

class LineHelper constructor(val x1: Int, val y1: Int, val x2: Int, val y2: Int, var char: Char) {
    fun getPoints(): Sequence<Pair<Pair<Int, Int>, Char>> {
        return getRange().asSequence().map { position -> buildPoint(position) }
    }

    private fun isLineHorizontal(): Boolean = y1 == y2
    private fun getLinePosition(): Int = if (isLineHorizontal()) y1 else x1
    private fun getRange(): IntRange = if (isLineHorizontal()) (x1..x2) else (y1..y2)

    private fun buildPoint(position: Int): Pair<Pair<Int, Int>, Char> =
        if (isLineHorizontal()) Pair(Pair(position, getLinePosition()), char)
        else Pair(Pair(getLinePosition(), position), char)
}
``` 
<br>

Generic fun used by canvas and rectangles to calculate all their printing points
```kotlin
// kotlin/drawing/PointCalculator.kt

// ups, some magic number's in code
 private fun genericRectangle(shape: Shape, xChar: Char, yChar: Char): Map<Pair<Int, Int>, Char> {
        return sequenceOf(
            LineHelper(shape.p1.first, shape.p1.second, shape.p2.first, shape.p1.second, xChar).getPoints(),
            LineHelper(shape.p2.first, shape.p1.second + 1, shape.p2.first, shape.p2.second - 1, yChar).getPoints(),
            LineHelper(shape.p1.first, shape.p2.second, shape.p2.first, shape.p2.second, xChar).getPoints(),
            LineHelper(shape.p1.first, shape.p1.second + 1, shape.p1.first, shape.p2.second - 1, yChar).getPoints()
        ).flatten().toMap()
    }


```
<br>


##### 1.2.4
 - Refactor - Add more Tests - Refactor - ... - and glue all together
    - read input
    - validate input
    - calculate points, add them to mutableMap
    - draw()


TheBigBadWolf @2018
