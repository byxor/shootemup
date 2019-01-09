package colliders

import org.junit.Assert.*
import org.junit.*
import shootemup.*

class APointCollider {
    @Test(expected = IllegalArgumentException::class)
    fun mustSpanAtLeastOneDimension() {
        PointCollider(vectorOf())
    }
}
