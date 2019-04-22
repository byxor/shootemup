package collisions

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import shootemup.geometry.*
import shootemup.collision.*

class APointToCircleCollision {

    @Test
    fun occursWhenThePointIsInTheCircle() {
        listOf(
                T(vectorOf(0), 1, vectorOf(0)),
                T(vectorOf(99), 1, vectorOf(99)),

                T(vectorOf(0), 2, vectorOf(0)),
                T(vectorOf(0), 2, vectorOf(1)),
                T(vectorOf(0), 2, vectorOf(-1)),

                T(vectorOf(5), 10, vectorOf(-4)),
                T(vectorOf(5), 10, vectorOf(-3)),
                T(vectorOf(5), 10, vectorOf(-2)),
                T(vectorOf(5), 10, vectorOf(-1)),
                T(vectorOf(5), 10, vectorOf(0)),
                T(vectorOf(5), 10, vectorOf(1)),
                T(vectorOf(5), 10, vectorOf(2)),
                T(vectorOf(5), 10, vectorOf(3)),
                T(vectorOf(5), 10, vectorOf(4)),
                T(vectorOf(5), 10, vectorOf(5)),
                T(vectorOf(5), 10, vectorOf(6)),
                T(vectorOf(5), 10, vectorOf(7)),
                T(vectorOf(5), 10, vectorOf(8)),
                T(vectorOf(5), 10, vectorOf(9)),
                T(vectorOf(5), 10, vectorOf(10)),
                T(vectorOf(5), 10, vectorOf(11)),
                T(vectorOf(5), 10, vectorOf(12)),
                T(vectorOf(5), 10, vectorOf(13)),
                T(vectorOf(5), 10, vectorOf(14)),

                T(vectorOf(0, 0), 1, vectorOf(0, 0)),

                T(vectorOf(-10, 0), 100, vectorOf(60, 60)),
                T(vectorOf(-10, 0), 100, vectorOf(43, 22)),
                T(vectorOf(-1000, 0), 100, vectorOf(-940, 29))
        ).forEach {
            val c1 = CircleCollider(it.circlePosition, it.circleRadius)
            val c2 = PointCollider(it.pointPosition)
            assertTrue(isColliding(c1, c2))
            assertTrue(isColliding(c2, c1))
        }
    }

    @Test
    fun doesNotOccurWhenThePointIsOutsideTheCircle() {
        listOf(
                T(vectorOf(0), 1, vectorOf(1)),
                T(vectorOf(0), 1, vectorOf(-1)),
                T(vectorOf(0), 1, vectorOf(2)),
                T(vectorOf(0), 1, vectorOf(-2)),

                T(vectorOf(10), 1, vectorOf(11)),
                T(vectorOf(10), 1, vectorOf(9)),

                T(vectorOf(5), 10, vectorOf(-5)),
                T(vectorOf(5), 10, vectorOf(-6)),
                T(vectorOf(5), 10, vectorOf(15)),
                T(vectorOf(5), 10, vectorOf(16)),

                T(vectorOf(0, 0), 1, vectorOf(0, 1)),
                T(vectorOf(0, 0), 1, vectorOf(0, -1)),
                T(vectorOf(0, 0), 1, vectorOf(-1, 0)),
                T(vectorOf(0, 0), 1, vectorOf(1, 0)),
                T(vectorOf(0, 0), 1, vectorOf(-1, 1)),
                T(vectorOf(0, 0), 1, vectorOf(-1, -1)),
                T(vectorOf(0, 0), 1, vectorOf(1, -1)),
                T(vectorOf(0, 0), 1, vectorOf(1, 1)),

                T(vectorOf(0, 0), 100, vectorOf(95, 95)),
                T(vectorOf(-10, 0), 100, vectorOf(85, 95))
        ).forEach {
            val c1 = CircleCollider(it.circlePosition, it.circleRadius)
            val c2 = PointCollider(it.pointPosition)
            assertFalse(isColliding(c1, c2))
            assertFalse(isColliding(c2, c1))
        }
    }

    private data class T(val circlePosition: Vector,
                         val circleRadius: Int,
                         val pointPosition: Vector)
}
