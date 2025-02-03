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