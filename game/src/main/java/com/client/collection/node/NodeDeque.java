package com.client.collection.node;

import net.runelite.rs.api.RSNode;
import net.runelite.rs.api.RSNodeDeque;

import java.util.Iterator;

public class NodeDeque implements RSNodeDeque {

    public Node sentinel = new Node();

    Node current;

    public NodeDeque() {
        this.sentinel.previous = this.sentinel;
        this.sentinel.next = this.sentinel;
    }

    public boolean method2028() {
        return this.sentinel.previous == this.sentinel;
    }

    public void insertBack(Node node) {
        addFirst(node);
    }

    public Node pop() {
        return removeLast();
    }


    public void clear() {
        while (true) {
            Node var1 = this.sentinel.previous;
            if (var1 == this.sentinel) {
                this.current = null;
                return;
            }

            var1.remove();
        }
    }

    public void addFirstt(Node var1) {
        if (var1.next != null) {
            var1.remove();
        }

        var1.next = this.sentinel.next;
        var1.previous = this.sentinel;
        var1.next.previous = var1;
        var1.previous.next = var1;
    }

    public void addLast(Node var1) {
        if (var1.next != null) {
            var1.remove();
        }

        var1.next = this.sentinel;
        var1.previous = this.sentinel.previous;
        var1.next.previous = var1;
        var1.previous.next = var1;
    }

    public Node removeLast() {
        Node var1 = this.sentinel.previous;
        if (var1 == this.sentinel) {
            return null;
        } else {
            var1.remove();
            return var1;
        }
    }

    public Node removeFirst() {
        Node var1 = this.sentinel.next;
        if (var1 == this.sentinel) {
            return null;
        } else {
            var1.remove();
            return var1;
        }
    }

    @Override
    public RSNode getCurrent() {
        return current;
    }

    @Override
    public RSNode getSentinel() {
        return sentinel;
    }

    public Node last() {
        Node var1 = this.sentinel.previous;
        if (var1 == this.sentinel) {
            this.current = null;
            return null;
        } else {
            this.current = var1.previous;
            return var1;
        }
    }

    public Node first() {
        Node var1 = this.sentinel.next;
        if (var1 == this.sentinel) {
            this.current = null;
            return null;
        } else {
            this.current = var1.next;
            return var1;
        }
    }

    public Node previous() {
        Node var1 = this.current;
        if (var1 == this.sentinel) {
            this.current = null;
            return null;
        } else {
            this.current = var1.previous;
            return var1;
        }
    }

    @Override
    public void addFirst(RSNode val) {
        addFirstt((Node) val);
    }

    public Node next() {
        Node var1 = this.current;
        if (var1 == this.sentinel) {
            this.current = null;
            return null;
        } else {
            this.current = var1.next;
            return var1;
        }
    }

    public static void NodeDeque_addBefore(Node var0, Node var1) {
        if (var0.next != null) {
            var0.remove();
        }

        var0.next = var1.next;
        var0.previous = var1;
        var0.next.previous = var0;
        var0.previous.next = var0;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    public void insertHead(Node node) {
        addFirst(node);
    }

    public void insertTail(Node node) {
        addLast(node);
    }

    public Node popHead() {
        return removeLast();
    }

    public Node reverseGetFirst() {
        return last();
    }

    public Node reverseGetNext() {
        return previous();
    }

    public Node getFirst() {
        return first();
    }

    public Node getLast() {
        return last();
    }

    public Node getNext() {
        return next();
    }


}
