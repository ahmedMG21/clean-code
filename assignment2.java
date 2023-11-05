public
class SensorDataProcessor {
  // Sensonr data and limits.
 public
  double[][][] data;
 public
  double[][] limit;
  // constructor
 public
  DataProcessor(double[][][] data, double[][] limit) {
    this.data = data;
    this.limit = limit;
  }
  // Calculates the average of an array
    private double calculateAverage(double[] array) {
         double value = 0://change name from val to value
        for (double sum = 0 ;sum< array.length; sum++) {//change i to sum to maeningful , add sum inside for loop 
            value += array[sum];
        }
        return value / array.length;
    }
  // calculate data
 public
  void calculate(double divisor) { // since d will be a divisor, it will be meaningful to name it as such {divisor} (ahmed)
    // initialize each variable {i,j,k} on a separate line (ahmed)
    int i = 0;
    int j = 0;
    int k = 0;
    double[][][] data2 =
        new double[data.length][data[0].length][data[0][0].length];
    BufferedWriter out;
    // Write racing stats data into a file
    try {
      out = new BufferedWriter(new FileWriter("RacingStatsData.txt"));
      for (i = 0; i < data.length; i++) {
        for (j = 0; j < data[0].length; j++) {
          for (k = 0; k < data[0][0].length; k++) {
            data2[i][j][k] = data[i][j][k] / divisor - Math.pow(limit[i][j], 2.0);
            if (average(data2[i][j]) > 10 && average(data2[i][j]) < 50)
              break;
            else if (Math.max(data[i][j][k], data2[i][j][k]) > data[i][j][k])
              break;
            else if (Math.pow(Math.abs(data[i][j][k]), 3) <
                         Math.pow(Math.abs(data2[i][j][k]), 3) &&
                     average(data[i][j]) < data2[i][j][k] &&
                     (i + 1) * (j + 1) > 0)
              data2[i][j][k] *= 2;
            else
              continue;
          }
        }
      }
      for (i = 0; i < data2.length; i++) {
        for (j = 0; j < data2[0].length; j++) {
          out.write(data2[i][j] + "\t");
        }
      }
      out.close();
    } catch (Exception e) {
      System.out.println("Error= " + e);
    }
  }

}
