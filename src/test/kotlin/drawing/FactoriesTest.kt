package drawing

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FactoriesTest {


    @Test
    fun `verify CanvasValidation instance`() {
        val instance = Factories.validateInstance(ShapeEnum.CANVAS)
        assertThat(instance is CanvasValidation).isTrue
    }


    @Test
    fun `verify LineValidation instance`() {
        val instance = Factories.validateInstance(ShapeEnum.LINE)
        assertThat(instance is LineValidation).isTrue
    }

    @Test
    fun `verify RectangleValidation instance`() {
        val instance = Factories.validateInstance(ShapeEnum.RECTANGLE)
        assertThat(instance is RectangleValidation).isTrue
    }

    @Test
    fun `verify InvalidInput instance`() {
        val instance = Factories.validateInstance(ShapeEnum.NO_SHAPE)
        assertThat(instance is InvalidInput).isTrue
    }

    @Test
    fun `verify Canvas instance`() {
        val instance = Factories.getPointsCalculator(ShapeEnum.CANVAS)
        assertThat(instance is Canvas).isTrue
    }


    @Test
    fun `verify Line instance`() {
        val instance = Factories.getPointsCalculator(ShapeEnum.LINE)
        assertThat(instance is Line).isTrue
    }

    @Test
    fun `verify Rectangle instance`() {
        val instance = Factories.getPointsCalculator(ShapeEnum.RECTANGLE)
        assertThat(instance is Rectangle).isTrue
    }


}
