package ddassistant;

// represents a directional slide during drilling operations
public class Slide {
	public double startDepth;
	public double length;
	public double direction;

	static final double UP = 0;
	static final double RIGHT = 90;
	static final double LEFT = -90;
	static final double DOWN = 180;

	// constructors
	public Slide(double startDepth, double length, double direction)
			throws IllegalArgumentException {
		setStartDepth(startDepth);
		setLength(length);
		setDirection(direction);
	}

	// public methods
	public double getStartDepth() {
		return startDepth;
	}

	public void setStartDepth(double startDepth)
			throws IllegalArgumentException {
		if (startDepth < 0)
			throw new IllegalArgumentException("startDepth must be >= 0");
		this.startDepth = startDepth;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) throws IllegalArgumentException {
		if (length <= 0)
			throw new IllegalArgumentException("length must be > 0");
		this.length = length;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		// sanitize direction input, store as -180 < direction <= 180 (0 is up)
		while (Math.abs(direction) > 180) {
			direction += (direction > 180) ? -360 : 360;
		}
		if (direction == -180)
			direction = 180;
		this.direction = direction;
	}
	
	public void join(Slide other) {
		double thisEndDepth = startDepth + length;
		double otherEndDepth = other.getStartDepth() + other.getLength();
		this.startDepth = Math.min(this.startDepth, other.startDepth);
		this.length = Math.max(thisEndDepth, otherEndDepth) - startDepth;
	}
	
	// make item sortable based on startDepth
	public int compareTo(Slide o) {
		if (this.startDepth < o.startDepth)
			return -1;
		else if (this.startDepth > o.startDepth)
			return 1;
		else return 0;
	}

	@Override
	public boolean equals(Object ob) {
		Slide o = (Slide)ob;
		return ((this.startDepth == o.startDepth) && (this.direction == o.getDirection())
				&& (this.length == o.getLength()));
	}

}
