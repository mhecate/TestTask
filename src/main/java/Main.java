import com.opencsv.CSVReader;

import java.text.ParseException;
import java.util.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("VVedite datu v formate  :  29-Mar-2020");
        String inputDate = in.next();

        List<Transaction> list = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("C:\\testovoezadanie\\Records.csv");
            CSVReader csvReader = new CSVReader(fileReader);
            String[] nextRecord;
            int row = 0;
            //System.out.print(inputDate);
            while ((nextRecord = csvReader.readNext()) != null) {
                if (row == 0) {
                    row++;
                    continue;
                }
                Transaction transaction = new Transaction();
                if (inputDate.equalsIgnoreCase(nextRecord[0])) {
                    transaction.setDate(nextRecord[0]);
                    transaction.setDesc(nextRecord[1]);
                    transaction.setDeposits(Double.parseDouble(nextRecord[2].replaceAll(",", "")));
                    transaction.setWithdrawls(Double.parseDouble(nextRecord[3].replaceAll(",", "")));
                    transaction.setBalance(Double.parseDouble(nextRecord[4].replaceAll(",", "")));
                    list.add(transaction);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(inputDate + " minimalnoe znachenie polya Witdrawls " + " min : " + getMin(list));
        System.out.println(inputDate + " maximalnoe znachenie polya Witdrawls " + " max : " + getMax(list));
        System.out.println(inputDate + " srednee znachenie polya Witdrawls " + " average : " + getAverage(list));

        List<Transaction> sortedList = list.stream()
                .sorted((o1, o2) -> o1.getDesc().compareTo(o2.getDesc()))
                .collect(Collectors.toList());

        String str = sortedList.get(0).getDesc();

        int fromIndex = 1;
        int toIndex = 0;
        for (int i = 0; i < sortedList.size(); i++) {
            if (str.equals(sortedList.get(i).getDesc())) {
                toIndex++;
            } else if (!str.equals(sortedList.get(i).getDesc())) {
                System.out.println(inputDate + " " + str + " min : " + getMin(sortedList.subList(fromIndex - 1, toIndex)));
                System.out.println(inputDate + " " + str + " max : " + getMax(sortedList.subList(fromIndex - 1, toIndex)));
                System.out.println(inputDate + " " + str + " average : " + getAverage(sortedList.subList(fromIndex - 1, toIndex)));
                str = sortedList.get(i).getDesc();
                fromIndex = toIndex;
            }
        }
    }


    private static String getMin(List<Transaction> list) {
        Transaction minByWithDrawls = list
                .stream()
                .min(Comparator.comparing(Transaction::getWithdrawls))
                .orElseThrow(NoSuchElementException::new);
        return minByWithDrawls.toString();
    }


    private static String getMax(List<Transaction> list) {
        Transaction minByWithDrawls = list
                .stream()
                .max(Comparator.comparing(Transaction::getWithdrawls))
                .orElseThrow(NoSuchElementException::new);
        return minByWithDrawls.toString();
    }


    private static String getAverage(List<Transaction> list) {

        return String.valueOf(list
                .stream()
                .mapToDouble(Transaction::getWithdrawls)
                .average().orElse(Double.NaN));
    }
}
