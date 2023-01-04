package nanotimeloop;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings({"DuplicatedCode", "BusyWait"})
public class NanoTimeLoopWithScaleChoice {
    static volatile int TOTAL_LIMIT = -1;

    public static void main(String[] args) throws InterruptedException {
        JRadioButton smallOption = new JRadioButton("small scale");
        smallOption.addActionListener(e -> TOTAL_LIMIT = 1000);
        smallOption.setFocusable(false);
        JRadioButton mediumOption = new JRadioButton("medium scale");
        mediumOption.addActionListener(e -> TOTAL_LIMIT = 10_000);
        mediumOption.setFocusable(false);
        JRadioButton largeOption = new JRadioButton("large scale");
        largeOption.addActionListener(e -> TOTAL_LIMIT = 100_000);
        largeOption.setFocusable(false);

        JFrame choiceFrame = new JFrame("Scale Choice");
        choiceFrame.setLayout(new FlowLayout());
        choiceFrame.add(smallOption);
        choiceFrame.add(mediumOption);
        choiceFrame.add(largeOption);
        choiceFrame.pack();
        choiceFrame.setVisible(true);
        choiceFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        while (TOTAL_LIMIT == -1) {Thread.sleep(200);}

        choiceFrame.dispose();

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
