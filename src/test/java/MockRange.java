import shootemup.geometry.IRange;
import java.util.Set;
import java.util.HashSet;


public class MockRange implements IRange {

    public MockRange(boolean returnValue) {
	this.returnValue = returnValue;
	this.valuesUsed = new HashSet<>();
    }

    public MockRange() {
	this(false);
    }

    @Override
    public boolean contains(int value) {
	valuesUsed.add(value);
	return returnValue;
    }

    public boolean calledWith(int value) {
	return valuesUsed.contains(value);
    }

    private final boolean returnValue;
    private final Set<Integer> valuesUsed;
}
