package graph.leetcode;

import graph.Pair;

import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(wordList == null || wordList.size() == 0) return 0;
        Set<String> wordSet = new HashSet<>();
        Queue<Pair> q = new LinkedList<>();
        wordSet.addAll(wordList);
        if(!wordSet.contains(endWord)) return 0;

        q.add(new Pair(beginWord, 1));
        wordSet.remove(beginWord); // mark as visited

        while(!q.isEmpty()) {
            Pair it = q.poll();
            String currWord = (String) it.first;
            int currDistance = (int)it.last;
            System.out.println("CurrWord =======>" + currWord);

            if(currWord.equals(endWord)) {
                System.out.println("Returning....");
                return currDistance;
            }

            for(int i=0; i< currWord.length(); i++) {
                for(int ch = 'a'; ch <= 'z'; ch ++) {
                    String newWord = currWord.substring(0, i) + (char)ch + currWord.substring(i+1);
                    int newDistance = currDistance + 1;
                    System.out.println("CHECKING:" + newWord);

                    if(wordSet.contains(newWord)) {
                        System.out.println("InQ_Before: " + newWord + " --> " +newDistance);
                        Pair newPair = new Pair(newWord, newDistance);
                        wordSet.remove(newWord); // mark as visited
                        System.out.println("wordSet:" + wordSet);
                        q.offer(newPair);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        int result = wordLadder.ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")); // 5
        int result1 = wordLadder.ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log")); // 5
        System.out.println("RESULT: " + result1);
    }
}
