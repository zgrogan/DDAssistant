package ddassistant;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

// used to store individual survey entries
public class DDSurvey implements Comparable<DDSurvey> {
	private final DoubleProperty depth;
	private final DoubleProperty inclination;
	private final DoubleProperty azimuth;

	public double getDepth() {
		return depth.get();
	}

	public void setDepth(double depth) {
		this.depth.set(depth);
	}

	public double getInclination() {
		return inclination.get();
	}

	public void setInclination(double inclination) {
		this.inclination.set(inclination);
	}

	public double getAzimuth() {
		return azimuth.get();
	}

	public void setAzimuth(double azimuth) {
		this.azimuth.set(azimuth);
	}

	public DDSurvey(){
		this.depth = new SimpleDoubleProperty();
		this.inclination = new SimpleDoubleProperty();
		this.azimuth = new SimpleDoubleProperty();
	}

	public DoubleProperty azimuthProperty()  { return azimuth; }
	public DoubleProperty depthProperty()  { return depth; }
	public DoubleProperty inclinationProperty()  { return inclination; }


	public DDSurvey(double depth, double azimuth, double inclination){
		this();
		this.depth.set(depth);
		this.inclination.set(inclination);
		this.azimuth.set(azimuth);
	}

	public int compareTo(DDSurvey o) {
		if (this.depth.get() < o.depth.get())
			return -1;
		else if (this.depth.get() > o.depth.get())
			return 1;
		else return 0;
	}
	
	@Override
	public String toString() {
		String ret = new String("");
		ret += "Depth = " + depth.get() + " Azimuth = " + azimuth.get() + " Inclination = " + inclination.get();
		return ret;
	}
	
	@Override
	public boolean equals(Object ob) {
		DDSurvey o = (DDSurvey)ob;
		return ((this.depth.get() == o.depth.get()) && (this.inclination.get() == o.inclination.get())
				&& (this.azimuth.get() == o.azimuth.get()));
	}
}
