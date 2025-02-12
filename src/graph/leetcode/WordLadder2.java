package graph.leetcode;

import graph.Pair;

import java.util.*;

public class WordLadder2 {
    public List<List<String>> ladderLength(String beginWord, String endWord, String[] wordList) {
        List<List<String>> allPath = new ArrayList<>();
        if(wordList == null || wordList.length == 0) return allPath;

        Set<String> wordSet = new HashSet<>(Arrays.asList(wordList));
        if(!wordSet.contains(endWord)) return allPath;

        Queue<List<String>> q = new ArrayDeque<>();
        q.add(Collections.singletonList(beginWord));

        Set<String> visited = new HashSet<>();
        boolean found = false;

        visited.add(beginWord); // mark as visited
        if (wordSet.contains(beginWord)) wordSet.remove(beginWord);


        int level = 0;
        while(!q.isEmpty() && !found) {
            int currLevelSize = q.size();
            Set<String> tempVisited = new HashSet<>();


            /*List<String> currPath = q.poll();
            if (currPath.size() > level) {
                level ++;
                for (String it : currPath) {
                    visited.remove(it); // remove all word which are already visited on previous levels
                }
            }*/


            for (int label=0; label< currLevelSize; label++) {
                List<String> currPath = q.poll();
                String currWord = currPath.getLast();
                System.out.println("CurrWord =======>" + currWord);

                if(currWord.equals(endWord)) {
                    found = true; // Stop at the shortest level
                    allPath.add(currPath);

                }

                // iterate over adj neighbours
                char[] wordChars = currWord.toCharArray();
                for(int i=0; i< currWord.length(); i++) {
                    char originalChar = wordChars[i];
                    for(char ch = 'a'; ch <= 'z'; ch ++) {
                        if (ch == originalChar) continue;
                        wordChars[i] = ch;
                        String newWord = new String(wordChars);
                        System.out.println("CHECKING:" + newWord);

                        if(wordSet.contains(newWord) && !visited.contains(newWord)) {
//                            System.out.println("InQ_Before: " + newWord + " --> " +newWord);
                            List<String> newPath = new ArrayList<>(currPath);
                            newPath.add(newWord);
                            q.offer(new ArrayList<>(newPath));
                            tempVisited.add(newWord);
                        }
                    }
                    wordChars[i] = originalChar; // Restore original character
                }
            }
            visited.remove(tempVisited);
        }
        return allPath;
    }

    public static void main(String[] args) {
        WordLadder2 wordLadder = new WordLadder2();
        String[] wordList1 = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList2 = List.of("hot", "dot", "dog", "lot", "log");

        List<List<String >> result = wordLadder.ladderLength("hit", "cog", wordList1);
        System.out.println("RESULT......: ");
        result.forEach(strings -> System.out.println(strings));


        Queue<ArrayList<String>> q = new ArrayDeque<>();
        ArrayList<String> init= new ArrayList<>();
        init.add("hello");
        q.add(init);

    }
}
