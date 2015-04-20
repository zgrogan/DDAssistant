package ddassistant;

import java.util.LinkedList;

public class DDWell {
	private TargetCurve targetCurve;
	private ActualCurve actualCurve;
	private double targetWindowHi;
	private double targetWindowLow;
	private double targetWindowLeft;
	private double targetWindowRight;

	public DDWell() {
		targetCurve = new TargetCurve();
		actualCurve = new ActualCurve();
	}

	public LinkedList<DDSurvey> getSurveys() {
		return actualCurve.getSurveys();
	}

	public void createTargetCurve(double targetDepth) {
		targetCurve = new TargetCurve(targetDepth);
	}

	public void addTargetTurn(double startDepth, double curveLength,
			double newAzimuth, double newInclination) {
		targetCurve
				.addTurn(startDepth, curveLength, newAzimuth, newInclination);
	}
	
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
