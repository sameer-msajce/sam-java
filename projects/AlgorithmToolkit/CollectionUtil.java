package algorithmtoolkit;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Generic collection utilities: Stack and Queue.
 */
public class CollectionUtil {

    // -------------------- GENERIC STACK ---------------------
    public static class Stack<T> {
        private LinkedList<T> list = new LinkedList<>();

        /** Push element to stack — O(1) */
        public void push(T value) {
            list.addFirst(value);
        }

        /** Pop element — O(1) */
        public T pop() {
            if (list.isEmpty()) throw new NoSuchElementException("Stack is empty");
            return list.removeFirst();
        }

        /** Peek top element — O(1) */
        public T peek() {
            if (list.isEmpty()) throw new NoSuchElementException("Stack is empty");
            return list.getFirst();
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }
    }

    // -------------------- GENERIC QUEUE ---------------------
    public static class Queue<T> {
        private LinkedList<T> list = new LinkedList<>();

        /** Enqueue element — O(1) */
        public void enqueue(T value) {
            list.addLast(value);
        }

        /** Dequeue element — O(1) */
        public T dequeue() {
            if (list.isEmpty()) throw new NoSuchElementException("Queue is empty");
            return list.removeFirst();
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }
    }

    // --------------------- TEST CASES -------------------------
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        System.out.println("Stack pop: " + stack.pop());

        Queue<String> q = new Queue<>();
        q.enqueue("A");
        q.enqueue("B");
        System.out.println("Queue dequeue: " + q.dequeue());
    }
}
