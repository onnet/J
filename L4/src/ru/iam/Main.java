package ru.iam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    static String byakaBuka = "Встретились Бяка и Бука.\n" +
            "Никто не издал ни звука.\n" +
            "Никто не подал и знака —\n" +
            "Молчали Бука и Бяка.\n" +
            "И Бука\n" +
            "Думал со скукой:\n" +
            "«Чего он так смотрит — букой?»\n" +
            "А Бяка думал:\n" +
            "«Однако\n" +
            "Какой он ужасный\n" +
            "Бяка…»";

    public static void main(String[] args) {
        findLongestString();
        IsWordPalindrome("Не гни папин ген");
        replaceByaka(byakaBuka);
        findSubstringOccurrences(byakaBuka, "Бяка");
        invertWords("This is my test");


    }

    // 1. Написать метод для поиска самой длинной строки.
    public static void findLongestString() {

        String[] strings = {
                "Displays the elements using the IEnumerator:",
                "Displays the elements using the Count and Item properties",
                "Add one element to the end of the StringCollection and insert another at index 3.",
                "Adds a range of elements from an array to the end of the StringCollection",
                "This code produces the following output",
                };
        String longest = "";
        for (String animals : strings) {
            if (animals.length() > longest.length()) {
                longest = animals;
            }
        }
        System.out.println("First longest string: " + longest);
    }
//2. Написать метод, который проверяет является ли слово палиндромом.

    public static void IsWordPalindrome(String test) {
        System.out.println("------------------------------------");
        String testCleaned = test.replaceAll("[^\\p{L}]", "");
        StringBuilder CleanedSB = new StringBuilder(testCleaned);
        String reverted = CleanedSB.reverse().toString();
        if (reverted.equalsIgnoreCase(testCleaned)) {
            System.out.println("'" + test + "' is palindrome");
        } else {
            System.out.println("'" + test + "' is  NOT a palindrome");
        }

    }

    //3. Напишите метод, заменяющий в тексте все вхождения слова «бяка» на «[вырезано цензурой]».
    public static void replaceByaka(String text) {
        System.out.println("------------------------------------");
        String input = text;
        String myStr = input.replaceAll("(\\w*)Бяка(\\w*)", "«[вырезано цензурой]»");
        System.out.println(myStr);

    }

    //4. Имеются две строки. Найти количество вхождений одной (являющейся подстрокой) в другую.
    public static void findSubstringOccurrences(String testString, String substring) {
        System.out.println("------------------------------------");
        String input = testString;
        int count = 0;
        Pattern pattern = Pattern.compile(substring);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            count++;
        }
        System.out.println("количество вхождений подстроки " + substring + " равно " + count);

    }

    //5. Напишите метод, который инвертирует слова в строке. D строке нет знаков препинания и слова разделены пробелами.
    public static void invertWords(String testText){
        System.out.println("------------------------------------");
        StringBuilder strBuilder = new StringBuilder(testText);
        strBuilder.reverse();

        String[] words = strBuilder.toString().split(" ");
        for(String word : words){
        }
        strBuilder.setLength(0);
        for(int i=words.length-1;i>=0;i--){
            strBuilder.append(words[i]);
            strBuilder.append(" ");
        }
        System.out.println(strBuilder.toString());
    }

}


