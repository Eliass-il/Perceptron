import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public static double[][] fileReader (File file) throws IOException {
        int count = 0;
        String text = null;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        double[][] arr = new double[lengthCalculus(file)][4];
        while ((text = reader.readLine()) != null) {
            String[] split = text.split(",");
            double column1 = Double.parseDouble(split[0]);
            double column2 = Double.parseDouble(split[1]);
            double column3 = Double.parseDouble(split[2]);
            double column4 = Double.parseDouble(split[3]);
            for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    arr[count][i] = column1;
                }
                if (i == 1) {
                    arr[count][i] = column2;
                }
                if (i == 2) {
                    arr[count][i] = column3;
                }
                if (i == 3) {
                    arr[count][i] = column4;
                }
            }
            count++;
        }
        return arr;
    }

    public static double[] expectedOutputReader(File file) throws IOException {
        String text = null;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        double[] arr = new double[lengthCalculus(file)];
        int count = 0;
        while ((text = reader.readLine()) != null) {
            String[] split = text.split(",");
            String column5 = split[4];
            if (column5.equals("Iris-setosa")){
                arr[count] = 0;
            }else {
                arr[count] = 1;
            }
            count++;
        }
        return arr;
    }


    public static int lengthCalculus(File file) throws IOException {
        String text = null;
        int count = 0;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((text = reader.readLine()) != null) {
            count++;
        }
        return count;
    }

}
