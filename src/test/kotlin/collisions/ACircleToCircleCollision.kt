package collisions

import org.junit.Assert.*
import org.junit.*
import shootemup.geometry.*
import shootemup.collision.*

class ACircleToCircleCollision {

    @Test
    fun doesNotOccurBetweenDistantCircles() = shouldAllNotCollide(listOf(
            Entry(vectorOf(0), 1, vectorOf(10), 1)
    ))

    @Test
    fun occursBetweenNearbyCircles() = shouldAllCollide(listOf(
            Entry(vectorOf(0), 1, vectorOf(1), 1)
    ))

    @Test
    fun doesNotOccurWhenTheEdgesTouch() = shouldAllNotCollide(listOf(
            Entry(vectorOf(0), 1, vectorOf(2), 1))
    )

    // ----------------------------------------------------------------------

    private fun shouldAllCollide(entries : List<Entry>) = entries.forEach {
        entry -> assertThatTheyCollide(entry)
    }

    private fun shouldAllNotCollide(entries : List<Entry>) = entries.forEach {
        entry -> assertThatTheyDontCollide(entry)
    }

    private fun assertThatTheyCollide(entry : Entry) = makeAssertion(entry, true)

    private fun assertThatTheyDontCollide(entry : Entry) = makeAssertion(entry, false)

    private fun makeAssertion(entry : Entry, isCollisionExpected : Boolean) {
        val c1 = CircleCollider(entry.position1, entry.radius1)
        val c2 = CircleCollider(entry.position2, entry.radius2)
        assertEquals(isCollisionExpected, isColliding(c1, c2))
        assertEquals(isCollisionExpected, isColliding(c2, c1))
    }

    private data class Entry(val position1: Vector,
                             val radius1: Int,
                             val position2: Vector,
                             val radius2: Int)
}
