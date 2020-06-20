package math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

Example 1:
Input: 123
Output: "One Hundred Twenty Three"

Example 2:
Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"

Example 3:
Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

Example 4:
Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
public class IntegerToEnglishWords {

    static final String[] SINGLE = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine"};
    static final String[] DOUBLE = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty",
            "Seventy", "Eighty", "Ninety"};
    static final String[] LEVEL = new String[]{"", "Thousand", "Million", "Billion"};
    static final String[] UNDER20 = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
            "Sixteen", "Seventeen", "Eighteen", "Nineteen"};


    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        int billion = num / 1000000000;
        num -= billion * 1000000000;
        int million = num / 1000000;
        num -= million * 1000000;
        int thousand = num / 1000;
        num -= thousand * 1000;
        int rest = num;
        StringBuilder sb = new StringBuilder();
        if (billion != 0) {
            sb.append(getThree(billion));
            sb.append(" Billion");
        }
        if (million != 0) {
            sb.append(" ");
            sb.append(getThree(million));
            sb.append(" Million");
        }
        if (thousand != 0) {
            sb.append(" ");
            sb.append(getThree(thousand));
            sb.append(" Thousand");
        }
        if (rest != 0) {
            sb.append(" ");
            sb.append(getThree(rest));
        }
        return sb.toString().trim();
    }

    private String getThree(int num) {
        StringBuilder sb = new StringBuilder();
        if (num >= 100) {
            sb.append(SINGLE[num / 100]);
            sb.append(" Hundred");
            num %= 100;
        }
        sb.append(" ");
        sb.append(getTwo(num));
        return sb.toString().trim();
    }

    private String getTwo(int num) {
        if (num == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (num >= 10 && num < 20) {
            return UNDER20[num - 10];
        }
        if (num >= 20) {
            sb.append(DOUBLE[num / 10]);
            num %= 10;
        }
        if (num > 0) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(SINGLE[num]);
        }
        return sb.toString();
    }

    /*
    Method2: keeping dynamic levels
     */
    public String numberToWords2(int num) {
        if (num == 0) {
            return "Zero";
        }
        List<String> list = new ArrayList<>();
        int level = 0;
        while (num > 0) {
            int chunkNum = num % 1000;
            String chunk = helper(chunkNum, level);
            if (!chunk.equals("")) {
                list.add(helper(chunkNum, level));
            }
            level++;
            num /= 1000;
        }
        Collections.reverse(list);
        String result = String.join(" ", list);
        return result.trim();
    }

    private String helper(int num, int level) {
        if (num == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (num >= 100) {
            sb.append(SINGLE[num / 100]);
            sb.append(" Hundred");
            num %= 100;
        }
        if (num >= 10 && num < 20) {
            sb.append(" ");
            sb.append(UNDER20[num - 10]);
        } else {
            if (num >= 20) {
                sb.append(" ");
                sb.append(DOUBLE[num / 10]);
                num %= 10;
            }
            if (num > 0) {
                sb.append(" ");
                sb.append(SINGLE[num]);
            }
        }
        sb.append(" ");
        sb.append(LEVEL[level]);
        return sb.toString().trim();
    }
}
