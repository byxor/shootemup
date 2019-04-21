package shootemup.geometry

typealias Vector = List<Int>

fun vectorOf(vararg components: Int): Vector {
    return listOf<Int>(*components.toTypedArray())
}

interface IRange {
    fun contains(value: Int): Boolean
}

typealias Dimensions = List<IRange>

// These member variables are only public to ease testing.
//
// Do not access them; they are implementation details.
//
// Your code will break, and you can't say I didn't warn you.
//
class Range(val _lower: Int, val _upper: Int) : IRange {
    override fun contains(value: Int): Boolean {
        return value >= _lower && value < _upper
    }
}
