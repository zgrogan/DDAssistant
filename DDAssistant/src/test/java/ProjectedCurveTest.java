package test.java;

import ddassistant.ActualCurve;
import ddassistant.ProjectedCurve;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zgrogan on 4/24/15.
 */
public class ProjectedCurveTest {
    @Test
    public void testConstructor() {
        ActualCurve ac = new ActualCurve();
        ac.addSurvey(250, 0, 1);
        ac.addSurvey(500, 160, 4);
        ProjectedCurve pc = new ProjectedCurve(ac, 400);
    }

    @Test
    public void testUpdateProjectedCurve() throws Exception {

    }

    @Test
    public void testGetPointAt() throws Exception {

    }
}