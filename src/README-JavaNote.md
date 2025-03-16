1. Java Collection



## Iterate Loop:
Using `IntStream.range()`:
````java
IntStream.range(1, 6).forEach(System.out::println); // 1 to 5
````
Using `IntStream.rangeClosed()`:
````java
IntStream.rangeClosed(1, 5).forEach(System.out::println); // 1 to 5 (inclusive)
````
````java
IntStream.range(0, list.size())
        .forEach(i -> System.out.println("Index: " + i + ", Value: " + list.get(i)));
````




## 1. Java Collection
### 1.1.  `Array` to `List`:

````java
String[] array = {"A", "B", "C"};
List<String> list = Arrays.asList(array); // Fixed-size list
````

````java
int[] array = {1, 2, 3, 4, 5};
List<Integer> list = Arrays.stream(array)  // Convert int[] to IntStream
        .boxed()        // Convert to Stream<Integer>
        .collect(Collectors.toList()); // Collect to List<Integer>

//OR, using IntStream
List<Integer> list = IntStream.of(array).boxed().collect(Collectors.toList()); 
````

### 1.2.  `List` to `Array`:
````java
int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
````

### Some important List method:
1. **removeLast** vs `removeFirst` vs *addFirst* (`ArrayList` does not support `removeLast()` or `addFirst()`
    ````java
    List<Integer> list = new ArrayList<>();
    list.add(10);
    list.add(11);
    list.add(12);
    
    // list.removeLast();  // ❌ Compilation error
    // list.addFirst(5);   // ❌ Compilation error
  
    // Manually remove last element
    list.remove(list.size() - 1);
  
    // Manually add element at first position
    list.add(0, 0);
    ````
- Use `LinkedList` for `removeLast()` and `a`ddFirst()`
    ````java
    LinkedList<Integer> list = new LinkedList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    
    list.removeLast(); // ✅ Works
    list.addFirst(0);  // ✅ Works
    ````
    Best Practice:
  - Use `ArrayList` if you need fast random access (`get(index)` is O(1)).
  - Use `LinkedList` if you frequently add/remove elements from the beginning or end
2. `subList`
    ````java
    list.subList(0, k);
    ````
3. fdfd

### 1.2. `ArrayList` vs `LinkedList` in Java
    
| Feature         | `ArrayList` | `LinkedList` |
|---------------|------------|-------------|
| **Internal Structure** | Resizable array | Doubly linked list |
| **Access Time (`get(index)`)** | O(1) – Direct index access | O(n) – Sequential traversal |
| **Insertion (`add(E)`)** | O(1) (amortized) at the end, O(n) in the middle | O(1) at the beginning/middle (with iterator), O(n) if direct index lookup is needed |
| **Deletion (`remove(index)`)** | O(n) – Shifting elements needed | O(1) if at the beginning/middle (with iterator), O(n) if direct index lookup is needed |
| **Memory Overhead** | Less (only stores objects) | Higher (stores objects + node references) |
| **Iteration Performance** | Faster (cache-friendly) | Slower (cache misses due to node traversal) |
| **Best Use Case** | Frequent random access, less insert/delete operations | Frequent insertions/deletions, less random access |


### 1.2. `List.of()` vs `Arrays.asList()` in Java

| Feature            | `List.of()`                                            | `Arrays.asList()`                                       |
|--------------------|--------------------------------------------------------|---------------------------------------------------------|
| **Mutability**    | **Immutable** (cannot modify)                          | **Fixed-size**, but elements can be modified            |
| **Null Elements** | Does not allow `null` values                           | Allows `null` values                                    |
| **Modification**  | Throws `UnsupportedOperationException` on modification | Cannot add/remove elements but can modify existing ones |
| **Performance**   | More optimized (backed by an immutable implementation) | Slightly less optimized (backed by an array)            |
| **Usage**        | `List<String> list = List.of("a", "b", "c");`          | `List<String> list = Arrays.asList("a", "b", "c");`     |

````java
public class ListExample {
    public static void main(String[] args) {
        // Using List.of() - Immutable list
        List<String> immutableList = List.of("A", "B", "C");
        // immutableList.add("D"); // Throws UnsupportedOperationException

        // Using Arrays.asList() - Fixed-size list
        List<String> fixedSizeList = Arrays.asList("X", "Y", "Z");
        fixedSizeList.set(0, "New X"); // Allowed
        // fixedSizeList.add("W"); // Throws UnsupportedOperationException

        List<String> mutableList = new ArrayList<>(List.of("A", "B", "C"));
        mutableList.add("D");  // ✅ Allowed
        mutableList.set(1, "New B"); // ✅ Allowed
    }
}
````