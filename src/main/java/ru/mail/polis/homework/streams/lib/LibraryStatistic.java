package ru.mail.polis.homework.streams.lib;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс для работы со статистикой по библиотеке.
 * Оценка 5-ть баллов
 */
public class LibraryStatistic {

    /**
     * Вернуть "специалистов" в литературном жанре с кол-вом прочитанных страниц.
     * Специалист жанра считается пользователь который прочел как минимум 5 книг в этом жанре,
     * при этом читал каждую из них не менее 14 дней.
     * @param library - данные библиотеки
     * @param genre - жанр
     * @return - map пользователь / кол-во прочитанных страниц
     */
    public Map<User, Integer> specialistInGenre(Library library, Genre genre) {
        /**
         * Stream<ArchivedData>> - Стрим, содержащий в себе объекты класса ArchivedData. Аналогия со стримом из List<Integer> - стрим чисел из листа
         *
         * Function <...> matchedBooks - функция получения лямбды-выражение (поскольку лямбда работает лишь один раз, требуется получать ее вновь из функции)
         *                                  для сохранения стрима-получения книг, подходящих под требования
         *                                  (совпадает жанр, прочитано больше чем 14 дней + добавление после под определенного пользователя)
         * @return filter(user -> matchedBooks.apply(user).count() >= 5) - Получение количество книг, которые подходят под требования (с помощью
         *                                  сохраненного стрима) + под определенного пользователя (user)
         */
        Function<User, Stream<ArchivedData>> matchedBooks = (user) -> library.getArchive().stream()
                                                                   .filter(a -> a.getBook().getGenre().equals(genre) &&
                                                                                a.getUser().equals(user) &&
                                                                                TimeUnit.MILLISECONDS.toDays(a.getReturned().getTime() - a.getTake().getTime()) >= 14);
        return library.getUsers().stream().filter(user -> matchedBooks.apply(user).count() >= 5)
                        .collect(Collectors.toMap(user -> user, user -> matchedBooks.apply(user)
                        .mapToInt(x -> x.getBook().getPage()).sum() + (user.getBook().getGenre() == genre? user.getReadedPages(): 0)));
    }

    /**
     * Вернуть любимый жанр пользователя. Тот что чаще всего встречается. Не учитывать тот что пользователь читает в данный момент.
     * Если есть несколько одинаковых по весам жанров - брать в расчет то, что пользователь читает в данный момент.
     * @param library - данные библиотеки
     * @param user - пользователь
     * @return - жанр
     */
    public Genre loveGenre(Library library, User user) {
        // Мапа с количеством книг каждого жанра, которые прочитал пользователь
        Map<Genre, Integer> map = new HashMap<>();

        library.getArchive().stream()
                .filter(archivedData -> archivedData.getUser().equals(user))
                .peek(archivedData -> {
                    Book curBook = archivedData.getBook();
                    if (map.containsKey(curBook.getGenre())) {
                        map.put(curBook.getGenre(), map.get(curBook.getGenre()) + 1);
                    } else {
                        map.put(curBook.getGenre(), 1);
                    }
                }).close();
        return map.entrySet().stream().max((x, y) -> {
                    if (x.getValue() > y.getValue()) {
                        return 1;
                    } else if (x.getValue() < y.getValue()) {
                        return -1;
                    } else {
                        Genre curGenre = user.getBook().getGenre();
                        if (x.getKey() == curGenre) 
                            return 1;
                        else if (y.getKey() == curGenre) 
                            return -1;
                        else 
                            return 0;
                    }
                }).get().getKey();
    }

    /*
    public static void main(String[] args) {
        // Мои тестики 
        
        Book book1 = new Book("Java", "Kristian Stuart", Genre.TECHNICAL, 148);
        Book book2 = new Book("Why not?", "Bred Pit", Genre.NOVEL, 93);
        Book book3 = new Book("Russia", "Vladimir Putin", Genre.HISTORY, 983);
        Book book4 = new Book("Piton", "Someone", Genre.TECHNICAL, 673);
        Book book5 = new Book("Fantasy story", "Another someone", Genre.FANTASY, 34);

        // Readed pages - количество прочитанных страниц в данной книге
        User user1 = new User("John", 23, book1, 48);
        User user2 = new User("Roman", 19, book2, 13);
        User user3 = new User("Bill", 36, book3, 101);
        
        Library library = new Library(List.of(user1, user2, user3),
                                        List.of(book1, book2, book3, book4, book5),
                                        List.of(new ArchivedData(user1, book4, new Timestamp(2002, 11, 23, 14, 33, 11, 0),
                                                new Timestamp(2004, 11, 23, 14, 33, 11, 0))));
    }
    */
    
    /**
     * Вернуть список пользователей которые больше половины книг держали на руках более 30-ти дней. Брать в расчет и книги которые сейчас
     * пользователи держат у себя (ArchivedData.returned == null)
     * @param library - данные библиотеки
     * @return - список ненадежных пользователей
     */
    public List<User> unreliableUsers(Library library) {
        return null;
    }

    /**
     * Вернуть список книг у которых страниц равно или больше чем переданное значение
     * @param library - данные библиотеки
     * @param countPage - кол-во страниц
     * @return - список книг
     */
    public List<Book> booksWithMoreCountPages(Library library, int countPage) {
        return null;
    }

    /**
     * Вернуть самого популярного автора в каждом жанре. Если кол-во весов у авторов одинаково брать по алфавиту.
     * @param library - данные библиотеки
     * @return - map жанр / самый популярный автор
     */
    public Map<Genre, String> mostPopularAuthorInGenre(Library library) {
        return null;
    }
}
