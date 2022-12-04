package ru.mail.polis.homework.objects;

import java.util.Arrays;
import java.util.Objects;

/**
 * Нужно найти символ, который встречается подряд в строке чаще всего, и указать количество повторений.
 * Если более одного символа с максимальным значением, то нужно вернуть тот символ,
 * который первый встречается в строчке
 * Если строка пустая или null, то вернуть null
 * Пример abbasbdlbdbfklsssbb -> (s, 3)
 */
public class RepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(getMaxRepeatingCharacters("babababab").getFirst());
    }

    public static Pair<Character, Integer> getMaxRepeatingCharacters(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        int curN = 0;
        char curC = str.charAt(0);
        Integer maxN = 0;
        Character maxC = null;

        for (int i = 0; i < str.length(); i++) {
            curN += i + 1 == str.length() && curN != 1? 1: 0;

            if (curC != str.charAt(i) || i + 1 == str.length()) {

                if (curN > maxN) {
                    maxN = curN;
                    maxC = curC;
                }

                curN = 1;
                curC = str.charAt(i);
            } else {
                curN++;
            }
        }

        return new Pair<>(maxC, maxN);
    }

    public static class Pair<T, V> {
        private final T first;
        private final V second;

        public Pair(T first, V second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

    }
}
