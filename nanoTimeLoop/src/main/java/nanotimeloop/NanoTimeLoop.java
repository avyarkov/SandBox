package nanotimeloop;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("DuplicatedCode")
public class NanoTimeLoop {

    public static void main(String[] args) {
        final int TOTAL_LIMIT = 1000;

        long[] nanoTime = new long[TOTAL_LIMIT];
        final long START = System.nanoTime();
        for (int i = 0; i < TOTAL_LIMIT; i++) {
            nanoTime[i] = System.nanoTime() - START;
        }

        XYSeries xySeries = new XYSeries("series");
        for (int i = 0; i < TOTAL_LIMIT; i++) {
            xySeries.add(1.0 * nanoTime[i] / 1000, i);
        }

        var dataset = new XYSeriesCollection();
        dataset.addSeries(xySeries);

        var jFreeChart = ChartFactory.createXYLineChart("title", "millis", "iteration", dataset);

        ChartPanel chartPanel = new ChartPanel(jFreeChart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setMouseWheelEnabled(true);

        JFrame windowFrame = new JFrame();
        windowFrame.add(chartPanel);
        windowFrame.pack();
        windowFrame.setVisible(true);
        windowFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
