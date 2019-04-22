package colliders

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import shootemup.geometry.*
import shootemup.collision.*

class APointCollider {
    @Test
    fun mustSpanAtLeastOneDimension() = assertThrows<IllegalArgumentException>({
        PointCollider(vectorOf())
    })

}
