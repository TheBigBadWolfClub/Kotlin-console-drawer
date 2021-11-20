package drawing

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LineHelperTest {

    @Test
    fun `calculate horizontal line points`() {
        val points: Map<Pair<Int, Int>, Char> = LineHelper(1, 2, 2, 2, Line.char).getPoints().toMap()
        Assertions.assertThat(points).containsExactly(
            Assertions.entry(Pair(1, 2), Line.char),
            Assertions.entry(Pair(2, 2), Line.char),
        )
    }

    @Test
    fun `calculate vertical line points`() {
        val points: Map<Pair<Int, Int>, Char> = LineHelper(6, 3, 6, 4, Line.char).getPoints().toMap()
        Assertions.assertThat(points).containsExactly(
            Assertions.entry(Pair(6, 3), Line.char),
            Assertions.entry(Pair(6, 4), Line.char),
        )
    }
}
