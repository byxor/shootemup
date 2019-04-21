package collisions

import org.junit.Assert.*
import org.junit.*
import shootemup.geometry.*
import shootemup.collision.*

class APointToPointCollision {

    @Test
    fun occursWhenThePositionsAreTheSame() {
        listOf(
                vectorOf(0),
                vectorOf(1),
                vectorOf(2),
                vectorOf(0, 0),
                vectorOf(9999, 8888),
                vectorOf(0, 8888),
                vectorOf(-1, -1, -1, -1, -1, -1, -1, -2, -3)
        ).forEach { position ->
            val c1 = PointCollider(position)
            assertTrue(isColliding(c1, c1))
        }
    }

    @Test
    fun doesNotOccurWhenThePositionsAreDifferent() {
        listOf(
                T(vectorOf(0), vectorOf(1)),
                T(vectorOf(0), vectorOf(-1)),
                T(vectorOf(0), vectorOf(100)),
                T(vectorOf(50), vectorOf(49)),
                T(vectorOf(50), vectorOf(40)),
                T(vectorOf(50), vectorOf(51)),
                T(vectorOf(50), vectorOf(70))
        ).forEach {
            val c1 = PointCollider(it.position1)
            val c2 = PointCollider(it.position2)
            assertFalse(isColliding(c1, c2))
            assertFalse(isColliding(c2, c1))
        }
    }

    @Test
    fun sometimesOccursWithAPointColliderInAHigherDimension() {
        listOf(
                T(vectorOf(0), vectorOf(0, 1)),
                T(vectorOf(10), vectorOf(10, -2)),
                T(vectorOf(1, 2, 3), vectorOf(1, 2, 3, 0, 0, 0, 0, 0, 1))
        ).forEach {
            val c1 = PointCollider(it.position1)
            val c2 = PointCollider(it.position2)
            assertFalse(isColliding(c1, c2))
            assertFalse(isColliding(c2, c1))
        }

        listOf(
                T(vectorOf(0), vectorOf(0, 0)),
                T(vectorOf(10), vectorOf(10, 0)),
                T(vectorOf(1, 2, 3), vectorOf(1, 2, 3, 0, 0, 0, 0, 0, 0))
        ).forEach {
            val c1 = PointCollider(it.position1)
            val c2 = PointCollider(it.position2)
            assertTrue(isColliding(c1, c2))
            assertTrue(isColliding(c2, c1))
        }
    }

    @Test
    fun sometimesOccursWithAPointColliderInALowerDimension() {
        listOf(
                T(vectorOf(0, 1), vectorOf(0)),
                T(vectorOf(10, -2), vectorOf(10)),
                T(vectorOf(1, 2, 3, 0, 0, 0, 0, 0, 1), vectorOf(1, 2, 3))
        ).forEach {
            val c1 = PointCollider(it.position1)
            val c2 = PointCollider(it.position2)
            assertFalse(isColliding(c1, c2))
            assertFalse(isColliding(c2, c1))
        }

        listOf(
                T(vectorOf(0, 0), vectorOf(0)),
                T(vectorOf(10, 0), vectorOf(10)),
                T(vectorOf(1, 2, 3, 0, 0, 0, 0, 0, 0), vectorOf(1, 2, 3))
        ).forEach {
            val c1 = PointCollider(it.position1)
            val c2 = PointCollider(it.position2)
            assertTrue(isColliding(c1, c2))
            assertTrue(isColliding(c2, c1))
        }
    }

    private data class T(val position1: Vector,
                         val position2: Vector)
}
