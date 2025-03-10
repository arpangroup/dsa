package heap;

/*
    Company Tags  : Facebook
    LeetCode      : 621
    Leetcode Link : https://leetcode.com/problems/task-scheduler/
 */
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        return 0;
    }

    public static void main(String[] args) {
        TaskScheduler solution = new TaskScheduler();
        char[] tasks = new char[]{'A','A','A','B','B','B'};
        int n = 2;
        System.out.println(solution.leastInterval(tasks, n)); // 8
        tasks = new char[]{'A','C','A','B','D','B'};
        n = 1;
        System.out.println(solution.leastInterval(tasks, n)); // 6
        tasks = new char[]{'A','A','A','B','B','B'};
        n = 3;
        System.out.println(solution.leastInterval(tasks, n)); // 10

    }
}
