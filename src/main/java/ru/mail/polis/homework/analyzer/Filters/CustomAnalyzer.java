package ru.mail.polis.homework.analyzer.Filters;

import ru.mail.polis.homework.analyzer.FilterType;
import ru.mail.polis.homework.analyzer.TextAnalyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomAnalyzer implements TextAnalyzer {
    /**
     *  Ограничивает количество чисел, используемых в строке
     */
    private static final int maxNumbers = 10;
    private final int PRIORITY = 3;

    @Override
    public FilterType analyze(String str) {
        if (str == null) {
            return FilterType.GOOD;
        }

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(str);

        int count = 0;
        while (matcher.find() && count <= maxNumbers) {
            count++;
        }

        if (count == maxNumbers) {
            return FilterType.CUSTOM;
        } else {
            return FilterType.GOOD;
        }
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }
}
