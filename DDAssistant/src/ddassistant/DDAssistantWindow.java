package ddassistant;
import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class DDAssistantWindow extends ApplicationFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String title = "DDAssistant";
	
	private XYDataset dataset;
	private JFreeChart chart;
	private ChartPanel chartPanel;
	
	private void initComponents(){
		dataset = createDataset();
		chart = createChart(dataset);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(chartPanel);
	}
	
	private JFreeChart createChart(XYDataset dataset){
		JFreeChart chart;
		XYPlot plot;
		
		chart = ChartFactory.createXYLineChart("DDAssistant", "Horizontal Distance", "Vertical Depth", dataset, PlotOrientation.VERTICAL, false, false, false);
		plot = (XYPlot) chart.getPlot();
		
		chart.setBackgroundPaint(Color.white);
		plot.setDomainAxisLocation(AxisLocation.TOP_OR_LEFT);
		plot.setDomainPannable(true);
		plot.setRangePannable(true);
		plot.setBackgroundPaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.white);
		
		
		return chart;
	}
	
	/*private XYDataset createDataset(float KODepth, float landDepth, float landAngle){
		return null;
	}*/
	
	/*private void addSurvey(float inclination, float direction, float depth){
		
	}*/
	
	private XYDataset createDataset(/*int KODepth, int landDepth, int landAngle*/){
		final LinkedList<Double> depth = new LinkedList<Double>();
		final LinkedList<Double> width = new LinkedList<Double>();
		XYSeriesCollection xyCollect = new XYSeriesCollection();
		XYSeries series = new XYSeries("0");
		XYSeries series0 = new XYSeries("1");
		
		float KODepth = 6000;
		float landDepth = 6300;
		float landAngle = 90;
		float totalDepth;
		
		
		series.add(0, 0);
		series.add(0, -KODepth);
		
		double y = 0;
		for(int x = 0; x < landDepth - KODepth; x++){
			y = Math.sqrt(Math.pow(landDepth - KODepth, 2) - Math.pow((x - (landDepth - KODepth)), 2)) + KODepth;
			series.add(x, -y);
		}
		
		series.add(6000, -y);
		
		
		xyCollect.addSeries(series);
		return xyCollect;
		
	}
	public DDAssistantWindow(){
		super(title);
		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initComponents();
	}
}
