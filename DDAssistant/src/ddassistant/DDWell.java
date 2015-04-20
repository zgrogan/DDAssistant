package ddassistant;

import java.util.LinkedList;

public class DDWell {
	// private members
	private TargetCurve targetCurve;
	private ActualCurve actualCurve;
	private double targetWindowHi;
	private double targetWindowLow;
	private double targetWindowLeft;
	private double targetWindowRight;

	// constructors
	public DDWell() {
		targetCurve = new TargetCurve();
		actualCurve = new ActualCurve();
	}

	// public methods
	public LinkedList<DDSurvey> getSurveys() {
		return actualCurve.getSurveys();
	}

	public void createTargetCurve(double targetDepth) {
		targetCurve = new TargetCurve(targetDepth);
	}

	// put a turn in the target curve
	public void addTargetTurn(double startDepth, double curveLength,
			double newAzimuth, double newInclination) {
		targetCurve
				.addTurn(startDepth, curveLength, newAzimuth, newInclination);
	}
	
	// add a kickoff.  endTVD refers to true vertical depth at end of curve
	public void addKickOff(double startDepth, double endTVD, double newAzimuth, double newInclination) {
		targetCurve.addKickOff(startDepth, endTVD, newAzimuth, newInclination);
	}
	
	// get survey information into actualCurve
	public void addSurvey(DDSurvey survey) {
		actualCurve.addSurvey(survey);
	}
	
	public void addSurvey(double depth, double azimuth, double inclination) {
		DDSurvey survey = new DDSurvey(depth, azimuth, inclination);
		actualCurve.addSurvey(survey);
	}
	
	public void removeSurvey(DDSurvey survey) {
		actualCurve.removeSurvey(survey);
	}
	
	public void editSurvey(DDSurvey oldSurvey, DDSurvey newSurvey) {
		oldSurvey = newSurvey;
		actualCurve.rebuildCurve();
	}
	
	public void editSurvey(DDSurvey survey, double depth, double azimuth, double inclination) {
		editSurvey(survey, new DDSurvey(depth, azimuth, inclination));
	}

	// define / retreive target window
	public double getTargetWindowHi() {
		return targetWindowHi;
	}

	public void setTargetWindowHi(double targetWindowHi) {
		this.targetWindowHi = targetWindowHi;
	}

	public double getTargetWindowLow() {
		return targetWindowLow;
	}

	public void setTargetWindowLow(double targetWindowLow) {
		this.targetWindowLow = targetWindowLow;
	}

	public double getTargetWindowLeft() {
		return targetWindowLeft;
	}

	public void setTargetWindowLeft(double targetWindowLeft) {
		this.targetWindowLeft = targetWindowLeft;
	}

	public double getTargetWindowRight() {
		return targetWindowRight;
	}

	public void setTargetWindowRight(double targetWindowRight) {
		this.targetWindowRight = targetWindowRight;
	}
}
