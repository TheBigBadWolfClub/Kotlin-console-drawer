package drawing

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PointCalculatorTest {


    @Test
    fun `calculate horizontal line points`() {
        val instance = Factories.getPointsCalculator(ShapeEnum.LINE)
        val points: Map<Pair<Int, Int>, Char> = instance.process(Shape("L 1 2 6 2"))
        Assertions.assertThat(points).containsOnly(
            Assertions.entry(Pair(1, 2), Line.char),
            Assertions.entry(Pair(2, 2), Line.char),
            Assertions.entry(Pair(3, 2), Line.char),
            Assertions.entry(Pair(4, 2), Line.char),
            Assertions.entry(Pair(5, 2), Line.char),
            Assertions.entry(Pair(6, 2), Line.char)
        )
    }

    @Test
    fun `calculate vertical line points`() {
        val instance = Factories.getPointsCalculator(ShapeEnum.LINE)
        val points: Map<Pair<Int, Int>, Char> = instance.process(Shape("L 6 3 6 4"))
        Assertions.assertThat(points).containsOnly(
            Assertions.entry(Pair(6, 3), Line.char),
            Assertions.entry(Pair(6, 4), Line.char),
        )
    }

    @Test
    fun `calculate rectangle points`() {
        val instance = Factories.getPointsCalculator(ShapeEnum.RECTANGLE)
        val points: Map<Pair<Int, Int>, Char> = instance.process(Shape("R 16 1 20 3"))
        Assertions.assertThat(points).containsOnly(
            // top
            Assertions.entry(Pair(16, 1), Rectangle.char),
            Assertions.entry(Pair(17, 1), Rectangle.char),
            Assertions.entry(Pair(18, 1), Rectangle.char),
            Assertions.entry(Pair(19, 1), Rectangle.char),
            Assertions.entry(Pair(20, 1), Rectangle.char),

            // right
            Assertions.entry(Pair(16, 2), Rectangle.char),

            // bottom
            Assertions.entry(Pair(16, 3), Rectangle.char),
            Assertions.entry(Pair(17, 3), Rectangle.char),
            Assertions.entry(Pair(18, 3), Rectangle.char),
            Assertions.entry(Pair(19, 3), Rectangle.char),
            Assertions.entry(Pair(20, 3), Rectangle.char),

            // left
            Assertions.entry(Pair(20, 2), Rectangle.char),
        )
    }

    @Test
    fun `calculate canvas points`() {
        val instance = Factories.getPointsCalculator(ShapeEnum.CANVAS)
        val points: Map<Pair<Int, Int>, Char> = instance.process(Shape("C 4 2"))
        Assertions.assertThat(points).containsOnly(
            // top
            Assertions.entry(Pair(0, 0), Canvas.hChar),
            Assertions.entry(Pair(1, 0), Canvas.hChar),
            Assertions.entry(Pair(2, 0), Canvas.hChar),
            Assertions.entry(Pair(3, 0), Canvas.hChar),
            Assertions.entry(Pair(4, 0), Canvas.hChar),
            Assertions.entry(Pair(5, 0), Canvas.hChar),

            // right
            Assertions.entry(Pair(5, 1), Canvas.vChar),
            Assertions.entry(Pair(5, 2), Canvas.vChar),

            // bottom
            Assertions.entry(Pair(0, 3), Canvas.hChar),
            Assertions.entry(Pair(1, 3), Canvas.hChar),
            Assertions.entry(Pair(2, 3), Canvas.hChar),
            Assertions.entry(Pair(3, 3), Canvas.hChar),
            Assertions.entry(Pair(4, 3), Canvas.hChar),
            Assertions.entry(Pair(5, 3), Canvas.hChar),

            // left
            Assertions.entry(Pair(0, 1), Canvas.vChar),
            Assertions.entry(Pair(0, 2), Canvas.vChar),
        )
    }
}
