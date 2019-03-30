package demo

import org.junit.Test
import kotlin.test.assertEquals

class HelloWorldTest {
    @Test
    fun `should say Hello`() {
        assertEquals("Hello Kotlin World!", sayHello())
    }
}