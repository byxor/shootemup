package shootemup

interface IRange {
    fun contains(value: Int): Boolean
}

typealias Dimensions = List<IRange>
typealias Vector = List<Int>

// Member variables are only public to ease testing.
// Do not read them; they are implementation details.
class Range(val _lower: Int, val _upper: Int) : IRange {
    override fun contains(value: Int): Boolean {
        return value >= _lower && value < _upper
    }
}


