package util;

import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import po.Trip;

public class ChartUtil {
	public static JPanel createStockBarChartPanel(List<Trip> trips) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		if (trips == null || trips.isEmpty()) {
			dataset.addValue(0, "庫存", "無資料");
		} else {
			for (Trip t : trips) {
				dataset.addValue(t.getStock(), "庫存", t.getTripName());
			}
		}
		JFreeChart chart = ChartFactory.createBarChart("行程庫存", "行程", "庫存", dataset, PlotOrientation.VERTICAL, true,
				true, false);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
		return chartPanel;
	}
}