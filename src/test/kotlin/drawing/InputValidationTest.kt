package drawing

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockkObject
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class InputValidationTest {

    @BeforeEach
    fun init() {
        mockkObject(ConsoleDrawer)
        MockKAnnotations.init(this)

        every { ConsoleDrawer.colLimit } returns 21
        every { ConsoleDrawer.rowLimit } returns 21
    }

    @Test
    fun `validate canvas input`() {
        val instance = Factories.validateInstance(ShapeEnum.CANVAS)
        every { ConsoleDrawer.isCanvasDefined() } returns false

        assertThat(instance.validate("C 20 4")).isTrue

        assertThat(instance.validate("X 20 4")).isFalse
        assertThat(instance.validate("C X X")).isFalse
        assertThat(instance.validate("C204")).isFalse
        assertThat(instance.validate("C 20")).isFalse
        assertThat(instance.validate("C 20 4 4")).isFalse
        assertThat(instance.validate("CC 20 4")).isFalse
        assertThat(instance.validate("")).isFalse
        assertThat(instance.validate("C 0 10")).isFalse
        assertThat(instance.validate("C 10 0")).isFalse
    }

    @Test
    fun `validate line input`() {
        val instance = Factories.validateInstance(ShapeEnum.LINE)
        every { ConsoleDrawer.isCanvasDefined() } returns true
        assertThat(instance.validate("L 1 2 6 2")).isTrue
        assertThat(instance.validate("L 6 3 6 4")).isTrue

        assertThat(instance.validate("X 1 2 6 2")).isFalse
        assertThat(instance.validate("L12 62")).isFalse
        assertThat(instance.validate("LL 1 2 6 2")).isFalse
        assertThat(instance.validate("")).isFalse
        assertThat(instance.validate("L 0 2 6 2")).isFalse
        assertThat(instance.validate("L 1 0 6 2")).isFalse
        assertThat(instance.validate("L 1 2 0 2")).isFalse
        assertThat(instance.validate("L 1 2 6 0")).isFalse


    }

    @Test
    fun `validate rectangle input`() {
        val instance = Factories.validateInstance(ShapeEnum.RECTANGLE)
        every { ConsoleDrawer.isCanvasDefined() } returns true
        assertThat(instance.validate("R 16 1 20 3")).isTrue

        assertThat(instance.validate("X 16 1 20 3")).isFalse
        assertThat(instance.validate("RR 16 1 20 3")).isFalse
        assertThat(instance.validate("RR 161 203")).isFalse
        assertThat(instance.validate("")).isFalse
        assertThat(instance.validate("R 0 1 20 3")).isFalse
        assertThat(instance.validate("R 16 0 20 3")).isFalse
        assertThat(instance.validate("R 16 1 0 3")).isFalse
        assertThat(instance.validate("R 16 1 20 0")).isFalse
    }

    @Test
    fun `assert coordinate X2 is higher than X1 rectangle`() {
        val instance = Factories.validateInstance(ShapeEnum.RECTANGLE)
        assertThat(instance.validate("R 20 1 16 3")).isFalse
        assertThat(instance.validate("R 16 3 20 1")).isFalse
    }

    @Test
    fun `assert coordinate X2 is higher than X1 line`() {
        val instance = Factories.validateInstance(ShapeEnum.LINE)
        assertThat(instance.validate("L 6 2 1 2")).isFalse
        assertThat(instance.validate("L 6 4 6 3")).isFalse
    }

    @Test
    fun `assert line can only be vertical or horizontal`() {
        val instance = Factories.validateInstance(ShapeEnum.LINE)
        assertThat(instance.validate("L 1 2 6 3")).isFalse
        assertThat(instance.validate("L 6 3 7 4")).isFalse
    }

    @Test
    fun `assert canvas can only be created once`() {
        val instance = Factories.validateInstance(ShapeEnum.CANVAS)
        every { ConsoleDrawer.isCanvasDefined() } returns false
        assertThat(instance.validate("C 20 4")).isTrue

        every { ConsoleDrawer.isCanvasDefined() } returns true
        assertThat(instance.validate("C 20 4")).isFalse
    }

    @Test
    fun `assert rectangle can only be draw if canvas is defined `() {
        val instance = Factories.validateInstance(ShapeEnum.RECTANGLE)
        every { ConsoleDrawer.isCanvasDefined() } returns true
        assertThat(instance.validate("R 16 1 20 3")).isTrue

        every { ConsoleDrawer.isCanvasDefined() } returns false
        assertThat(instance.validate("R 16 1 20 3")).isFalse
    }

    @Test
    fun `assert line can only be draw if canvas is defined `() {
        val instance = Factories.validateInstance(ShapeEnum.LINE)
        every { ConsoleDrawer.isCanvasDefined() } returns true
        assertThat(instance.validate("L 1 2 6 2")).isTrue
        assertThat(instance.validate("L 6 3 6 4")).isTrue

        every { ConsoleDrawer.isCanvasDefined() } returns false
        assertThat(instance.validate("L 1 2 6 2")).isFalse
        assertThat(instance.validate("L 6 3 6 4")).isFalse
    }

    @Test
    fun `assert rectangle coordinates are inside canvas columns`() {
        val instance = Factories.validateInstance(ShapeEnum.RECTANGLE)
        every { ConsoleDrawer.isCanvasDefined() } returns true
        assertThat(instance.validate("R 16 1 20 3")).isTrue

        every { ConsoleDrawer.colLimit } returns 2
        assertThat(instance.validate("R 16 1 20 3")).isFalse
    }

    @Test
    fun `assert line coordinates are inside canvas columns`() {
        val instance = Factories.validateInstance(ShapeEnum.LINE)
        every { ConsoleDrawer.isCanvasDefined() } returns true
        assertThat(instance.validate("L 1 2 6 2")).isTrue
        assertThat(instance.validate("L 6 3 6 4")).isTrue

        every { ConsoleDrawer.colLimit } returns 2
        assertThat(instance.validate("L 1 2 6 2")).isFalse
        assertThat(instance.validate("L 6 3 6 4")).isFalse
    }

    @Test
    fun `assert rectangle coordinates are inside canvas rows`() {
        val instance = Factories.validateInstance(ShapeEnum.RECTANGLE)
        every { ConsoleDrawer.isCanvasDefined() } returns true
        assertThat(instance.validate("R 16 1 20 3")).isTrue

        every { ConsoleDrawer.rowLimit } returns 2
        assertThat(instance.validate("R 16 1 20 3")).isFalse
    }

    @Test
    fun `assert line coordinates are inside canvas rows`() {
        val instance = Factories.validateInstance(ShapeEnum.LINE)
        every { ConsoleDrawer.isCanvasDefined() } returns true
        assertThat(instance.validate("L 1 2 6 2")).isTrue
        assertThat(instance.validate("L 6 3 6 4")).isTrue

        every { ConsoleDrawer.rowLimit } returns 2
        assertThat(instance.validate("L 1 2 6 2")).isFalse
        assertThat(instance.validate("L 6 3 6 4")).isFalse
    }

    @Test
    fun `assert canvas input cannot be higher than maxSize`() {
        val instance = Factories.validateInstance(ShapeEnum.CANVAS)
        assertThat(instance.validate("C ${ConsoleDrawer.canvasMaxSize} 10")).isFalse
        assertThat(instance.validate("C 10 ${ConsoleDrawer.canvasMaxSize}")).isFalse
    }

    @Test
    fun `assert isExpectedNumberOfArgs`() {
        val instance = Factories.validateInstance(ShapeEnum.CANVAS)
        var expectedNumberOfArgs = instance.isExpectedNumberOfArgs("C 20 4".splitToSequence(' '), 3)
        assertThat(expectedNumberOfArgs).isTrue

        expectedNumberOfArgs = instance.isExpectedNumberOfArgs("C 20 4 5".splitToSequence(' '), 3)
        assertThat(expectedNumberOfArgs).isFalse
    }

    @Test
    fun `assert isCoordinatesInputValid`() {
        val instance = Factories.validateInstance(ShapeEnum.LINE)
        var result = instance.isCoordinatesInputValid("L 1 2 6 2".splitToSequence(' '))
        assertThat(result).isTrue

        result = instance.isCoordinatesInputValid("L 1 2 6".splitToSequence(' '))
        assertThat(result).isFalse

        result = instance.isCoordinatesInputValid("L X 2 6 2".splitToSequence(' '))
        assertThat(result).isFalse

        result = instance.isCoordinatesInputValid("L 6 2 1 2".splitToSequence(' '))
        assertThat(result).isFalse

        every { ConsoleDrawer.colLimit } returns 2
        every { ConsoleDrawer.rowLimit } returns 2
        result = instance.isCoordinatesInputValid("L 1 2 6 2".splitToSequence(' '))
        assertThat(result).isFalse
    }

    @Test
    fun `assert validateAllInts`() {
        val instance = Factories.validateInstance(ShapeEnum.LINE)
        var result = instance.validateAllInts("L 1 2 6 2".splitToSequence(' '), 5)
        assertThat(result).isTrue

        result = instance.validateAllInts("L X 2 6 2".splitToSequence(' '), 5)
        assertThat(result).isFalse

        result = instance.validateAllInts("L 0 2 6 2".splitToSequence(' '), 5)
        assertThat(result).isFalse
    }

    @Test
    fun `assert isValidLineOrientation`() {
        val instance = Factories.validateInstance(ShapeEnum.LINE)
        var result = instance.isValidLineOrientation("L 1 2 6 2".splitToSequence(' '))
        assertThat(result).isTrue

        result = instance.isValidLineOrientation("L 1 2 6 1".splitToSequence(' '))
        assertThat(result).isFalse

    }

    @Test
    fun `assert isValidChar`() {
        val instance = Factories.validateInstance(ShapeEnum.LINE)
        var result = instance.isValidChar("L", 'L')
        assertThat(result).isTrue

        result = instance.isValidChar("L", 'R')
        assertThat(result).isFalse

    }
}
