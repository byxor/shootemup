package colliders

// import org.junit.Assert.*
// import org.junit.*
// import java.util.concurrent.ThreadLocalRandom
// import shootemup.*
// import mocks.*

// class ABoxCollider {

//     @Test(expected = IllegalArgumentException::class)
//     fun mustSpanAtLeastOneDimension() {
//         val occupiedSpace = emptyList<IRange>()
//         BoxCollider(occupiedSpace)
//     }

//     @Test
//     fun isNotTouchingAPointIfADimensionCheckFails() {
//         manyDimensions().forEach({ n ->
//             val occupiedSpace = failingDimensionChecks(n)
//             val point = randomPoint(n)
//             val boxCollider = BoxCollider(occupiedSpace)
//             assertFalse(boxCollider.isTouching(point))
//         })
//     }

//     @Test
//     fun isTouchingAPointIfAllDimensionChecksPass() {
//         manyDimensions().forEach({ n->
//             val occupiedSpace = passingDimensionChecks(n)
//             val point = randomPoint(n)
//             val boxCollider = BoxCollider(occupiedSpace)
//             assertTrue(boxCollider.isTouching(point))
//         })
//     }

//     private fun passingDimensionChecks(n: Int) = IntArray(n).map({ MockRange(true) })

//     private fun failingDimensionChecks(n: Int): Dimensions {
//         val random = ThreadLocalRandom.current()
//         val numPassing = random.nextInt(n) - 1

//         return intRange(n).map({ i ->
//             if (i < numPassing)
//                 MockRange(true)
//             else
//                 MockRange(false)
//         })
//     }

//     private fun randomPoint(n: Int): Vector {
//         val random = ThreadLocalRandom.current()
//         return IntArray(n) { random.nextInt(Integer.MAX_VALUE) }.asList()
//     }

//     private fun manyDimensions() = IntRange(1, 20)
//     private fun intRange(upper: Int) = IntRange(0, upper)
// }
