package drawing

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream

class InputReaderTest {
    private val systemIn = System.`in`
    private lateinit var reader: InputReader

    @BeforeEach
    fun init() {
        reader = InputReader()
    }

    @AfterEach
    fun tearDown() {
        System.setIn(systemIn)
    }

    @Test
    fun `assert working user input`() {
        val byteArray = ByteArrayInputStream("C 20 4".toByteArray())
        System.setIn(byteArray)
        val userInput: String = reader.readInput()
        Assertions.assertThat(userInput).isNotNull
        Assertions.assertThat(userInput).isNotEmpty
        Assertions.assertThat(userInput).isEqualTo("C 20 4")
    }

    @Test
    fun `assert user input never null`() {
        val byteArray = ByteArrayInputStream("C 20 4".toByteArray())
        System.setIn(byteArray)
        val userInput = reader.readInput()
        Assertions.assertThat(userInput).isNotNull
        Assertions.assertThat(userInput).isNotEmpty
        Assertions.assertThat(userInput).isEqualTo("C 20 4")

        val nullInput = reader.readInput()
        Assertions.assertThat(nullInput).isNullOrEmpty()
    }

    @Test
    fun `assert input is trimmed`() {
        val byteArray = ByteArrayInputStream("  C   20   4  ".toByteArray())
        System.setIn(byteArray)
        val userInput: String = reader.readInput()
        Assertions.assertThat(userInput).isNotNull
        Assertions.assertThat(userInput).isNotEmpty
        Assertions.assertThat(userInput).isEqualTo("C 20 4")
    }

    @Test
    fun `assert input is converted to uppercase`() {
        val byteArray = ByteArrayInputStream("c 20 4 ".toByteArray())
        System.setIn(byteArray)
        val userInput: String = reader.readInput()
        Assertions.assertThat(userInput).isNotNull
        Assertions.assertThat(userInput).isNotEmpty
        Assertions.assertThat(userInput).isEqualTo("C 20 4")
    }
}
