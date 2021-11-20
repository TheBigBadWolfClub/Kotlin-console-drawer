package drawing

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ShapeTest {

    private lateinit var shape: Shape

    @BeforeEach
    fun init() {
    }

    @Test
    fun `shape from line input`() {
        shape = Shape("L 1 2 6 2")
        Assertions.assertThat(shape.shape).isEqualTo(ShapeEnum.LINE)
        Assertions.assertThat(shape.p1).isEqualTo(Pair(1, 2))
        Assertions.assertThat(shape.p2).isEqualTo(Pair(6, 2))
    }

    @Test
    fun `shape from rectangle input`() {
        shape = Shape("R 16 1 20 3")
        Assertions.assertThat(shape.shape).isEqualTo(ShapeEnum.RECTANGLE)
        Assertions.assertThat(shape.p1).isEqualTo(Pair(16, 1))
        Assertions.assertThat(shape.p2).isEqualTo(Pair(20, 3))
    }

    @Test
    fun `shape from canvas input`() {
        shape = Shape("C 20 4")
        Assertions.assertThat(shape.shape).isEqualTo(ShapeEnum.CANVAS)
        Assertions.assertThat(shape.p1).isEqualTo(Pair(0, 0))
        Assertions.assertThat(shape.p2).isEqualTo(Pair(21, 5))
    }
}
