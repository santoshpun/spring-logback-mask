package com.santosh.logging;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

@Slf4j
public class RegexTester {

    public static void main(String[] args) {
        RegexTester tester = new RegexTester();

        //tester.test1();

        //tester.test2();

        //tester.test3();

        //tester.test4();

        //tester.test5();

        tester.test6();

//        UserDetail userDetail = new UserDetail();
//        userDetail.setSsn("3310104322");
//        userDetail.setName("Santosh Pun");
//        log.info("user detail : " + userDetail);
    }

    private void test1() {
        String text =
                "This is the text which is to be searched " +
                        "for occurrences of the word 'is'.";

        String patternString = "is";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        int count = 0;
        while (matcher.find()) {
            count++;
            log.info("found: " + count + " : "
                    + matcher.start() + " - " + matcher.end());
        }
    }

    private void test2() {
        String text =
                "John writes about this, and John writes about that," +
                        " and John writes about everything. ";

        String patternString1 = "(John)";

        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            log.info("found: " + matcher.group(1));
        }
    }

    private void test3() {
        String text = "{ \"user_id\" : \"1234\", \"ssn\" : \"3310104322\",\"ssn\" : \"3310104322\"}";

        /*
            " (\\
            ssn
            space
            colon (:)
            space (\\s, first slash is used as a literal)
            . (one or more character)
            * (Occurs zero or more times, is short for {0,})
            ? (Occurs no or one times, ? is short for {0,1}.,
               X? finds no or exactly one letter X)
         */
        String patternString1 = "\\\"ssn\\\"\\s*:\\s*\\\"(.*?)\\\"";

        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            log.info("found: " + matcher.group(1));
        }
    }

    private void test4() {
        String text = "{ \"user_id\" : \"1234\", \"ssn\" : \"3310104322\",\"ssn\" : \"3310104322\"}";

        /*
            " (\\
            ssn
            space
            colon (:)
            space (\\s, first slash is used as a literal)
            . (one or more character)
            * (Occurs zero or more times, is short for {0,})
            ? (Occurs no or one times, ? is short for {0,1}.,
               X? finds no or exactly one letter X)
         */
        String patternString1 = "\\\"ssn\\\"\\s*:\\s*\\\"(.*?)\\\"";

        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(text);

        StringBuilder sb = new StringBuilder(text);
        while (matcher.find()) {
            IntStream.rangeClosed(1, matcher.groupCount()).forEach(group -> {
                System.out.println("start : " + matcher.start(group) + ", end : " + matcher.end(group));
                if (matcher.group(group) != null) {
                    IntStream.range(matcher.start(group), matcher.end(group))
                            .forEach(i -> {
                                sb.setCharAt(i, '*');
                            }); // replace each character with asterisk
                }
            });
        }
        System.out.println("str: " + sb.toString());
    }

    //use case 1
    private void test5() {
        String text = "UserDetail(ssn=3310104322, name=Santosh Pun)";

        String patternString1 = "ssn=([a-zA-Z0-9]+)";

        Pattern pattern = Pattern.compile(patternString1, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        StringBuilder sb = new StringBuilder(text);
        while (matcher.find()) {
            IntStream.rangeClosed(1, matcher.groupCount()).forEach(group -> {
                if (matcher.group(group) != null) {
                    System.out.println("start : " + matcher.start(group) + ", end : " + matcher.end(group));
                    IntStream.range(matcher.start(group), matcher.end(group))
                            .forEach(i -> {
                                sb.setCharAt(i, '*');
                            }); // replace each character with asterisk
                }
            });
        }
        System.out.println(sb.toString());
    }

    //use case 2
    private void test6() {
        String text = "{ \"user_id\" : \"1234\", \"ssn\": \"3310104322\",\"ssn\" : \"3310104322\"}";

        String patternString1 = "\\\"ssn\\\"\\s*:\\s*\\\"(.*?)\\\"";

        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(text);

        StringBuilder sb = new StringBuilder(text);
        while (matcher.find()) {
            IntStream.rangeClosed(1, matcher.groupCount()).forEach(group -> {
                System.out.println("start : " + matcher.start(group) + ", end : " + matcher.end(group));
                if (matcher.group(group) != null) {
                    IntStream.range(matcher.start(group), matcher.end(group))
                            .forEach(i -> {
                                sb.setCharAt(i, '*');
                            }); // replace each character with asterisk
                }
            });
        }
        System.out.println("str: " + sb.toString());
    }

    private void test7() {
        String text =
                "User clientId=23421. Some more text clientId=33432. This clientNum=100";

        Pattern p = Pattern.compile("(clientId=)(\\d+)");
        Matcher m = p.matcher(text);

        StringBuffer result = new StringBuffer();
        while (m.find()) {
            System.out.println("Masking: " + m.group(2));
            m.appendReplacement(result, m.group(1) + "******");
        }
        m.appendTail(result);
        System.out.println(result);
    }
}
