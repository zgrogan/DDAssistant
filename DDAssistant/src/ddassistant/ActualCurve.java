package ddassistant;

import java.util.LinkedList;
import java.util.List;

import javafx.geometry.Point3D;

@SuppressWarnings("restriction")
public class ActualCurve extends DDCurveData {

	List<DDSurvey> surveys = new LinkedList<DDSurvey>();

	//constructors
	public ActualCurve() {
		super();
		this.surveys = new LinkedList<DDSurvey>();
	}

	public ActualCurve(List<DDSurvey> surveys) {
		this();
		init(surveys);
	}

	// build / rebuild this object based on a list of surveys
	private void init(List<DDSurvey> surveys) {
		surveys.sort(null);
		this.points = new LinkedList<Point3D>();
		points.add(ZERO);
		this.surveys = new LinkedList<DDSurvey>();
		for (DDSurvey survey : surveys) {
			addSurvey(survey);
		}
	}

	public void addSurvey(DDSurvey survey) {
		double startDepth = getDepth();
		if (survey.depth > startDepth) {
			if(!surveys.contains(survey))
					surveys.add(survey);
			double magnitude = survey.depth - startDepth;
			this.addTurn(startDepth, magnitude, survey.azimuth,	survey.inclination);
		} else {
			surveys.add(survey);
			rebuildCurve();
		}
	}

	private void rebuildCurve() {
		this.points = new LinkedList<Point3D>();
		surveys.sort(null);
		init(surveys);
	}

	public double getDepth() {
		double depth = 0;
		// add up each segment length, O(n)
		if (points.size() > 1) {
			for (int i = 0; i < points.size() - 1; i++) {
				depth += points.get(i).distance(points.get(i + 1));
			}
		}
		return depth;
	}

	public void addSurvey(double depth, double azimuth, double inclination) {
		DDSurvey survey = new DDSurvey(depth, azimuth, inclination);
		this.addSurvey(survey);
	}
}
