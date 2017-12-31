package shootemup.geometry

// Member variables are only public to ease testing.
// Do not access these; they are implementation details.
class Range(val _lower: Int, val _upper: Int) : IRange {

    override fun contains(value: Int): Boolean {
        return value >= _lower && value < _upper
    }
}
