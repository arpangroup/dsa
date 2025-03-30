package recursion;

import java.util.*;

/*
    Company Tags                :
    LeetCode                    : 93
    Leetcode Link               : https://leetcode.com/problems/restore-ip-addresses/
*/
public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        if(s.length() > 12) return new ArrayList<>();

        List<String> result = new ArrayList<>();
        backtrack(s, 0, 0, "", result);
        return result;
    }

    public void backtrack(String str, int idx, int dot, String currIp, List<String> result) {
        if(dot == 4 && idx == str.length()) {
            result.add(currIp.substring(0, currIp.length() - 1)); // Remove trailing dot
        }

        for(int i=idx; i < Math.min(idx+3, str.length()) ; i++) {
            String segment = str.substring(idx, i+1); // end index is not inclusive, so add 1
            int num = Integer.parseInt(segment);
            if(num > 255 || (segment.startsWith("0") && segment.length() > 1)) continue; // Prevent leading zeros

            // take current char
            backtrack(str, i+1, dot+1, currIp + segment + ".", result);
        }
    }

    public static void main(String[] args) {
        RestoreIPAddresses solution = new RestoreIPAddresses();
        System.out.println(solution.restoreIpAddresses("25525511135")); // ["255.255.11.135","255.255.111.35"]
        System.out.println(solution.restoreIpAddresses("0000")); // ["0.0.0.0"]
        System.out.println(solution.restoreIpAddresses("101023")); // ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
    }
}
