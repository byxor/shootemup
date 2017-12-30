package shootemup;

import java.util.List;
import java.util.stream.IntStream;

import shootemup.geometry.IRange;
import shootemup.geometry.Vector;


public class Hitbox {

    public Hitbox(List<IRange> dimensions) {
	assertEnough(dimensions);
	this.dimensions = dimensions;
    }

    public boolean contains(Vector point) {
	for (int i = 0; i < dimensions.size(); i++) {
	    IRange dimension = dimensions.get(i);
            Integer element = point.elements.get(i);
            if (!dimension.contains(element))
		return false;
	}
	return true;
    }

    private void assertEnough(List<IRange> dimensions) {
        if (dimensions.size() <= 0)
	    throw new IllegalArgumentException("Hitbox cannot be 0-dimensional");
    }

    private final List<IRange> dimensions;

}
