package ru.mail.polis.homework.simple;

import java.util.function.ToDoubleFunction;

public class HomeworkTask {

    /**
     * Нужно численно посчитать интеграл от a до b с шагом delta от функции function
     * Для того, что бы получить значение по Y для точки X, надо просто написать function.applyAsDouble(t)
     * Считаем, что функция определена на всем пространстве от a до b
     */
    public static double calcIntegral(double a, double b, ToDoubleFunction<Double> function, double delta) {
        double value = 0;

        for (double i = a; i < b; i += delta) {
            value += delta * function.applyAsDouble(i);
        }

        return value;
    }

    /**
     * Вывести номер максимальной цифры. Счет начинается слева направо,
     * выводим номер первой максимальной цифры (если их несколько)
     */
    public static byte maxNumber(long a) {
        /*   Math.abs(Long.MIN_VALUE) == Long.MIN_VALUE
        *      (*) Отрицательное значение будет равно себе же в модуле (*)
        */

        String stringA = Long.toString(a);
        byte step = 0;
        char max = '0' - 1;
        byte maxNum = 0;

        for (char c: stringA.toCharArray()) {
            if (c == '-') {
                continue;
            }
            step++;

            if (max < c) {
                maxNum = step;
                max = c;
            }
        }

        return maxNum;
    }

    /**
     * Даны две точки в пространстве (x1, y1) и (x2, y2). Вам нужно найти Y координату третьей точки (x3, y3),
     * которая находится на той же прямой что и первые две.
     */
    public static double lineFunction(int x1, int y1, int x2, int y2, int x3) {
        // Направляющий вектор - a(x1 - x2, y1 - y2)
        // Каноническое уравнение прямой: (x - x1) / Ax = (y - y1) / Ay
        return (double) (y1 - y2) / (x1 - x2) * (x3 - x1) + y1;
    }

    /**
     * Даны 4 точки в пространстве A(x1, y1), B(x2, y2), C(x3, y3), D(x4, y4). Найдите площадь выпуклого
     * четырехуголька ABCD.
     * Это дополнительное задание, необязательное для выполнения
     */
    public static double square(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        double AB = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        double BC = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
        double CD = Math.sqrt(Math.pow(x3 - x4, 2) + Math.pow(y3 - y4, 2));
        double AD = Math.sqrt(Math.pow(x4 - x1, 2) + Math.pow(y4 - y1, 2));

        double p = (AB + BC + CD + AD) / 2;

        return Math.sqrt((p - AB) * (p - BC) * (p - CD) * (p - AD));
    }

}