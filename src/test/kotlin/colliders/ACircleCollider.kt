package colliders

import org.junit.Assert.*
import org.junit.*
import shootemup.geometry.*
import shootemup.collision.*

class ACircleCollider {

    @Test
    fun mustHaveAPositiveRadius() {
        listOf(
                vectorOf(0),
                vectorOf(0, 1),
                vectorOf(0, 1, 2),
                vectorOf(-1, -2, 0),
                vectorOf(10000, 20000, 30000)
        ).forEach { position ->
            listOf(-1, -2, -3, -4, -5, -6).forEach { radius ->
                try {
                    CircleCollider(position, radius)
                    assertFalse(true)
                } catch (e: IllegalArgumentException) {}
            }
        }
    }
}
