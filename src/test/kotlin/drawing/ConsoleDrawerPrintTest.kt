package drawing

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ConsoleDrawerPrintTest {
    private val standardOut = System.out
    private val outputStreamCaptor = ByteArrayOutputStream()

    private lateinit var drawer: ConsoleDrawer

    @BeforeEach
    fun init() {
        outputStreamCaptor.reset()
        System.setOut(PrintStream(outputStreamCaptor))
        drawer = ConsoleDrawer
        ConsoleDrawer.rowLimit = 10
        ConsoleDrawer.colLimit = 10

    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
    }

    @Test
    fun `is cable of drawing and empty canvas`() {
        ConsoleDrawer.draw()
        val output = outputStreamCaptor.toString()
        val rows: List<String> = output.lines().filter { l -> l.isNotEmpty() }
        Assertions.assertThat(rows.size).isEqualTo(11)

        rows.forEach { r -> Assertions.assertThat(r.length).isEqualTo(11) }
    }

    @Test
    fun `is cable of drawing top canvas`() {
        (0 .. 10).asSequence().forEach { it -> ConsoleDrawer.points[Pair(it, 0)] = Canvas.hChar }
        ConsoleDrawer.draw()

        val output = outputStreamCaptor.toString()
        val rows: List<String> = output.lines().filter { l -> l.isNotEmpty() }
        Assertions.assertThat(rows.size).isEqualTo(11)
        Assertions.assertThat(rows[0].length).isEqualTo(11)
        Assertions.assertThat(rows[0].filter { it == Canvas.hChar }.count()).isEqualTo(11)

    }

}
