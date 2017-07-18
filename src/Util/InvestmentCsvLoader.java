package Util;

import Model.Investment;
import Model.ProductType;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bradleyw on 17/05/2017.
 */
public class InvestmentCsvLoader {

    public static List<Investment> loadInvestmentsFromFile(String filePath) throws FileNotFoundException {
        List<Investment> returnArray = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String [] nextLine;
        try {
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[0].equals("investor"))
                {
                    //discard header
                    continue;
                }
                returnArray.add(new Investment(nextLine[0],Long.valueOf(nextLine[1]),ProductType.valueOf(nextLine[2]),Double.valueOf(nextLine[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnArray;
    }
}
