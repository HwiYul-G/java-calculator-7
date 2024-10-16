package calculator.service;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Separator {
    private static final String BASIC_SEPARATOR_COMMA = ",";
    private static final String BASIC_SEPARATOR_COLON = ":";
    private static final Pattern CUSTOM_SEPARATOR_PATTERN = Pattern.compile("//(.+)\\\\n");

    public int[] basicSeparate(String content) {
        return Arrays.stream(
                content.replaceAll(BASIC_SEPARATOR_COLON, BASIC_SEPARATOR_COMMA).split(BASIC_SEPARATOR_COMMA)
        ).mapToInt(this::convertToNumber).toArray();
    }

    public int[] customSeparate(String content) {
        Matcher matcher = CUSTOM_SEPARATOR_PATTERN.matcher(content);
        if (matcher.find()) {
            String customSeparator = matcher.group(1);
            return Arrays.stream(content.substring(matcher.end()).split(customSeparator))
                    .mapToInt(this::convertToNumber).toArray();
        }
        throw new IllegalArgumentException("[ERROR] Separator: 타당하지 않은 형식의 커스텀 구분자");
    }

    private int convertToNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] Separator: 에러 메시지 추천");
        }
    }

}
