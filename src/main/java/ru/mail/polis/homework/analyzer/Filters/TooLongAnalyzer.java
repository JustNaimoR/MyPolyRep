package ru.mail.polis.homework.analyzer.Filters;

import ru.mail.polis.homework.analyzer.FilterType;
import ru.mail.polis.homework.analyzer.TextAnalyzer;

public class TooLongAnalyzer implements TextAnalyzer {
    private final long maxLength;
    private final int PRIORITY = 1;

    public TooLongAnalyzer(long maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public FilterType analyze(String str) {
        if (str == null) {
            return FilterType.GOOD;
        }

        return str.length() <= maxLength? FilterType.GOOD: FilterType.TOO_LONG;
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

}
