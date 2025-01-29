

## Directed Acyclic Graph (No-Cycle):
<img src="../../resources/graph1.png" width="180" height="200">

````javascript
const graph = {
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
const graph = {
    a: ['b', 'c'],
    b: ['d'],
    c: ['e'],
    d: ['f'],
    e: ['a'],
    f: []
}
````
