package ddassistant;

import java.util.LinkedList;
import javafx.geometry.Point3D;

@SuppressWarnings("restriction")
public class DDWell {
	// curves
	private TargetCurve targetCurve;
	private final ActualCurve actualCurve;
	
	// target window
	private double targetWindowHi;
	private double targetWindowLow;
	private double targetWindowLeft;
	private double targetWindowRight;
	
	// slides
	private LinkedList<Slide> slides;

	// constructors
	public DDWell() {
		targetCurve = new TargetCurve();
		actualCurve = new ActualCurve();
		slides = new LinkedList<Slide>();
	}
	

	// public methods
	public void addSlide(Slide slide) {
		slides.add(slide);
	}
	
	public void addSlide(double startDepth, double length, double direction) {
		Slide slide = new Slide(startDepth, length, direction);
	}
	
	public void removeSlide(Slide slide) {
		slides.remove(slide);
	}

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
	
	// access to sets of points
	public LinkedList<Point3D> getTargetPoints() {
		return targetCurve.getPoints();
	}
	
	public LinkedList<Point3D> getActualPoints() {
		return actualCurve.getPoints();
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

        // give access to curves
	public TargetCurve getTargetCurve() {
		return targetCurve;
	}

	public ActualCurve getActualCurve() {
		return actualCurve;
	}
}
