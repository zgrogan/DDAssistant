package ddassistant;

import javafx.geometry.Point3D;

import java.util.HashMap;
import java.util.LinkedList;

@SuppressWarnings("restriction")
public class DDWell {
	// well identifying informaton
	private HashMap<String, String> wellInfo;

	// curves
	private TargetCurve targetCurve;
	private final ActualCurve actualCurve;
	private ProjectedCurve projectedCurve;
	private double projectionLength = 100;

	// target window
	private double targetWindowHi = 20;
	private double targetWindowLow = 20;
	private double targetWindowLeft = 20;
	private double targetWindowRight = 20;

	// slides
	private LinkedList<Slide> slides;

	// constructors
	public DDWell() {
		targetCurve = new TargetCurve();
		actualCurve = new ActualCurve();
		actualCurve.addSurvey(0,0,0);
		projectedCurve = new ProjectedCurve(actualCurve, projectionLength);
		slides = new LinkedList<Slide>();
		wellInfo = new HashMap<String, String>();
	}

	// public methods
	public synchronized void addSlide(Slide slide) {
		slides.add(slide);
	}

	public synchronized void addSlide(double startDepth, double length, double direction) {
		Slide slide = new Slide(startDepth, length, direction);
	}

	public synchronized void removeSlide(Slide slide) {
		slides.remove(slide);
	}

	public synchronized LinkedList<DDSurvey> getSurveys() {
		return actualCurve.getSurveys();
	}

	public synchronized void createTargetCurve(double targetDepth) {
		targetCurve = new TargetCurve(targetDepth);
	}

	// put a turn in the target curve
	public synchronized void addTargetTurn(double startDepth, double curveLength,
			double newAzimuth, double newInclination) {
		targetCurve
				.addTurn(startDepth, curveLength, newAzimuth, newInclination);
	}

	// add a kickoff. endTVD refers to true vertical depth at end of curve
	public synchronized void addKickOff(double startDepth, double endTVD, double newAzimuth,
			double newInclination) {
		targetCurve.addKickOff(startDepth, endTVD, newAzimuth, newInclination);
	}

	// get survey information into actualCurve
	public synchronized void addSurvey(DDSurvey survey) {
		actualCurve.addSurvey(survey);
	}

	public void addSurvey(double depth, double azimuth, double inclination) {
		DDSurvey survey = new DDSurvey(depth, azimuth, inclination);
		actualCurve.addSurvey(survey);
	}

	public synchronized void removeSurvey(DDSurvey survey) {
		actualCurve.removeSurvey(survey);
	}

	public synchronized void editSurvey(DDSurvey oldSurvey, DDSurvey newSurvey) {
		oldSurvey = newSurvey;
		actualCurve.rebuildCurve();
	}

	public synchronized void editSurvey(DDSurvey survey, double depth, double azimuth,
			double inclination) {
		editSurvey(survey, new DDSurvey(depth, azimuth, inclination));
	}

	// access to sets of points
	public synchronized LinkedList<Point3D> getTargetPoints() {
		return targetCurve.getPoints();
	}

	public synchronized LinkedList<Point3D> getActualPoints() {
		return actualCurve.getPoints();
	}

	// define / retreive target window
	public synchronized double getTargetWindowHi() {
		return targetWindowHi;
	}

	public synchronized void setTargetWindowHi(double targetWindowHi) {
		this.targetWindowHi = targetWindowHi;
	}

	public synchronized double getTargetWindowLow() {
		return targetWindowLow;
	}

	public synchronized void setTargetWindowLow(double targetWindowLow) {
		this.targetWindowLow = targetWindowLow;
	}

	public synchronized double getTargetWindowLeft() {
		return targetWindowLeft;
	}

	public synchronized void setTargetWindowLeft(double targetWindowLeft) {
		this.targetWindowLeft = targetWindowLeft;
	}

	public synchronized double getTargetWindowRight() {
		return targetWindowRight;
	}

	public synchronized void setTargetWindowRight(double targetWindowRight) {
		this.targetWindowRight = targetWindowRight;
	}

	// give access to curves
	public synchronized TargetCurve getTargetCurve() {
		return targetCurve;
	}

	public synchronized ActualCurve getActualCurve() {
		return actualCurve;
	}

	public synchronized HashMap<String, String> getWellInfo() {
		return wellInfo;
	}

	public synchronized void setWellInfo(HashMap<String, String> wellInfo) {
		this.wellInfo = wellInfo;
	}

	public synchronized void setWellName(String name) {
		wellInfo.put("Well Name", name);
	}
	
	public synchronized String getWellName() {
		return (wellInfo.containsKey("Well Name") ? wellInfo.get("Well Name") : "");
	}

	public synchronized LinkedList<Slide> getSlides() {
		return slides;
	}

	public ProjectedCurve getProjectedCurve() {
		return this.projectedCurve;
	}
	
	@Override
	public String toString() {
		String ret = "";
		ret += getWellName() + targetCurve + actualCurve + "\n";
		return ret;
	}

}
