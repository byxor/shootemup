import org.junit.Assert.*
import org.junit.*
import shootemup.*

class AProjectile {

    @Test
    fun mustHaveConsistentDimensions() {
        data class T(val position: Vector, val velocity: Vector)
        listOf(
                T(listOf(0),          listOf(0, 0)),
                T(listOf(0, 0),       listOf(0)),
                T(listOf(1, 2, 3, 4), listOf(9, 8, 7, 6, 5))
        ).forEach({
            try {
                Projectile(it.position, it.velocity)
                assertFalse(true)
            } catch(e: IllegalArgumentException) {}
        })
    }

    @Test
    fun willNotMoveWhenItsVelocityIsZero() {
        listOf(
                listOf(0),
                listOf(1),
                listOf(0, 0),
                listOf(1, 2, 3)
        ).forEach({ position ->
            val velocity = MutableList(position.size) {0}
            val projectile = Projectile(position, velocity)
            val next = projectile.travel()
            assertEquals(projectile.position, next.position)
        })
    }

    @Test
    fun willTravelToTheExpectedPosition() {
        data class T(val velocity: Vector,
                     val expectedFirst: Vector,
                     val expectedSecond: Vector)

        listOf(
                // 1-Dimensional
                T(listOf(1),     listOf(1),     listOf(2)),
                T(listOf(10),    listOf(10),    listOf(20)),
                T(listOf(15),    listOf(15),    listOf(30)),
                T(listOf(-1),    listOf(-1),    listOf(-2)),
                T(listOf(-1000), listOf(-1000), listOf(-2000)),

                // 2-Dimensional
                T(listOf(1, 1),  listOf(1, 1),  listOf(2, 2)),
                T(listOf(0, 1),  listOf(0, 1),  listOf(0, 2)),
                T(listOf(0, 10), listOf(0, 10), listOf(0, 20)),
                T(listOf(-5, 1), listOf(-5, 1), listOf(-10, 2)),

                // 3-Dimensional
                T(listOf(1, 1, 1),    listOf(1, 1, 1),    listOf(2, 2, 2)),
                T(listOf(90, -15, 3), listOf(90, -15, 3), listOf(180, -30, 6))
        ).forEach({
            val position = MutableList(it.velocity.size) {0}
            val projectile = Projectile(position, it.velocity)

            val next = projectile.travel()
            assertEquals(it.expectedFirst, next.position)

            val nextNext = next.travel()
            assertEquals(it.expectedSecond, nextNext.position)
        })
    }
}
