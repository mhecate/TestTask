import java.util.Comparator;
import java.util.Date;

public class Transaction {
    private String date;
    private String desc;
    private double deposits;
    private double withdrawls;
    private double balance;
    public Transaction(){}
    public Transaction(String date, String desc, double deposits, double withdrawls, double balance) {
        this.date = date;
        this.desc = desc;
        this.deposits = deposits;
        this.withdrawls = withdrawls;
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getDeposits() {
        return deposits;
    }

    public void setDeposits(double deposits) {
        this.deposits = deposits;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getWithdrawls() {
        return withdrawls;
    }

    public void setWithdrawls(double withdrawls) {
        this.withdrawls = withdrawls;
    }

    public String toString() {
    return "" + getWithdrawls();
    }



}
