package de.stadler.marco.challenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/***
 * @author Marco Stadler
 */
public class CSVReader {
    private static final String DELIMITER = ",";

    private CSVReader() {

    }

    /***
     * reads out the data of the csv-file
     * @param file the file that yields the data
     * @return the mapped data as a list of Location objects
     */
    public static List<Location> read(File file) {
        ArrayList<Location> result = new ArrayList<>();

        try (FileReader fr = new FileReader(file, StandardCharsets.UTF_8)) {
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] tempArr;
            boolean isFirstLineFlag = true;
            while ((line = br.readLine()) != null) {

                if (isFirstLineFlag) {
                    //dont safe the col names
                    isFirstLineFlag = false;
                    line = br.readLine();
                }

                tempArr = line.split(DELIMITER);

                Location currLocation = new Location(
                        Integer.parseInt(tempArr[0]),
                        tempArr[1],
                        tempArr[2],
                        tempArr[3],
                        tempArr[4],
                        tempArr[5],
                        Double.parseDouble(tempArr[6]),
                        Double.parseDouble(tempArr[7])
                );

                result.add(currLocation);
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }
}
