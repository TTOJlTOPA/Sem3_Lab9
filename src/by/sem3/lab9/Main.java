package by.sem3.lab9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("resources/input.txt"));
            FileWriter writer = new FileWriter("out/output.txt");
            StringBuffer line = new StringBuffer();
            while (reader.ready()) {
                line.append(reader.readLine());
                deleteSymbolsInBrackets(line);
                deleteUnnecessaryDigits(line);
                deleteInsignificantZeros(line);
                System.out.println(line);
                writer.write(line.toString() + "\n");
                line.delete(0, line.length());
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void deleteSymbolsInBrackets(StringBuffer str) {
        Pattern pattern = Pattern.compile("[(][^)]*[)]");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find(0)) {
            str.delete(matcher.start(), matcher.end());
        }
    }

    private static void deleteUnnecessaryDigits(StringBuffer str) {
        Pattern pattern = Pattern.compile("[0-9]{3,}");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find(0)) {
            str.delete(matcher.start() + 2, matcher.end());
        }
    }

    private static void deleteInsignificantZeros(StringBuffer str) {
        Pattern pattern = Pattern.compile("(^|[^1-9])[0]+[0-9]");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find(0)) {
            if (matcher.start() != 0) {
                str.delete(matcher.start() + 1, matcher.end() - 1);
            } else {
                str.delete(matcher.start(), matcher.end() - 1);
            }
        }
    }
}
