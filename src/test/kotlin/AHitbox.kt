import org.junit.Assert.*
import org.junit.*
import java.util.concurrent.ThreadLocalRandom
import shootemup.*

class AHitbox {

    @Test(expected = IllegalArgumentException::class)
    fun mustSpanAtLeastOneDimension() {
        val occupiedSpace = emptyList<IRange>()
        Hitbox(occupiedSpace)
    }

    @Test
    fun isNotTouchingAPointIfADimensionCheckFails() {
        manyDimensions().forEach({ n ->
            val occupiedSpace = failingDimensionChecks(n)
            val point = randomPoint(n)
            val hitbox = Hitbox(occupiedSpace)
            assertFalse(hitbox.isTouching(point))
        })
    }

    @Test
    fun isTouchingAPointIfAllDimensionChecksPass() {
        manyDimensions().forEach({ n->
            val occupiedSpace = passingDimensionChecks(n)
            val point = randomPoint(n)
            val hitbox = Hitbox(occupiedSpace)
            assertTrue(hitbox.isTouching(point))
        })
    }

    private fun passingDimensionChecks(n: Int) = IntArray(n).map({ MockRange(true) })

    private fun failingDimensionChecks(n: Int): Dimensions {
        val random = ThreadLocalRandom.current()
        val numPassing = random.nextInt(n) - 1

        return intRange(n).map({ i ->
            if (i < numPassing)
                MockRange(true)
            else
                MockRange(false)
        })
    }

    private fun randomPoint(n: Int): Vector {
        val random = ThreadLocalRandom.current()
        return IntArray(n) { random.nextInt(Integer.MAX_VALUE) }.asList()
    }

    private fun manyDimensions() = IntRange(1, 20)
    private fun intRange(upper: Int) = IntRange(0, upper)
}
