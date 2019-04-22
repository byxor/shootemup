package colliders

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shootemup.geometry.*
import shootemup.collision.*

class ACircleCollider {

    @Test
    fun mustHaveAPositiveRadius() {
        val radii = (-100)..(-1)

        radii.forEach { radius ->
            assertThrows<IllegalArgumentException>("foo", {
                val position = vectorOf(0)
                CircleCollider(position, radius)             
            })
        }
    }
}
