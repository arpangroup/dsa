## Directed Acyclic Graph (No-Cycle):
<img src="../../resources/graph1.png" width="180" height="200">

````javascript
const graphDirected = {
    a: ['b', 'c'],
    b: ['d'],
    c: ['e'],
    d: ['f'],
    e: [],
    f: []
}
````

## Cyclic Graph
<img src="../../resources/graph2.png" width="180" height="200">

````javascript
const graphDirected = {
    a: ['b', 'c'],
    b: ['d'],
    c: ['e'],
    d: ['f'],
    e: ['a'],
    f: []
}
````

## Undirected Graph
![graph3.png](../../resources/graph3.png)

### Adjacency Matrix Representation:
For above diagram n = 7, 
- so we will create a adjMatrix of size (n+1) * (N+1). Because array index is start at 0
![img_2.png](img_2.png)
- 
````java
adj[n+1][n+1]
````


## Topological Sort:
![topological_sort.png](../../resources/topological_sort.png)


## Q: Is tracking the parent node necessary for cycle detection in an undirected graph, or is the visited set alone sufficient?
The **parent node check is necessary** to distinguish between a genuine cycle and a backtracking revisit.

The **visited set** alone is insufficient because:

### 1. False Positives Without Parent Check:
- If a node v is visited again, it could be due to a back edge (a cycle) or simply because v was visited from its neighbor (its parent in DFS).
- Without tracking the **parent**, we might incorrectly detect a cycle when encountering a previously visited node that is actually just the DFS parent.

### 2. Example of Incorrect Detection Without Parent:
Consider this undirected graph:
````lua
   1 -- 2
   |  
   3
````
- DFS from `1`: Mark `1` as visited → Visit `2` → Mark `2` as visited.
- Backtrack and visit `3` from `1`. Mark `3` as visited.
- When `3` sees `1` again, if we only check `visited`, we might think it's a cycle, but it's just the parent.

### 3. Correct Detection With Parent:
- If `v` is visited and is **not the parent** of the current node, it means there's a back edge, forming a cycle.
- Otherwise, if `v` is the parent, it’s just part of the DFS traversal, not a cycle.


![img_3.png](img_3.png)