package string;

public class StrStr {
    public static void main(String[] args) {
        StrStr solution = new StrStr();
        String str1 = "hello";
        String str2 = "ll";
        int result = solution.strStr(str1, str2);
        System.out.println(result);
    }
    public int strStr(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < str2.length())
            return -1;

        if (str2.length() == 0)
            return 0;

        long hash2 = getHash(str2, 0, str2.length() - 1);
        long hash1 = getHash(str1, 0, str2.length() - 1);
        if (hash1 == hash2) return 0;

        for (int i = 1; i <= str1.length() - str2.length(); i++) {
            hash1 = (hash1 - (str1.charAt(i - 1) - 'a') * (long) Math.pow(26, str2.length() - 1)) * 26 + str1.charAt(i + str2.length() - 1) - 'a';
            if (hash1 == hash2)
                return i;
        }
        return -1;
    }

    private long getHash(String input, int left, int right) {
        long hash = 0;
        for (int i = left; i <= right; i++) {
            hash = hash * 26 + input.charAt(i) - 'a';
        }
        return hash;
    }
}
