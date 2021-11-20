package drawing

import App
import io.mockk.*
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class AppTest
{

    private lateinit var app: App

    @BeforeEach
    fun init() {
        app = App()
        mockkObject(ConsoleDrawer)
    }

    @Test
    fun `test valid input for Canvas`() {
        every { ConsoleDrawer.isCanvasDefined() } returns false
        val input = "C 20 4"
        val testValidInput = app.validateInput(input)
        Assertions.assertThat(testValidInput).isTrue
    }

    @Test
    fun `test valid input for Line`() {
        every { ConsoleDrawer.isCanvasDefined() } returns true
        every { ConsoleDrawer.colLimit } returns 100
        every { ConsoleDrawer.rowLimit } returns 100
        val input = "L 1 2 6 2"
        val testValidInput = app.validateInput(input)
        Assertions.assertThat(testValidInput).isTrue
    }

    @Test
    fun `test valid input for Rectangle`() {
        every { ConsoleDrawer.isCanvasDefined() } returns true
        every { ConsoleDrawer.colLimit } returns 100
        every { ConsoleDrawer.rowLimit } returns 100
        val input = "R 16 1 20 3"
        val testValidInput = app.validateInput(input)
        Assertions.assertThat(testValidInput).isTrue
    }

    @Test
    fun `test invalid input for default`() {
        val input = "X 16 1 20 3"
        val testValidInput = app.validateInput(input)
        Assertions.assertThat(testValidInput).isFalse
    }

    @Test
    fun `test canvas maxSize override`() {
        every { ConsoleDrawer.canvasMaxSize } returns 101
        app.setCanvasMaxSize(arrayOf("101"))
        Assertions.assertThat(ConsoleDrawer.canvasMaxSize).isEqualTo(101)
    }

    @Test
    fun `verify canvas is set`() {
        val canvas = "C 20 4 4 4"
        every { ConsoleDrawer.rowLimit } returns 5
        every { ConsoleDrawer.colLimit } returns 21
        every { ConsoleDrawer.points } returns mutableMapOf(Pair(Pair(1,1), Canvas.hChar))
        app.processInputAndDraw(canvas)
        Assertions.assertThat(ConsoleDrawer.rowLimit).isEqualTo(5)
        Assertions.assertThat(ConsoleDrawer.colLimit).isEqualTo(21)
        Assertions.assertThat(ConsoleDrawer.points).isNotEmpty
        verify { ConsoleDrawer.draw() }
    }
}
