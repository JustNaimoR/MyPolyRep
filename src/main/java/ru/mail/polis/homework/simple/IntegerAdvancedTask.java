package ru.mail.polis.homework.simple;


import javax.swing.plaf.metal.MetalTheme;

/**
 * Возможно вам понадобится класс Math с его методами. Например, чтобы вычислить квадратный корень, достаточно написать
 * Math.sqrt(1.44)
 * Чтобы увидеть все методы класса Math, достаточно написать Math. и среда вам сама покажет возможные методы.
 * Для просмотра подробной документации по выбранному методу нажмите Ctrl + q
 */
public class IntegerAdvancedTask {

    private static final double EPS = 1e-10;

    /**
     * Сумма первых n-членов геометрической прогрессии с первым элементом a и множителем r
     * a + aq + aq^2 + ... + aq^(n-1)
     * <p>
     * Пример: (1, 2, 3) -> 7
     */
    public static long progression(int a, double q, int n) {
        if (n == 1) {
            return a;
        }

        return a * (long) Math.pow(q, n - 1) + progression(a, q, n - 1);
    }

    /**
     * Гусеница ползает по столу квадратами по часовой стрелке. За день она двигается следующим образом:
     * сначала наверх на up, потом направо на right. Ночью она двигается вниз на down и налево на left.
     * Сколько суток понадобится гусенице, чтобы доползти до поля с травой?
     * Считаем, что на каждой клетке с координатами >= grassX или >= grassY находится трава
     * Если она этого никогда не сможет сделать, Верните число Integer.MAX_VALUE;
     * Пример: (10, 3, 5, 5, 20, 11) -> 2
     */
    public static int snake(int up, int right, int down, int left, int grassX, int grassY) {
        int curX = 0,
            curY = 0;
        int day = 0;

        if (up <= down && right <= left && up < grassY && right < grassX) {
            // Когда шаг вправо и вверх менее шага вниз и влево соответственно
            return Integer.MAX_VALUE;
        }

        while (curX < grassX && curY < grassY) {
            day++;
            curY += up;
            curX += right;

            if (!(curX < grassX && curY < grassY)) {
                return day;
            }

            curY -= down;
            curX -= left;
        }

        return day;
    }

    /**
     * Дано число n в 10-ном формате и номер разряда order.
     * Выведите цифру стоящую на нужном разряде для числа n в 16-ом формате
     * Нельзя пользоваться String-ами
     * Пример: (454355, 2) -> D
     */
    public static char kDecimal(int n, int order) {
        while (order-- > 1) {
            n = n / 16;
        }

        switch (n % 16) {
            case 10:
                return 'A';
            case 11:
                return 'B';
            case 12:
                return 'C';
            case 13:
                return 'D';
            case 14:
                return 'E';
            case 15:
                return 'F';
            default:
                return (char) ('0' + n % 16);
        }
    }

    /**
     * Дано число в 10-ном формате.
     * Нужно вывести номер минимальной цифры для числа в 16-ном формате. Счет начинается справа налево,
     * выводим номер первой минимальной цифры (если их несколько)
     * Нельзя пользоваться String-ами
     * (6726455) -> 2
     */
    public static byte minNumber(long a) {
        int min = 17;
        byte minNum = 0;
        int step = 1;

        while (a > 0) {
            if (a % 16 < min) {
                min = (int) (a % 16);
                minNum = (byte) step;
            }

            step++;
            a = a / 16;
        }

        return minNum;
    }

}