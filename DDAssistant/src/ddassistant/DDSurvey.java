package ddassistant;


// used to store individual survey entries
public class DDSurvey implements Comparable<DDSurvey> {
	public double depth;
	public double inclination;
	public double azimuth;
	
	public DDSurvey(){
		super();
	}
	
	public DDSurvey(double depth, double azimuth, double inclination){
		super();
		this.depth = depth;
		this.inclination = inclination;
		this.azimuth = azimuth;
	}

	public int compareTo(DDSurvey o) {
		if (this.depth < o.depth)
			return -1;
		else if (this.depth > o.depth)
			return 1;
		else return 0;
	}
	
	@Override
	public String toString() {
		String ret = new String("");
		ret += "Depth = " + depth + " Azimuth = " + azimuth + " Inclination = " + inclination;
		return ret;
	}
	
	@Override
	public boolean equals(Object ob) {
		DDSurvey o = (DDSurvey)ob;
		return ((this.depth == o.depth) && (this.inclination == o.inclination)
				&& (this.azimuth == o.azimuth));
	}
}
