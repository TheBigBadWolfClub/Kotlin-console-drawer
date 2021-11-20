package drawing

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ConsoleDrawerTest {

    @BeforeEach
    fun init() {
        ConsoleDrawer.rowLimit = 0
        ConsoleDrawer.colLimit = 0
        ConsoleDrawer.points.clear()
    }

    @Test
    fun `drawer init state`() {
        Assertions.assertThat(ConsoleDrawer.points).isEmpty()
        Assertions.assertThat(ConsoleDrawer.rowLimit).isEqualTo(0)
        Assertions.assertThat(ConsoleDrawer.colLimit).isEqualTo(0)
    }

    @Test
    fun `drawer set limits`() {
        Assertions.assertThat(ConsoleDrawer.rowLimit).isEqualTo(0)
        Assertions.assertThat(ConsoleDrawer.colLimit).isEqualTo(0)
        ConsoleDrawer.rowLimit = 10
        ConsoleDrawer.colLimit = 10
        Assertions.assertThat(ConsoleDrawer.rowLimit).isEqualTo(10)
        Assertions.assertThat(ConsoleDrawer.colLimit).isEqualTo(10)
    }

    @Test
    fun `add point to drawer`() {
        Assertions.assertThat(ConsoleDrawer.points).isEmpty()
        ConsoleDrawer.points[Pair(0, 0)] = Canvas.hChar
        Assertions.assertThat(ConsoleDrawer.points.size).isEqualTo(1)
    }

    /**
     * TODO: this test is failing randomly
     */
//    @Test()
//    fun `verify canvas is defined`() {
//        ConsoleDrawer.rowLimit = 10
//        ConsoleDrawer.colLimit = 10
//        Assertions.assertThat(ConsoleDrawer.isCanvasDefined()).isTrue
//    }

    @Test()
    fun `verify canvas is NOT defined`() {
        ConsoleDrawer.rowLimit = 0
        ConsoleDrawer.colLimit = 0
        Assertions.assertThat(ConsoleDrawer.isCanvasDefined()).isFalse
    }

    @Test
    fun `draw canvas`() {
        ConsoleDrawer.draw()
    }

}
