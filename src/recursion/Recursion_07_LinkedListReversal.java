package recursion;

public class Recursion_07_LinkedListReversal {
    static class Node {
        public int data;
        public Node next;
        public Node(int date) {
            this.data = date;
            this.next = null;
        }
    }

    static void print(Node head) {
        if (head == null) return;
        System.out.print(head.data + " -> ");
        print(head.next);
    }

    public static Node reverse(Node head) {
        if (head == null || head.next == null) return head;
        Node p = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        print(node1);
        Node head = reverse(node1);
        print(head);


    }
}
