package howtoinvestfordummies;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LineChartForPortfolio extends Application {

  String pattern = "MM-dd-yyyy";
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    Map<Date, Double> sortedPortfolioHistoricalData = new LinkedHashMap<>();
    sortedPortfolioHistoricalData.put(new Date(1111111111L), 10.0);
    sortedPortfolioHistoricalData.put(new Date(2222222222L), 15.0);
    sortedPortfolioHistoricalData.put(new Date(3333333333L), 12.0);
    sortedPortfolioHistoricalData.put(new Date(4444444444L), 17.0);
    sortedPortfolioHistoricalData.put(new Date(5555555555L), 16.0);
    sortedPortfolioHistoricalData.put(new Date(6666666666L), 20.0);
    sortedPortfolioHistoricalData.put(new Date(7777777777L), 21.0);
    BorderPane graphPane = createchartFromHistoriaData(stage,
            sortedPortfolioHistoricalData);
    stage.setScene(new Scene(graphPane));
    stage.show();
  }

  BorderPane createchartFromHistoriaData(Stage primaryStage, Map<Date, Double>
          portfolioHistoricalData) {
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    ObservableList<Data<String, Number>> graphData = FXCollections.observableArrayList();
    LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
    for (Date k : portfolioHistoricalData.keySet()) {
      graphData.add(new Data<String, Number>(simpleDateFormat.format(k),
              portfolioHistoricalData.get(k)));
    }
    chart.getData().add(new Series<>(graphData));
    BorderPane chartPane = new BorderPane(chart);
    return chartPane;
  }
}