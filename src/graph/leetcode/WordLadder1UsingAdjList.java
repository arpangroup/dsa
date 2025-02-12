package graph.leetcode;

import graph.Pair;

import java.util.*;

public class WordLadder1UsingAdjList {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(wordList == null || wordList.size() == 0) return 0;
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(beginWord, 1));
        if (wordSet.contains(beginWord)) wordSet.remove(beginWord); // visited


        while(!q.isEmpty()) {
            Pair it = q.poll();
            String currWord = (String) it.first;
            int currDistance = (int) it.last;
            System.out.println("CurrWord =======>(" + currWord + ", " +currDistance + ")" );

            if(currWord.equals(endWord)) return currDistance;

            List<String> neighbours = getNeighbours(currWord, wordSet);
            for (String nei : neighbours) {
                if (wordSet.contains(nei)) {
                    q.add(new Pair<>(nei, currDistance + 1));
                    wordSet.remove(nei);
                }
            }
        }
        return 0;
    }

    private List<String> getNeighbours(String word, Set<String> wordSet) {
        Set<String> neighbours = new HashSet<>();
        for (int i=0; i< word.length(); i++) {
            for(int ch = 'a'; ch <= 'z'; ch ++) {
                String newWord = word.substring(0, i) + (char)ch + word.substring(i+1);
                if (newWord.equals(word)) {
                    continue;
                }
                if (wordSet.contains(newWord)) {
                    neighbours.add(newWord);
                }
            }
        }
        return neighbours.stream().toList();
    }

    /*private Map<String, List<String>> buildGraph(List<String> wordList, String beginWord) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put(beginWord, getNeighbours(beginWord, wordList));


        for (String s : wordList) {
            List<String> neighbours = getNeighbours(s, wordList);
            if (graph.containsKey(s)) {
                graph.get(s).addAll(neighbours);
            } else {
                graph.put(s,neighbours);
            }
        }
        return graph;
    }*/

    private void printGraph(Map<String, List<String>> graph) {
        graph.forEach((k, v) -> {
            System.out.print(k + " -> ");
            v.forEach(s -> System.out.print(s + " "));
            System.out.println();
        });
    }

    public static void main(String[] args) {
        WordLadder1UsingAdjList wordLadder = new WordLadder1UsingAdjList();
        List<String> wordList1 = List.of("hot", "dot", "dog", "lot", "log", "cog");
        List<String> wordList2 = List.of("hot", "dot", "dog", "lot", "log");

        int result = wordLadder.ladderLength("hit", "cog", wordList1); // 5
        System.out.println("RESULT: " + result);
    }
}
