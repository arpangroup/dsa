## Sliding Window

https://leetcode.com/problem-list/sliding-window/

https://medium.com/@ayushisharma5141/sliding-window-approach-types-and-problems-b1e256e2e92b


<table>
<tr>
<td>Fixed Size</td>
<td>Variable Size</td>
</tr>
<tr>
<td>
<pre>
public static void fixedSlidingWindow(int[] arr, int k) {
    int left =0, right = 0;           // step1: initialization
    while (right < arr.length) {
        sum += arr[right];            // Step2: Accumulate result <br/>
        if(windowSize < k) right ++;  // Step3: grow till window size
        else if (windowSize == k) {            
            max = Math.max(max, sum); // Step4: calculate ans <br/>
            // Step5: Slide the window ==> Remove Left + slide(i++; j++)
            sum = sum - arr[left];    // Step5.1. Remove calculation for left
            left ++; right++;         // Step5.2. Slide the window
        }
    }
    
}
</pre>
</td>
<td>
<pre>
public static void variableSlidingWindow(int[] arr, int k) {
    int left = 0, right = 0;
    int sum = 0, max = 0;
    while (right < arr.length) {
        sum += arr[right]; <br/>
        if (sum < k) right++;
        if (sum == k) {
            max = Math.max(max, right - left + 1);
            right++;
        } else if (sum > k) {
            //while (sum >  k) {sum -= arr[left]; left++}
            sum -= arr[left];
            left++;
            right++;
        }
    }
    return maxWindowSize;
}
</pre>
</td>
</tr>
</table>


## Template: Fixed Size Window:
````java
public static void fixedSlidingWindow(int[] arr, int k) {
    int left =0, right = 0;           // step1: initialization
    while (right < arr.length) {
        sum += arr[right];            // Step2: Accumulate result

        if(windowSize < k) right ++;  // Step3: grow till window size
        else if (windowSize == k) {            
            max = Math.max(max, sum); // Step4: calculate ans

            // Step5: Slide the window ==> Remove Left + slide(i++; j++)
            sum = sum - arr[left];    // Step5.1. Remove calculation for left
            left ++; right++;         // Step5.2. Slide the window
        }
    }
}
````

## Variable Size Sliding Window
````java
public static void variableSlidingWindow(int[] arr, int k) {
    int left = 0, right = 0;

    int sum = 0, max = 0;
    while (right < arr.length) {
        sum += arr[right];

        if (sum < k) right++;
        if (sum == k) {
            max = Math.max(max, right - left + 1);
            right++;
        } else if (sum > k) {
            //while (sum >  k) sum -= arr[left];
            sum -= arr[left];
            left++;
            right++;
        }
    }
    return maxWindowSize;
}
````

## Fixed Size Sliding Window
1.  [Find sum of all K size subarrays(560)](https://leetcode.com/problems/subarray-sum-equals-k/)
    1. **Range Sum of Sorted Subarray Sums** [LC-1508](https://leetcode.com/problems/range-sum-of-sorted-subarray-sums/) `Heap`
    2. **Sum of Subarray Ranges** [LC-2104](https://leetcode.com/problems/sum-of-subarray-ranges/) `stack & queue`
    2. Sum of Subarray Minimums [LC-907](https://leetcode.com/problems/sum-of-subarray-minimums/) `stack & queue`
    3. 
2.  [Find All Anagrams in a String(438)](https://leetcode.com/problems/find-all-anagrams-in-a-string/description/)
3.  [Maximum Sum of Distinct Subarrays With Length K(2461)](https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/description/)
4.  [Substring with Concatenation of All Words(30)](https://leetcode.com/problems/substring-with-concatenation-of-all-words/)
5.  [Repeated DNA Sequences(187)](https://leetcode.com/problems/repeated-dna-sequences/description/)
6.  [Permutation in String(567)](https://leetcode.com/problems/permutation-in-string/description/)
7.  [Maximum Average Subarray I(643)](https://leetcode.com/problems/maximum-average-subarray-i/description/)
8.	[Minimum Number of K Consecutive Bit Flips(995)](https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/description/)
9.	[Find K-Length Substrings With No Repeated Characters(1100)](https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/solutions/1772470/Java-or-Simple-or-Sliding-Window-template-or-Explained/)
10.	[Minimum Swaps to Group All 1’s Together (1151)](https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/solutions/2128446/Java-or-Sliding-Window-or-Explained/)
11.	[Diet Plan Performance(1176)](https://leetcode.com/problems/diet-plan-performance/solutions/1773821/Java-or-Sliding-Window-Template-or-Explained/)
12.	[Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold(1343)](https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/solutions/1772628/Java-or-Sliding-Window-template-or-Explained/)
13.	[Maximum Points You Can Obtain from Cards(1423)](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/description/)
14.	[Maximum Number of Vowels in a Substring of Given Length(1456)](https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/)
15.	[Defuse the Bomb(1652)](https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/)
16.	[Substrings of Size Three with Distinct Characters(1876)](https://leetcode.com/problems/substrings-of-size-three-with-distinct-characters/description/)
17.	[K Radius Subarray Averages(2090)](https://leetcode.com/problems/substrings-of-size-three-with-distinct-characters/description/)
18.	[Minimum Swaps to Group All 1’s Together II(2134)](https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/description/)
19.	[Find the K-Beauty of a Number(2269)](https://leetcode.com/problems/find-the-k-beauty-of-a-number/description/)
20.	[Minimum Recolors to Get K Consecutive Black Blocks(2379)](https://leetcode.com/problems/find-the-k-beauty-of-a-number/description/)



## Variable Size Sliding Window
1.	[Longest Substring Without Repeating Characters(3)](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
2.  [Max Consecutive Ones III(1004)](https://leetcode.com/problems/max-consecutive-ones-iii/)
2.	[Longest Repeating Character Replacement(424)](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
3.	[Sliding Window Maximum(239)](https://leetcode.com/problems/sliding-window-maximum/)
4.	[**Minimum Window Substring(76)**](https://leetcode.com/problems/minimum-window-substring/)
5.	[Minimum Size Subarray Sum(209)](https://leetcode.com/problems/minimum-size-subarray-sum/)
6.	[Minimum Consecutive Cards to Pick Up(2260)](https://leetcode.com/problems/minimum-size-subarray-sum/)
7.	[Maximum Erasure Value(1695)](https://leetcode.com/problems/maximum-erasure-value/)
8.	[Fruit Into Baskets(904)](https://leetcode.com/problems/fruit-into-baskets/)
9.	[Count Number of Nice Subarrays(1248)](https://leetcode.com/problems/count-number-of-nice-subarrays/)
10.	[Arithmetic Slices(413)](https://leetcode.com/problems/arithmetic-slices/)
11.	[**Subarrays-with-k-different-integers(992)**](https://leetcode.com/problems/subarrays-with-k-different-integers/)