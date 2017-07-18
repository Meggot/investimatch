package Util;

import Model.Investment;
import Model.Loan;
import Model.ProductType;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bradleyw on 17/05/2017.
 */
public class LoanCsvLoader {
    public static List<Loan> loadLoansFromCsv(String filePath) throws FileNotFoundException {
        List<Loan> returnArray = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] nextLine;
        DateFormat completedDateFormat = new SimpleDateFormat("DD/MM/YYYY");
        try {
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[0].equals("loanId")) {
                    //Discard header
                    continue;
                }
                returnArray.add(new Loan(Integer.valueOf(nextLine[0]), Long.valueOf(nextLine[1]), ProductType.valueOf(nextLine[2]), Double.valueOf(nextLine[3]), completedDateFormat.parse(nextLine[4])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnArray;
    }
}
