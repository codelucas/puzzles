package JavaInterview;

import java.util.Arrays;

class Stack<E> {
    private E[] arr;
    private int fill;
    
    @SuppressWarnings("unchecked")
    public Stack(int size) {
        arr = (E[]) new Object[size];
        fill = 0;
    }
    
    public void push(E e) {
        if (fill >= arr.length) {
            arr = Arrays.copyOf(arr, arr.length*2);
        }
        arr[fill++] = e;
    }
    
    public E pop() {
        if (fill <= 0) {
            return null;
        }
        E e = arr[fill-1];
        fill--;
        return e;
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (E e : arr) {
            sb.append(e);
            sb.append(" ");
        }
        return sb.toString();
    }
    
    public boolean isEmpty() {
        return (fill == 0);
    }
}

class Queue<E> {
    private Stack<E> stack1;
    private Stack<E> stack2;
    
    public Queue(int size) {
        stack1 = new Stack<E>(size);
        stack2 = new Stack<E>(size);
    }
    
    private void shift() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }
    
    public void push(E e) {
        stack1.push(e);
    }
    
    public E pop() {
        if (stack2.isEmpty()) {
            shift();
        }
        return stack2.pop();
    }
}
public class QueueWithStacks {
    /*
     * How can you implement a queue with
     * only stacks?
     * 
     * Maintain two stacks. Whenever something
     * is pushed, push it into stack 1. When something
     * is popped, pop and push all of stack 1 onto stack
     * 2. Then pop once from stack 2.
     */
    
    public static void main(String[] args) {
        /*
        Stack<Integer> s = new Stack<Integer>(10);
        s.pop();
        for (int i=1; i<2; i++) {
            s.push(i);
        }
        System.out.println(s.pop());
        System.out.println(s.toString());
        */
        Queue<Integer> q = new Queue<Integer>(10);
        q.push(10);
        q.push(200);
        System.out.println(q.pop());
    }
}


