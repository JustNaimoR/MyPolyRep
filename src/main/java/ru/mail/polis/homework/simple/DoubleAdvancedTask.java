package ru.mail.polis.homework.simple;

import java.sql.Array;
import java.util.Arrays;

/**
 * Возможно вам понадобится класс Math с его методами. Например, чтобы вычислить квадратный корень, достаточно написать
 * Math.sqrt(1.44)
 * Чтобы увидеть все методы класса Math, достаточно написать Math. и среда вам сама покажет возможные методы.
 * Для просмотра подробной документации по выбранному методу нажмите Ctrl + q
 */
public class DoubleAdvancedTask {

    /**
     * Вывести три корня кубического уравнения через запятую: a * x ^ 3 + b * x ^ 2 + c * x + d = 0;
     * Вывод менять не нужно, надо только посчитать x1, x2 и x3, где x1 >= x2 >= x3
     * Считаем, что все три корня вещественные.
     * <p>
     * Если используете какой-то конкретный способ, напишите какой.
     * Пример: (1, -4, -7, 10) -> "-2.0, 1.0, 5.0"
     */
    public static String equation(int d, int a, int b, int c) {
        // Формула Виетта
        double[] result;
        if (d != 1) {
            a = a / d;
            b = b / d;
            c = c / d;
        }

        double p = b / 3. - a * a / 9.;
        double q = a * a * a / 27. - a * b / 6. + c / 2.;
        double D = p * p * p + q * q;

        if (Double.compare(D, 0) >= 0) {
            double r;
            if (Double.compare(D, 0) == 0) {
                r = Math.cbrt(-q);
                result = new double[3];
                result[0] = 2 * r;
                result[1] = -r;

            } else {
                r = Math.cbrt(-q + Math.sqrt(D));
                double s = Math.cbrt(-q - Math.sqrt(D));
                result = new double[3];
                result[0] = r + s;

                result[1] = 0.0;
            }
            result[2] = 0.0;
        } else {
            double ang = Math.acos(-q / Math.sqrt(-p * p * p));
            double r = 2 * Math.sqrt(-p);
            result = new double[3];
            for (int k = -1; k <= 1; k++) {
                double theta = (ang - 2 * Math.PI * k) / 3;
                result[k + 1] = r * Math.cos(theta);
            }

        }
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i] - a / 3.;
        }

        Arrays.sort(result);

        return result[2] + ", " + result[1] + ", " + result[0];

        /*
        // (Для x^3 + Ax^2 + Bx + C = 0)
        double A = 0,
               B = 0,
               C = 0;
        if (a != 0) {
            A = (double) b / a;
            B = (double) c / a;
            C = (double) d / a;
        } else {
            // ??? //
        }

        double[] res;
        double P = - A * A / 3 + B;
        double Q = 2 * Math.pow(A / 3, 3) - A * B / 3 + C;
        double delta = Math.pow(P / 3, 3) + Math.pow(Q / 2, 3);

        if (delta <= 0) {
            double phi = Math.acos(-Q / 2 * Math.pow(3 / -P, 3/2.));
            double y1 = 2 * Math.sqrt(-P / 3) * Math.cos(phi / 3);
            double y2 = 2 * Math.sqrt(-P / 3) * Math.cos(phi / 3 + 2 * Math.PI / 3);
            double y3 = 2 * Math.sqrt(-P / 3) * Math.cos(phi / 3 - 2 * Math.PI / 3);

            res = new double[] { (y1 - A / 3), (y2 - A / 3), (y3 - A / 3) };
            Arrays.sort(res);

            return res[2] + ", " + res[1] + ", " + res[0];
        } else {
            return 0 + ", " + 0 + ", " + 0;
        }
        */
    }

    /**
     * Нужно посчитать расстояние, между двумя прямыми
     * Примеры: (1, 1, 2, -1) -> 0
     * (0, 1, 0, 5) -> 4
     */
    public static float length(double a1, double b1, double a2, double b2) {
        // Уравнение прямой: y = a * x + b (или ax - y + b = 0)
        if (Math.abs(a1 - a2) < 0.00001 && Math.abs(b1 - b2) > 0.00001) {
            // Прямые параллельны -> можно вычислить расстояние между
            double x = 0;
            double y = b1;

            return (float) (Math.abs(x * a2 - y * 1 + b2) / Math.sqrt(a2 * a2 + 1));
        } else {
            // Прямые не параллельны или совпадают -> 0
            return 0;
        }
    }

    /**
     * Даны три точки в пространстве (x1, y1, z1) , (x2, y2, z2) и (x3, y3, z3). Вам нужно найти Z координату
     * четвертой точки (x4, y4, z4), которая находится на той же плоскости что и первые три.
     * (0, 0, 1,
     * 1, 1, 1,
     * 10, 100, 1,
     * 235, -5) -> 1
     */
    public static double surfaceFunction(int x1, int y1, int z1,
                                         int x2, int y2, int z2,
                                         int x3, int y3, int z3,
                                         int x4, int y4) {
        // Алгебраические дополнения
        int A11 = (y2 - y1) * (z3 - z1) - (y3 - y1) * (z2 - z1);
        int A12 = -((x2 - x1) * (z3 - z1) - (x3 - x1) * (z2 - z1));
        int A13 = (x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1);

        return -((x4 - x1) * A11 + (y4 - y1) * A12) / (double) A13 + z1;
    }
}
