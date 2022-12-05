package ru.mail.polis.homework.objects;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 15 тугриков
 * Реализовать все методы односвязного списка.
 */
public class CustomLinkedList implements Iterable<Integer> {
    private Node head = null;

    /**
     * 1 тугрик
     * Возвращает количество элементов в списке
     *
     * @return size
     */
    public int size() {
        int k = 0;
        Node cur = head;

        while (cur != null) {
            k++;
            cur = cur.next;
        }

        return k;
    }

    /**
     * 2 тугрика
     * Реализовать метод:
     * Добавляет элемент в односвязный список.
     *
     * @param value - data for create Node.
     */
    public void add(int value) {
        add(size(), value);
    }

    /**
     * 2 тугрика
     * Метод должен вернуть число на соответствующем индексе.
     *
     * @param index
     */
    public int get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || size() <= index) {
            throw new IndexOutOfBoundsException(index);
        }

        Node cur = head;

        while (index != 0 && cur.next != null) {
            cur = cur.next;
            index--;
        }

        return cur.value;
    }

    /**
     * 2 тугрика
     * Реализовать метод:
     * Добавляет элемент в односвязный список на заданную позицию.
     * Если был передан невалидный index - надо выкинуть исключение IndexOutOfBoundsException.
     * throw new IndexOutOfBoundsException(i);
     *
     * @param i - index
     * @param value - data for create Node.
     */
    public void add(int i, int value) throws IndexOutOfBoundsException {
        if (i < 0 || size() < i) {
            throw new IndexOutOfBoundsException(i);
        }

        Node cur = head;
        Node node = new Node(value);

        if (i == 0) {
            node.next = head;
            head = node;
        } else {
            while (i > 1) {
                cur = cur.next;
                i--;
            }
            node.setNext(cur.next);
            cur.setNext(node);
        }
    }

    /**
     * 2 тугрика
     * Реализовать метод:
     * Удаляет элемент в указанной позиции, при это связывая его соседние элементы друг с другом.
     * Если был передан невалидный index - надо выкинуть исключение IndexOutOfBoundsException.
     * throw new IndexOutOfBoundsException(i);
     *
     * @param index - position what element need remove.
     */
    public void removeElement(int index) throws IndexOutOfBoundsException {
        if (index < 0 || size() <= index) {
            throw new IndexOutOfBoundsException(index);
        }

        Node cur = head;

        while (index > 1) {
            cur = cur.next;
            index--;
        }

        if (index == 0) {
            head = head.next;
        } else {
            cur.next = cur.next.next;
        }
    }

    /**
     * 2 тугрика
     * Реализовать метод:
     * Переворачивает все элементы списка.
     * Пример:
     *  Исходная последовательность списка "1 -> 2 -> 3 -> 4 -> null"
     *  После исполнения метода последовательность должна быть такой "4 -> 3 -> 2 -> 1 -> null"
     */
    public void revertList() {
        if (head == null) {
            return;
        }
        revertList(head, size() - 1);
    }

    private void revertList(Node start, int shift) {
        if (start.next != null) {
            revertList(start.next, shift - 1);
        }

        removeElement(size() - 1);
        add(shift, start.value);
    }

    /**
     * 1 тугрик
     * Метод выводит всю последовательность хранящуюся в списке начиная с head.
     * Формат вывода:
     *  - значение каждой Node должно разделяться " -> "
     *  - последовательность всегда заканчивается на null
     *  - если в списке нет элементов - верните строку "null"
     *
     * @return - String with description all list
     */
    @Override
    public String toString() {
        if (head == null) {
            return "null";
        }

        Node cur = head;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            str.append(cur.value).append(" -> ");
            cur = cur.next;
        }
        str.append("null");

        return str.toString();
    }

    /**
     * 3 тугрика
     * Возвращает итератор, который умеет только итерироваться. БЕЗ удаления!
     *
     * @return iterator
     */
    @Override
    public Iterator<Integer> iterator() {
        class MyListIterator implements Iterator<Integer> {
            Node cur;

            MyListIterator(Node head) {
                cur = head;
            }

            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    Integer i = cur.value;
                    cur = cur.next;
                    return i;
                } else {
                    throw new NoSuchElementException();
                }
            }
        }

        return new MyListIterator(head);
    }

    private static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
