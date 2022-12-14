package ru.mail.polis.homework.objects;

import java.util.Arrays;

public class MaxTask {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getMaxArray(new int[] {}, 0)));
    }

    /**
     * Вам дан массив и количество элементов в возвращаемом массиве
     * Вернуть нужно массив из count максимальных элементов array, упорядоченный по убыванию.
     * Если массив null или его длина меньше count, то вернуть null
     * Например ({1, 3, 10, 11, 22, 0}, 2) -> {22, 11}
     * ({1, 3, 22, 11, 22, 0}, 3) -> {22, 22, 11}
     * НЕЛЬЗЯ СОРТИРОВАТЬ массив array и его копии
     *
     */
    public static int[] getMaxArray(int[] array, int count) {
        if (array == null || array.length < count) {
            return null;
        }

        int[] result = new int[count];

        for (int i = 0; i < count; i++) {
            result[i] = deleteMax(array);
        }

        return result;
    }

    private static int deleteMax(int[] array) {
        int maxS = 0;
        int max = array[maxS];

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                maxS = i;
            }
        }

        array[maxS] = Integer.MIN_VALUE;    // Удаление из массива макс элемента
        return max;
    }

}