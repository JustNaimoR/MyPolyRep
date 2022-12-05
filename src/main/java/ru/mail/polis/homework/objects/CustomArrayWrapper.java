package ru.mail.polis.homework.objects;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Вам придется реализовать Iterable класс CustomArrayWrapper вместе с методами которые
 * могут возващать итераторы только по четным/нечетным позициям в массиве. Пример с классического
 * итератора можете взять из лекции. Обратите внимание что подсчет четного или нечетного элемента
 * идет с человеческой точки зрения.
 * Пример:
 * дан массив [100, 0 ,100, 0, 100]
 * тогда все элементы со значением 100 имеют нечетную позицию, а элементы = 0 - четную.
 */
public class CustomArrayWrapper implements Iterable<Integer> {

    private final int[] array;          // массив
    private int position;               // следующая позиция куда будет вставлен элемент

    public CustomArrayWrapper(int size) {
        this.array = new int[size];
    }

    public void add(int value) {
        checkIndex(position);
        array[position] = value;
        position++;
    }

    public void edit(int index, int value) {
        checkIndex(index);
        array[index] = value;
    }

    public int get(int index) {
        checkIndex(index);
        return array[index];
    }

    public int size() {
        return array.length;
    }

    /**
     * Реализовать метод:
     * Возврящает обычный итератор.
     *
     * @return default Iterator
     */
    @Override
    public Iterator<Integer> iterator() {

        class DefaultIterator implements Iterator<Integer> {

            private final int[] array;
            private int curPos;

            DefaultIterator(int[] array) {
                this.array = array;
                curPos = 0;
            }

            @Override
            public boolean hasNext() {
                return curPos < array.length;
            }

            @Override
            public Integer next() {
                return array[curPos++];
            }
        }

        return new DefaultIterator(array);
    }

    /**
     * Реализовать метод:
     * Возвращает итератор который проходит только четные элементы.
     *
     * @return Iterator for EVEN elements
     */
    public Iterator<Integer> evenIterator() {
        class EvenIterator implements Iterator<Integer> {

            private final int[] array;
            private final int size;
            private int curPos;

            EvenIterator(int[] array, int size) {
                this.array = array;
                this.size = size;
                curPos = 1;
            }

            @Override
            public boolean hasNext() {
                return curPos < size;
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    Integer i = array[curPos];
                    curPos += 2;
                    return i;
                } else {
                    throw new ConcurrentModificationException();
                }
            }
        }

        return new EvenIterator(array, position);
    }

    /**
     * Реализовать метод:
     * Возвращает итератор который проходит нечетные элементы
     *
     * @return Iterator for ODD elements
     */
    public Iterator<Integer> oddIterator() {
        class OddIterator implements Iterator<Integer> {

            private final int[] array;
            private final int curLength;
            private int curPos;

            OddIterator(int[] array, int size) {
                this.array = array;
                curLength = size;
                curPos = 0;
            }

            @Override
            public boolean hasNext() {
                return curPos < curLength;
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    Integer i = array[curPos];
                    curPos += 2;
                    return i;
                } else {
                    throw new ConcurrentModificationException();
                }
            }
        }

        return new OddIterator(array, position);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException();
        }
    }

}
