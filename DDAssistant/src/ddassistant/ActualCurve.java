package ddassistant;

import java.util.LinkedList;
import javafx.geometry.Point3D;

@SuppressWarnings("restriction")
public class ActualCurve extends DDCurveData {

	LinkedList<DDSurvey> surveys = new LinkedList<DDSurvey>();

	//constructors
	public ActualCurve() {
		super();
		this.surveys = new LinkedList<DDSurvey>();
	}

	public ActualCurve(LinkedList<DDSurvey> surveys) {
		this();
		init(surveys);
	}
	
	// rebuild points
	private void init() {
		this.points = new LinkedList<Point3D>();
		points.add(ZERO);
		surveys.sort(null);
		for (DDSurvey survey : surveys) {
			addSurvey(survey);
		}		
	}

	// build / rebuild this object based on a list of surveys
	private void init(LinkedList<DDSurvey> surveys) {
		this.surveys = surveys;
		init();
	}

	// adds a survey and rebuilds curve if necessary
	public void addSurvey(DDSurvey survey) {
		double startDepth = getDepth();
		if (survey.getDepth() > startDepth) {
			if(!surveys.contains(survey))
					surveys.add(survey);
			double curveLength = survey.getDepth() - startDepth;
			this.addTurn(startDepth, curveLength, survey.getAzimuth(), survey.getInclination());
		} else if (Math.abs(survey.getDepth() - startDepth) <= 0.001){
			surveys.add(survey);
		} else {
			surveys.add(survey);
			rebuildCurve();
		}
	}

	public void addSurvey(double depth, double azimuth, double inclination) {
		DDSurvey survey = new DDSurvey(depth, azimuth, inclination);
		this.addSurvey(survey);
	}

	// rebuilds points based on surveys;
	public void rebuildCurve() {
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

	public LinkedList<DDSurvey> getSurveys() {
		return surveys;
	}

	public void removeSurvey(DDSurvey survey) {
		if (surveys.contains(survey))
			surveys.remove(survey);
		this.init();
	}
}
