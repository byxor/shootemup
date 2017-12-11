package shootemup.geometry;


public class Range implements IRange {

    public Range(int lower, int upper) {
	this._lower = lower;
	this._upper = upper;
    }

    @Override
    public boolean contains(int value) {
	return value >= _lower && value < _upper;
    }

    // These are only public to ease testing.
    // Do not access these; they are implementation details.
    public final int _lower;
    public final int _upper;
}
