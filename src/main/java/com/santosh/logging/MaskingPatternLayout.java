package com.santosh.logging;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
https://www.schibsted.pl/blog/logback-pattern-gdpr/
https://howtodoinjava.com/spring-boot2/logging/multiple-log-files/
*/
@Slf4j
@Component
public class MaskingPatternLayout extends PatternLayout {

    private Pattern multilinePattern;
    private List<String> maskPatterns = new ArrayList<>();

    public void addMaskPattern(String maskPattern) {
        maskPatterns.add(maskPattern);
        multilinePattern = Pattern.compile(
                maskPatterns.stream()
                        .collect(Collectors.joining("|")), // build pattern using logical OR
                Pattern.MULTILINE
        );
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        return maskMessage(super.doLayout(event)); // calling superclass method is required
    }

    private String maskMessage(String message) {
        if (multilinePattern == null) {
            return message;
        }

        StringBuilder sb = new StringBuilder(message);
        Matcher matcher = multilinePattern.matcher(sb);
        while (matcher.find()) {
            IntStream.rangeClosed(1, matcher.groupCount()).forEach(group -> {
                if (matcher.group(group) != null) {
                    IntStream.range(matcher.start(group), matcher.end(group))
                            .forEach(i -> sb.setCharAt(i, '*')); // replace each character with asterisk
                }
            });
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String message = "{ \"user_id\" : \"1234\", \"ssn\" : \"3310104322\", \"favourite_team\" : \"Juventus\", \"address\" : \"Wiejska 4, Warszawa\", \"additional_info_1\" : \"192.168.1.1\", \"additional_info_2\" : \"bianconeri36@gmail.com\" }";

        List<String> maskPatterns = new ArrayList<>();
        Pattern multilinePattern;
        maskPatterns.add("\\\"ssn\\\"\\s*:\\s*\\\"(.*?)\\\"");

        multilinePattern = Pattern.compile(
                maskPatterns.stream()
                        .collect(Collectors.joining("|")), // build pattern using logical OR
                Pattern.MULTILINE
        );

        StringBuilder sb = new StringBuilder(message);
        Matcher matcher = multilinePattern.matcher(sb);
        while (matcher.find()) {
            IntStream.rangeClosed(1, matcher.groupCount()).forEach(group -> {
                if (matcher.group(group) != null) {
                    IntStream.range(matcher.start(group), matcher.end(group))
                            .forEach(i -> sb.setCharAt(i, '*')); // replace each character with asterisk
                }
            });
        }

        System.out.println(sb.toString());
    }
}
