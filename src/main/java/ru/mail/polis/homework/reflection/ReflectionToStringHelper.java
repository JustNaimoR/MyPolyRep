package ru.mail.polis.homework.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Необходимо реализовать метод reflectiveToString, который для произвольного объекта
 * возвращает его строковое описание в формате:
 * <p>
 * {field_1: value_1, field_2: value_2, ..., field_n: value_n}
 * <p>
 * где field_i - имя поля
 * value_i - его строковое представление (String.valueOf),
 * за исключением массивов, для которых value формируется как:
 * [element_1, element_2, ..., element_m]
 * где element_i - строковое представление элемента (String.valueOf)
 * элементы должны идти в том же порядке, что и в массиве.
 * <p>
 * Все null'ы следует представлять строкой "null".
 * <p>
 * Порядок полей
 * Сначала следует перечислить в алфавитном порядке поля, объявленные непосредственно в классе объекта,
 * потом в алфавитном порядке поля объявленные в родительском классе и так далее по иерархии наследования.
 * Примеры можно посмотреть в тестах.
 * <p>
   Какие поля выводить
 * Необходимо включать только нестатические поля. Также нужно пропускать поля, помеченные аннотацией @SkipField
 * <p>
 * Упрощения
   Чтобы не усложнять задание, предполагаем, что нет циклических ссылок, inner классов, и transient полей
 * <p>
 * Реализация
 * В пакете ru.mail.polis.homework.reflection можно редактировать только этот файл
 * или добавлять новые (не рекомендуется, т.к. решение вполне умещается тут в несколько методов).
 * Редактировать остальные файлы нельзя.
 * <p>
 * Баллы
 * В задании 3 уровня сложности, для каждого свой набор тестов:
 * Easy - простой класс, нет наследования, массивов, статических полей, аннотации SkipField (4 балла)
 * Easy + Medium - нет наследования, массивов, но есть статические поля и поля с аннотацией SkipField (6 баллов)
 * Easy + Medium + Hard - нужно реализовать все требования задания (10 баллов)
 * <p>
 * Итого, по заданию можно набрать 10 баллов
 * Баллы могут снижаться за неэффективный или неаккуратный код
 */
public class ReflectionToStringHelper {

    public static String reflectiveToString(Object o) {
        if (o == null)
            return "null";
        StringBuilder string = new StringBuilder("{");

        for (Class<?> clazz =  o.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            Arrays.stream(clazz.getDeclaredFields())
                    .filter(x -> !Modifier.isStatic(x.getModifiers()))
                    .filter(x -> Arrays.stream(x.getDeclaredAnnotations()).noneMatch(a -> a.annotationType().equals(SkipField.class)))
                    .sorted(Comparator.comparing(Field::getName))
                    .map(x -> {
                        try {
                            x.setAccessible(true);
                            if (x.getType().isArray()) {
                                // Is an array
                                if (x.get(o) == null)
                                    return x.getName() + ": null";

                                StringBuilder sb = new StringBuilder(x.getName() + ": [");
                                for (int i = 0; i < Array.getLength(x.get(o)); i++) {
                                    sb.append(Array.get(x.get(o), i)).append(", ");
                                }

                                if (Array.getLength(x.get(o)) != 0) {
                                    return sb.delete(sb.length() - 2, sb.length()).append("]").toString();
                                } else {
                                    return sb.append("]").toString();
                                }
                            } else {
                                // Isn't an array
                                return x.getName() + ": " + x.get(o);
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .forEach(str -> string.append(str).append(", "));
        }
        if (string.length() == 1) {
            // пустая строка, лишь { в ней
            return string.append("}").toString();
        } else {
            return string.delete(string.length() - 2, string.length()).append("}").toString();
        }
    }

    public static void main(String[] args) {
        class B {}
        class A extends B {}

        class SomeParent {
            int parentPole = 25;
            boolean[] mass = {true, false};
        }

        class SomeClazz extends SomeParent {
             private final String name = "Hello world!";
             public int digit = 10;
             private int name2 = 20200;
             int[] mass2 = {1, 45, 34};
//             private final A[] mass = {new A(), new A(), new A()};
             A param = null;
        }


        System.out.println(reflectiveToString(new SomeClazz()));
    }

}
