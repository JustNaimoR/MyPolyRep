package ru.mail.polis.homework.objects;

// import com.sun.org.apache.xpath.internal.operations.Number;

public class StringTasks {

    /**
     * Убрать все лишние символы из строки и вернуть получившееся число.
     * Разрешенные символы: цифры, '-', '.', 'e'
     * Если '.' и 'e' больше чем 1, возвращаем null
     * Правила на '-' является валидность числа. --3 не валидно. -3e-1 валдино
     * Любой класс-обертка StringTasksTest над примитивами наследуется от Number
     * Если вы используете функции типа Double.valueOf() -- получите только половину тугриков.
     * Для полного количества тугриков надо парсить в ручную.
     * <p>
     * Работайте со строкой, НЕ надо ее переводить в массив байт (это можно использовать только для цикла)
     * У класса Character есть полезные методы, например Character.isDigit()
     */

    //TODO  В функции предпологается вернуть объект класса Number, но описанный класс, который подключен,
    //      я не понял и как вернуть именно этот тип и откуда - не знаю :/
    // Функция реализована чисто для алгоритма.
    public static Number valueOf(String str) {
        String allowedChars = "-.e";

        if (str == null || str.length() == 0) {
            return null;
        }
        // Убираем лишние символы из записи
        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (!Character.isDigit(str.charAt(i)) && !allowedChars.contains(cur + "")) {
                str = str.replaceAll(cur + "", "");
                i--;
            }
        }
        // Две '.' и 'e' в записи числа
        if (str.indexOf('.') != str.lastIndexOf('.') ||
                str.indexOf('e') != str.lastIndexOf('e')) {
            return null;
        }

        /// Исключительные ситуации ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        boolean doublePart = false;     // Часть числа после точки (Для числа boolean типа)
        for (int i = 0; i < str.length(); i++) {

            boolean nextCharIsDigit = (i + 1 != str.length() && Character.isDigit(str.charAt(i + 1)));
            switch (str.charAt(i)) {
                case '-':
                    if (i == 0 && nextCharIsDigit) {
                        // Первый символ - минус
                    } else if (i != 0 && str.charAt(i - 1) == 'e' && nextCharIsDigit) {
                        // минус после 'e' перед цифрой
                    } else {
                        return null;
                    }
                    break;
                case '.':
                    doublePart = true;
                    if (str.indexOf('e') != -1 && str.indexOf('e') < i) {
                        return null;
                    }

                    break;
                case 'e':
                    if (i + 1 == str.length()) {
                        // Последний элемент - экспонента
                        return null;
                    }
                    break;
            }
        }

        if (doublePart || str.contains('e' + "")) {
            return Double.valueOf(str);
        } else {
            return Long.valueOf(str);
        }
    }

    public static void main(String[] args) {
        /**
         *  Алгоритм работающий, вот только блять тесты ска требуют непонять что, а в реализации класса, что передается как
         *   возвращаемое значение, и которое импортировано непонять откуда, вообще нету норм методов для анализа.
         *   Незачем сидеть так долго над этой херней...
         */
        System.out.println(valueOf("fff1fdf.asdsad3e"));
    }
}