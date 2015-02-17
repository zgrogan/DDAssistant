package ddassistant;

import java.util.LinkedList;

import org.jfree.data.xy.XYSeries;

public abstract class DDCurveData {
	
	public abstract XYSeries getXYSeries();
	
	public abstract XYSeries getXZSeries();

	protected LinkedList<DDSurvey> data;

	public LinkedList<DDSurvey> getData() {
		return data;
	}

	public void setData(LinkedList<DDSurvey> data) {
		this.data = data;
	}
	
	
}
