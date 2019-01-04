import org.junit.Assert.*
import org.junit.*
import shootemup.*

class AProjectile {

    @Test
    fun mustHaveConsistentDimensions() {
        data class D(val position: Vector, val velocity: Vector)
        listOf(
                D(listOf(0),          listOf(0, 0)),
                D(listOf(0, 0),       listOf(0)),
                D(listOf(1, 2, 3, 4), listOf(9, 8, 7, 6, 5))
        ).forEach({
            try {
                Projectile(it.position, it.velocity)
                assertFalse(true)
            } catch(e: IllegalArgumentException) {
                // Good.
            }
        })
    }

    @Test
    fun willNotMoveWhenItsVelocityIsZero() {
        val positions = listOf(
                listOf(0),
                listOf(1),
                listOf(0, 0),
                listOf(1, 2, 3)
        )
        positions.forEach({ position ->
            val velocity = MutableList(position.size) {0}
            val projectile = Projectile(position, velocity)
            val next = projectile.travel()
            assertEquals(projectile.position, next.position)
        })
    }

    @Test
    fun willTravelToTheExpectedPosition() {
        data class D(val velocity: Vector,
                     val expectedFirst: Vector,
                     val expectedSecond: Vector)

        listOf(
                D(listOf(1), listOf(1), listOf(2)),
                D(listOf(10), listOf(10), listOf(20)),
                D(listOf(15), listOf(15), listOf(30)),
                D(listOf(-1), listOf(-1), listOf(-2)),
                D(listOf(-1000), listOf(-1000), listOf(-2000))
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
