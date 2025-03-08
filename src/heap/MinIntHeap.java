package heap;

import java.util.Arrays;

public class MinIntHeap {
    private int capacity = 10;
    private int size = 0;

    int[] items = new int[capacity];

    private int getLeftChildIndex(int parentIndex) { return 2 * parentIndex + 1; }
    private int getRightChildIndex(int parentIndex) { return 2 * parentIndex + 2; }
    private int getParentIndex(int childIndex) {return (childIndex - 1) / 2; }

    private boolean hasLeftChild(int index){ return getLeftChildIndex(index) < size; }
    private boolean hasRightChild(int index){ return getRightChildIndex(index) < size; }
    private boolean hasParent(int index){ return getParentIndex(index) >= 0; }


    private int leftChild(int index){ return items[getLeftChildIndex(index)]; }
    private int rightChild(int index){ return items[getRightChildIndex(index)]; }
    private int parent(int index){ return items[getParentIndex(index)]; }

    private void swap(int i, int j) {
        int temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    private void ensureExtraCapacity() {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }

    public int peek() {
        if (size == 0) throw new IllegalArgumentException();
        return items[0];
    }

    public int poll() {
        if (size == 0) throw new IllegalArgumentException();
        int item = items[0];
        items[0] = items[size - 1];
        size --;
        heapifyDown();
        return item;
    }

    public void add(int item) {
        ensureExtraCapacity();
        items[size] = item;
        size++;
        heapifyUp();
    }

    public void insert(int item) {
        ensureExtraCapacity();
        items[size] = item;
        size++;

        int i = size-1;
        while (i > 0) {
            int parentIndex = getParentIndex(i);
            if (items[parentIndex] > items[i]) {
                swap(i, parentIndex);
                i = parentIndex;
            } else {
                return;
            }
        }

    }

    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && parent(index) > items[index]) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallChildrenIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallChildrenIndex = getRightChildIndex(index);
            }

            if (items[index] < items[smallChildrenIndex]) {
                break;
            } else {
                swap(index, smallChildrenIndex);
            }
            index = smallChildrenIndex;
        }
        while (hasRightChild(index) && rightChild(index) > items[index]) {
            swap(index, getRightChildIndex(index));
            index = getRightChildIndex(index);
        }
        /*int index = 0;
        while (index < size) {
            int left = leftChild(index);
            int right = rightChild(index);
            int largerIndex = left > right ? getLeftChildIndex(index) : getRightChildIndex(index);
            if (items[index] > items[largerIndex]) {
                swap(index, largerIndex);
                index = largerIndex;
            } else {
                return;
            }
        }*/
    }

    public static void main(String[] args) {
        MinIntHeap heap = new MinIntHeap();
        heap.add(5);
        heap.add(4);
        heap.add(3);
        heap.add(10);
        heap.add(1);
        heap.add(20);
        System.out.println("POLL: " + heap.poll());
        System.out.println("POLL: " + heap.poll());
        System.out.println("POLL: " + heap.poll());
        System.out.println("POLL: " + heap.poll());
        System.out.println("POLL: " + heap.poll());
        System.out.println("POLL: " + heap.poll());
    }

}
