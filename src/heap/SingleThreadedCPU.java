package heap;

import java.util.*;

/*
    Company Tags                : Google
    Leetcode Link               : https://leetcode.com/problems/single-threaded-cpu/

    Current Complexity:
       - Sorting Tasks: O(NlogN) (due to sorting by enqueue time)
       - Priority Queue Operations: O(NlogN)(each insertion/removal is O(logN))
       - Overall Complexity: O(NlogN)
*/
public class SingleThreadedCPU {
    static class Task {
        public int taskId;
        public int enqueueTime;
        public int processingTime;
        public Task(int taskId, int enqueueTime, int processingTime) {
            this.taskId = taskId;
            this.enqueueTime = enqueueTime;
            this.processingTime = processingTime;
        }

        @Override
        public String toString() {
            //return "{enqueueTime: " + enqueueTime + ", processingTime: " + processingTime + ", taskId: " + taskId + "}";
            return taskId+"";
        }
    }

    public int[] getOrder(int[][] tasks) {
        int[] result = new int[tasks.length];
        int resultIdx = -1;

        // Step1: sort the tasks based on shortest processing time
        Task[] sortedTask = new Task[tasks.length];
        for(int i=0; i< tasks.length; i++) sortedTask[i] = new Task(i, tasks[i][0], tasks[i][1]);
        Arrays.sort(sortedTask, Comparator.comparingInt(a -> a.enqueueTime));
        System.out.println("SORTED_TASKS: " + Arrays.toString(sortedTask));

        // Priority queue to pick the shortest processing time first (if equal, pick smallest taskId)
        PriorityQueue<Task> pq = new PriorityQueue<>((a, b) ->
                a.processingTime == b.processingTime ? a.taskId - b.taskId : a.processingTime - b.processingTime);


        int currTime = 0, idx = 0;
        while (idx < tasks.length || !pq.isEmpty()) {
            // If no tasks are available, move time to the next available task
            if (pq.isEmpty() && currTime < sortedTask[idx].enqueueTime) {
                currTime = sortedTask[idx].enqueueTime;
            }

            // Add all tasks that are available at current time
            while (idx < sortedTask.length && sortedTask[idx].enqueueTime <= currTime) {
                pq.offer(sortedTask[idx]);
                idx++;
            }

            // Process the next task
            if (!pq.isEmpty()) {
                Task currTask = pq.poll();
                currTime += currTask.processingTime;
                result[++resultIdx] = currTask.taskId;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        SingleThreadedCPU solution = new SingleThreadedCPU();
        int[][] tasks = new int[][]{{1,2},{2,4},{3,2},{4,1}};
        System.out.println(Arrays.toString(solution.getOrder(tasks))); // [0,2,3,1]
        tasks = new int[][]{{7,10},{7,12},{7,5},{7,4},{7,2}};
        System.out.println(Arrays.toString(solution.getOrder(tasks))); // [4,3,2,0,1]

    }
}
