import com.opencsv.CSVReader;

import java.text.ParseException;
import java.util.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;


public class Main {
    private static final long BYTE_TO_MB_CONVERSION_VALUE = 1024 * 1024;

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
                String date = nextRecord[0];
                if(inputDate.equalsIgnoreCase(date)) {
                    transaction.setDate(date);
                    transaction.setDesc(nextRecord[1]);
                    transaction.setDeposits(Double.parseDouble(nextRecord[2].replaceAll(",", "")));
                    transaction.setWithdrawls(Double.parseDouble(nextRecord[3].replaceAll(",", "")));
                    transaction.setBalance(Double.parseDouble(nextRecord[4].replaceAll(",", "")));
                    list.add(transaction);
                    //System.out.println(transaction.getDesc());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(inputDate + "minimalnoe znachenie polya Witdrawls " + " min : " + getMin(list));
        System.out.println(inputDate + "maximalnoe znachenie polya Witdrawls " + " max : " + getMax(list));
        System.out.println(inputDate + "srednee znachenie polya Witdrawls " + " average : " + getAverage(list));
        List<Transaction> sortedList = list.stream()
                .sorted((o1, o2) -> o1.getDesc().compareTo(o2.getDesc()))
                .collect(Collectors.toList());
        String str = sortedList.get(0).getDesc();
        int fromIndex = 0;
        int toIndex = 0;
        for(int i = 1; i<sortedList.size(); i++){
            if(str.equals(sortedList.get(i).getDesc())){
                toIndex++;
            }
            else if(!str.equals(sortedList.get(i).getDesc())){
                System.out.println(inputDate + " " + str + " min : " + getMin(sortedList.subList(fromIndex, toIndex)));
                System.out.println(inputDate + " " + str + " max : " + getMax(sortedList.subList(fromIndex, toIndex)));
                System.out.println(inputDate + " " + str + " average : " + getAverage(sortedList.subList(fromIndex, toIndex)));
                //System.out.println(sortedList.get(i).getDesc());
                str = sortedList.get(i).getDesc();
                fromIndex =toIndex;
            }

        }

        long start = System.nanoTime();
        double elapsedTimeInSec = (System.nanoTime() - start) * 1.0e-9;
        System.out.println(elapsedTimeInSec + " seconds");

        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used Memory before: " + usedMemoryBefore);
        // working code here
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory increased: " + (usedMemoryAfter-usedMemoryBefore));
;
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

    public static void forFun(){}
}
