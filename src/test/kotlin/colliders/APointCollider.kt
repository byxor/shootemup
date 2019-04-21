package colliders

import org.junit.Assert.*
import org.junit.*
import shootemup.geometry.*
import shootemup.collision.*

class APointCollider {
    @Test(expected = IllegalArgumentException::class)
    fun mustSpanAtLeastOneDimension() {
        PointCollider(vectorOf())
    }
}
