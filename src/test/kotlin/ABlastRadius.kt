import org.junit.Assert.*
import org.junit.*
import shootemup.*

class ABlastRadius {

    @Test
    fun mustHaveAPositiveRadius() {
        listOf(
                listOf(0),
                listOf(0, 1),
                listOf(0, 1, 2),
                listOf(-1, -2, 0),
                listOf(10000, 20000, 30000)
        ).forEach({ position ->
            listOf(-1, -2, -3, -4, -5, -6).forEach({ radius ->
                try {
                    BlastRadius(position, radius)
                    assertFalse(true)
                } catch (e: IllegalArgumentException) {}
            })
        })
    }

    @Test
    fun containsNearbyPoints() {
        listOf(
                T1(listOf(0), 1, listOf(0)),
                T1(listOf(99), 1, listOf(99)),

                T1(listOf(0), 2, listOf(0)),
                T1(listOf(0), 2, listOf(1)),
                T1(listOf(0), 2, listOf(-1)),

                T1(listOf(5), 10, listOf(-4)),
                T1(listOf(5), 10, listOf(-3)),
                T1(listOf(5), 10, listOf(-2)),
                T1(listOf(5), 10, listOf(-1)),
                T1(listOf(5), 10, listOf(0)),
                T1(listOf(5), 10, listOf(1)),
                T1(listOf(5), 10, listOf(2)),
                T1(listOf(5), 10, listOf(3)),
                T1(listOf(5), 10, listOf(4)),
                T1(listOf(5), 10, listOf(5)),
                T1(listOf(5), 10, listOf(6)),
                T1(listOf(5), 10, listOf(7)),
                T1(listOf(5), 10, listOf(8)),
                T1(listOf(5), 10, listOf(9)),
                T1(listOf(5), 10, listOf(10)),
                T1(listOf(5), 10, listOf(11)),
                T1(listOf(5), 10, listOf(12)),
                T1(listOf(5), 10, listOf(13)),
                T1(listOf(5), 10, listOf(14))
        ).forEach({
            val blastRadius = BlastRadius(it.position, it.radius)
            assertTrue(blastRadius.contains(it.point))
        })
    }

    @Test
    fun doesNotContainDistantPoints() {
        listOf(
                T1(listOf(0), 1, listOf(1)),
                T1(listOf(0), 1, listOf(-1)),
                T1(listOf(0), 1, listOf(2)),
                T1(listOf(0), 1, listOf(-2)),

                T1(listOf(10), 1, listOf(11)),
                T1(listOf(10), 1, listOf(9)),

                T1(listOf(5), 10, listOf(-5)),
                T1(listOf(5), 10, listOf(-6)),
                T1(listOf(5), 10, listOf(15)),
                T1(listOf(5), 10, listOf(16))
        ).forEach({
            val blastRadius = BlastRadius(it.position, it.radius)
            assertFalse(blastRadius.contains(it.point))
        })
    }
}

internal data class T1(val position: Vector,
                       val radius: Int,
                       val point: Vector)
