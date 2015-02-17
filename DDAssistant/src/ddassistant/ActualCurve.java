package ddassistant;

import org.jfree.data.xy.XYSeries;

public class ActualCurve extends DDCurveData {

	XYSeries xySeries;
	XYSeries xzSeries;

	public ActualCurve() {
		xySeries = new XYSeries("XY");
		xySeries.add(0, 0);
		xzSeries = new XYSeries("XZ");
		xzSeries.add(0, 0);
	}

	public XYSeries getXYSeries() {
		XYSeries xys = new XYSeries("ActualCurve XY");
		xys.add(0, 0);
		double lastDepth = 0;
		for (DDSurvey survey : data) {
			//double x = Math.sin(survey.inclination) 
			//		* (survey.depth - xySeries.get

		}
		return xySeries;
	}

	public XYSeries getXZSeries() {
		return xzSeries;
	}

	public void addSurvey(DDSurvey survey) {
		data.add(survey);
		data.sort(null);
	}

	public void addSurvey(double depth, double inclination, double azimuth) {
		DDSurvey survey = new DDSurvey(depth, inclination, azimuth);
		this.addSurvey(survey);
	}

}
