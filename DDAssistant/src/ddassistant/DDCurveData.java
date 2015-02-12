package ddassistant;

import java.util.LinkedList;

import org.jfree.data.xy.XYSeries;

public class DDCurveData {

	private LinkedList<DDSurvey> data;

	public DDCurveData() {
		data = new LinkedList<DDSurvey>();
	}

	public DDCurveData(double koDepth, double landDepth,
			double landInclination, double azimuth) {

	}

	public void changeDirection(double depth, double azimuth,
			double inclination, double targetDepth) {
	}

	public LinkedList<DDSurvey> getData() {
		return data;
	}

	public void setData(LinkedList<DDSurvey> data) {
		this.data = data;
	}

	public void addSurvey(DDSurvey survey) {
		data.add(survey);
		data.sort(null);
	}

	public void addSurvey(double depth, double inclination, double azimuth) {
		DDSurvey survey = new DDSurvey(depth, inclination, azimuth);
		this.addSurvey(survey);
	}

	public DDSurvey getSurveyObject() {
		return new DDSurvey();
	}

	public XYSeries getXYSeries() {
		return null;

	}
	
	public XYSeries getXZSeries() {
		return null;
	}
}
