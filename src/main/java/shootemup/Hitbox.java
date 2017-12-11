package shootemup;

import shootemup.geometry.IRange;
import shootemup.geometry.Point;


public class Hitbox {

    public Hitbox(IRange horizontalRange, IRange verticalRange) {
	this.horizontalRange = horizontalRange;
	this.verticalRange = verticalRange;
    }

    public boolean contains(Point point) {
	boolean containsX = horizontalRange.contains(point.x);
	boolean containsY = verticalRange.contains(point.y);
	return containsX && containsY;
    }

    private final IRange horizontalRange;
    private final IRange verticalRange;
}
