package ru.mail.polis.homework.analyzer.Filters;

import ru.mail.polis.homework.analyzer.FilterType;
import ru.mail.polis.homework.analyzer.TextAnalyzer;

public class NegativeTextAnalyzer implements TextAnalyzer {
    private static final String[] smiles = {"=(", ":(", ":|"};
    private final int PRIORITY = 2;

    @Override
    public FilterType analyze(String str) {
        if (str == null) {
            return FilterType.GOOD;
        }

        for (String cur: smiles) {
            if (str.contains(cur)) {
                return FilterType.NEGATIVE_TEXT;
            }
        }
        return FilterType.GOOD;
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }
}
