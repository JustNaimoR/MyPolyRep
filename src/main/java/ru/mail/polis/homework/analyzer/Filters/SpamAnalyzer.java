package ru.mail.polis.homework.analyzer.Filters;

import ru.mail.polis.homework.analyzer.FilterType;
import ru.mail.polis.homework.analyzer.TextAnalyzer;

public class SpamAnalyzer implements TextAnalyzer {
    private final String[] spam;
    private final int PRIORITY = 0;

    public SpamAnalyzer(String[] spam) {
        this.spam = spam;
    }

    @Override
    public FilterType analyze(String str) {
        if (str == null) {
            return FilterType.GOOD;
        }

        for (String cur: spam) {
            if (str.contains(cur)) {
                return FilterType.SPAM;
            }
        }
        return FilterType.GOOD;
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }
}
