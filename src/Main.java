
import java.io.*;
import java.util.*;

public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    static int[][][] profitData = new int[MONTHS][DAYS][COMMS];

    public static void main(String[] args) {
        loadData();
        {
                }
            }
    public static void loadData() {
        for (int m = 0; m < MONTHS; m++) {
            String fileName = "Data_Files/" + months[m] + ".txt";
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String line;
                line = br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] p = line.trim().split(",");
                    int day = Integer.parseInt(p[0].trim()) - 1;
                    String comm = p[1];
                    int profit = Integer.parseInt(p[2].trim());
                    int idx = -1;
                    for (int i = 0; i < COMMS; i++) {
                        if (commodities[i].equalsIgnoreCase(comm)) {
                            idx = i;
                            break;
                        }
                    }
                    if (idx == -1) continue;
                    profitData[m][day][idx] = profit;
                }
                br.close();
            } catch (Exception e) {
            }
        }
    }

    public static String mostProfitableCommodityInMonth(int month) {
        if (month < 1 || month > 12) return "INVALID_MONTH";

        int m = month - 1;
        int[] totals = new int[COMMS];

        for (int d = 0; d < DAYS; d++) {
            for (int c = 0; c < COMMS; c++) {
                totals[c] += profitData[m][d][c];
            }
        }
        int maxIndex = 0;
        for (int i = 1; i < COMMS; i++) {
            if (totals[i] > totals[maxIndex]) {
                maxIndex = i;
            }
        }
        return commodities[maxIndex];
    }

    public static int totalProfitOnDay(int month, int day) {
        if (month < 1 || month > 12) return -99999;
        if (day < 1 || day > 28) return -99999;

        int m = month - 1;
        int d = day - 1;

        int total = 0;
        for (int c = 0; c < COMMS; c++) {
            total += profitData[m][d][c];
        }
        return total;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
        if (from < 1 || to > 28 || from > to) return -99999;

        int idx = -1;
        for (int i = 0; i < COMMS; i++) {
            if (commodities[i].equalsIgnoreCase(commodity)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) return -1;
        int total = 0;
        for (int m = 0; m < MONTHS; m++) {
            for (int d = from - 1; d <= to - 1; d++) {
                total += profitData[m][d][idx];
            }
        }
        return total;
    }

    public static int bestDayOfMonth(int month) {
        if (month < 1 || month > 12) return -1;

        int dayOfBest = 1;
        int maxProfit = Integer.MIN_VALUE;

        for (int day = 0; day < DAYS; day++) {
            int totalProfit = 0;
            for (int comm = 0; comm < COMMS; comm++) {
                totalProfit += profitData[month - 1][day][comm];
            }
            if (totalProfit > maxProfit) {
                maxProfit = totalProfit;
                dayOfBest = day + 1;
            }
        }
        return dayOfBest;
    }

    public static String bestMonthForCommodity(String comm) {
        return "DUMMY";
    }

    public static int consecutiveLossDays(String comm) {
        return 1234;
    }

    public static int daysAboveThreshold(String comm, int threshold) {
        return 1234;
    }

    public static int biggestDailySwing(int month) {
        return 1234;
    }

    public static String compareTwoCommodities(String c1, String c2) {
        return "DUMMY is better by 1234";
    }

    public static String bestWeekOfMonth(int month) {
        return "DUMMY";
    }
}



