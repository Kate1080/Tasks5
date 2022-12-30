import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class tasks5 {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        System.out.println(Arrays.toString(encrypt("Hello")));
        System.out.println(Arrays.toString(encrypt("Sunshine")));
        int[] array1 = new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68};
        System.out.println(decrypt(array1));

        System.out.println("Задание 2");
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));

        System.out.println("Задание 3");
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println(canComplete("tulb", "beautiful"));
        System.out.println(canComplete("bbutl", "beautiful"));

        System.out.println("Задание 4");
        System.out.println(sumDigProd(16, 28));
        System.out.println(sumDigProd(0));
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6));

        System.out.println("Задание 5");
        String[] mas1 = new String[]{"toe", "ocelot", "maniac"};
        System.out.println(Arrays.toString(sameVowelGroup(mas1)));
        String[] mas2 = new String[]{"many", "carriage", "emit", "apricot", "animal"};
        System.out.println(Arrays.toString(sameVowelGroup(mas2)));
        String[] mas3 = new String[]{"hoops", "chuff", "bot", "bottom"};
        System.out.println(Arrays.toString(sameVowelGroup(mas3)));

        System.out.println("Задание 6");
        System.out.println(validateCard(1234567890123456L));
        System.out.println(validateCard(1234567890123452L));

        System.out.println("Задание 7");
        System.out.println(numToEng(0));
        System.out.println(numToEng(18));
        System.out.println(numToEng(126));
        System.out.println(numToEng(909));

        System.out.println("Задание 8");
        System.out.println(getSha256Hash("password123"));
        System.out.println(getSha256Hash("Fluffy@home"));
        System.out.println(getSha256Hash("Hey dude!"));

        System.out.println("Задание 9");
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));

        System.out.println("Задание 10");
        System.out.println("Задание 10");
        System.out.println(hexLattice(1));
        System.out.println(hexLattice(7));
        System.out.println(hexLattice(19));
        System.out.println(hexLattice(21));
    }


    public static int[] encrypt(String s) {
        int[] result = new int[s.length()];
        int ascii = (int) s.charAt(0);
        result[0] = ascii;
        for (int i = 1; i < s.length(); i++) {
            int ascii_other = (int) s.charAt(i);
            int dif = Math.abs(ascii - ascii_other);
            result[i] = dif;
            ascii = ascii_other;
        }

        return result;

    }

    public static String decrypt(int[] a) {
        StringBuilder result = new StringBuilder();
        int ascii = 0; // значение ascii прошлого символа
        for (int i = 0; i < a.length; i++) {
            result.append((char) (ascii + a[i]));
            ascii = result.charAt(i);
        }
        return result.toString();
    }


    //Создайте функцию, которая принимает имя шахматной фигуры, ее положение и
//целевую позицию. Функция должна возвращать true, если фигура может двигаться
//к цели, и false, если она не может этого сделать.
    public static boolean canMove(String figure, String start, String finish) {
        if ("King".equals(figure)) {
            return (Math.abs((int) start.charAt(0) - (int) finish.charAt((0))) <= 1) && (Math.abs((int) start.charAt(1) - (int) finish.charAt((1))) <= 1);
        } else if ("Queen".equals(figure)) {
            return (start.charAt(0) == finish.charAt((0))) || (start.charAt(1) == finish.charAt((1))) ||
                    (Math.abs((int) start.charAt(0) - (int) finish.charAt((0))) == Math.abs((int) start.charAt(1) - (int) finish.charAt((1))));
        } else if ("Bishop".equals(figure)) {
            /* слон */
            return (Math.abs((int) start.charAt(0) - (int) finish.charAt((0))) == Math.abs((int) start.charAt(1) - (int) finish.charAt((1))));
        } else if ("Knight".equals(figure)) {
            /* конь(на 1 шаг по буквам и 2 по цифрам или 2 шага по буквам и 1 по цифрам) */
            return (Math.abs((int) start.charAt(0) - (int) finish.charAt((0))) == 1) && (Math.abs((int) start.charAt(1) - (int) finish.charAt((1))) == 2) || (Math.abs((int) start.charAt(0) - (int) finish.charAt((0))) == 2) && (Math.abs((int) start.charAt(1) - (int) finish.charAt((1))) == 1);
        } else if ("Rook".equals(figure)) {
            /*ладья*/
            return (start.charAt(0) == finish.charAt((0))) || (start.charAt(1) == finish.charAt((1)));
        } else if ("Pawn".equals(figure)) {
            /*пешка*/
            return ((start.charAt(0) == finish.charAt(0)) && (finish.charAt(1) - start.charAt(1) == 1)) || (start.charAt(1) == 7 && finish.charAt(1) == 5) || (start.charAt(1) == 2 && finish.charAt(1) == 4);
        }
        return false;
    }


    //    Входная строка может быть завершена, если можно добавить дополнительные
//    буквы, и никакие буквы не должны быть удалены, чтобы соответствовать слову.
//    Кроме того, порядок букв во входной строке должен быть таким же, как и порядок
//    букв в последнем слове.
    public static boolean canComplete(String start, String result) {
        int letter = 0;
        for (int i = 0; i < result.length(); i++) {
            if (start.charAt(letter) == result.charAt(i)) {
                letter++;
            }
        }
        return letter == start.length();
    }


    //    Создайте функцию, которая принимает числа в качестве аргументов, складывает их
//    вместе и возвращает произведение цифр до тех пор, пока ответ не станет длиной
//    всего в 1 цифру.
    public static int sumDigProd(int... x) {
        int result = 0;
        int p = 1;
        for (int i : x) {
            result += i;
        }
        while (result >= 10) {
            while (result != 0) {
                p *= result % 10;
                result /= 10;
            }
            result = p;
        }
        return result;
    }

    public static Set<Character> Vovels(String s) {
        String svovels = s.replaceAll("[bcdfghjklmnpqrstvwxyz]", "");
        Set<Character> vovels = new HashSet<>();
        for (char i : svovels.toCharArray()) {
            vovels.add(i);
        }
        return vovels;
    }


    //    Напишите функцию, которая выбирает все слова, имеющие все те же гласные (в
//любом порядке и / или количестве), что и первое слово, включая первое слово.
    public static String[] sameVowelGroup(String[] words) {
        Set<Character> firstVovels = Vovels(words[0]);

        ArrayList<String> result = new ArrayList<>();
        result.add(words[0]);

        for (int i = 1; i < words.length; i++) {
            Set<Character> buf = Vovels(words[i]);
            if (buf.equals(firstVovels)) {
                result.add(words[i]);
            }
        }
        return result.toArray(new String[]{});
    }


//Создайте функцию, которая принимает число в качестве аргумента и возвращает
//true, если это число является действительным номером кредитной карты, а в
//противном случае-false

    public static boolean validateCard(long s) {

        int pos = 0;
        int sum = 0;

        int len = String.valueOf(s).length();
        if (len < 14 || len > 16) {
            return false;
        }

        int check_digit = (int) s % 10;
        s = s / 10;

        StringBuilder revS = new StringBuilder(String.valueOf(s)).reverse();

        for (int i = 0; i < revS.length(); i++) {
            pos = revS.charAt(i);
            if ((i + 1) % 2 != 0) {
                pos *= 2;
                if (pos >= 10) {
                    pos = pos % 10 + (pos / 10) % 10;
                }
            }
            sum += pos;
        }

        int to_compare = 10 - (sum % 10);
        return to_compare == check_digit;
    }

//   Напишите функцию, которая принимает положительное целое число от 0 до 999
//включительно и возвращает строковое представление этого целого числа,
//написанное на английском языке.
    public static String numToEng(int x) {
        String[] up_to_20 = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

        if (x == 0) {
            return "zero";
        }

        if (x < 20) {
            return up_to_20[x];
        } else if (x < 100) {
            if (x % 10 == 0) {
                return tens[x / 10];
            }
            return tens[x / 10] + " " + up_to_20[x % 10];
        } else {
            String space1 = " ";
            String space2 = " ";
            // если у нас десаток нет, то пробел не ставим между сотнями и единицами
            if ((x / 10) % 10 == 0) {
                space2 = "";
                if (x % 10 == 0) {
                    space1 = "";
                }
            }
            return up_to_20[x / 100] + " hundred" + space1 + tens[(x / 10) % 10] + space2 + up_to_20[x % 10];
        }
    }

//    Создайте функцию, которая возвращает безопасный хеш SHA-256 для данной строки.
//Хеш должен быть отформатирован в виде шестнадцатеричной цифры.
    public static String getSha256Hash(String str) {
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException e){
            System.out.println("error");
        }
        return hexString.toString();
    }


//    Напишите функцию, которая принимает строку и возвращает строку с правильным
//регистром для заголовков символов в серии "Игра престолов".

    public static StringBuilder correctTitle(String s) {
        String[] words = s.split(" ");
        String attention = "and the of in";
        StringBuilder result = new StringBuilder();
        String word;
        for (int i = 0; i < words.length; i++) {
            word = words[i];
//            if (word.contains("-")){
//                String[] omg = word.split("-");
//                result.append(String.valueOf(omg[0].charAt(0)).toUpperCase() + omg[0].substring(1).toLowerCase() + "-" + String.valueOf(omg[1].charAt(0)).toUpperCase() + omg[1].substring(1).toLowerCase());
//            }
            if (!attention.contains(word)) {
                result.append(String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1).toLowerCase());
            } else {
                result.append(word.toLowerCase());
            }
            if (i != words.length - 1) {
                result.append(" ");
            }
        }
        return result;
    }


//     Гексагональная решетка - это привычная двумерная решетка, в которой каждая точка
//имеет 6 соседей.
    static StringBuilder hexLattice(int ch){
        double n = (3 + Math.sqrt(12 * ch - 3)) / 6;
        if (n % 1 != 0) {
            return new StringBuilder("Invalid");
        }
        StringBuilder result = new StringBuilder("");
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n - i; j++) {
                result.append(" ");
            }
            for (int k = 1; k < n + i; k++) {
                result.append("o ");
            }
            result.append("\n");
        }
        for (int i = (int) (n - 1); i >= 1; i--){
            for (int j = 1; j <= n - i; j++) {
                result.append(" ");
            }
            for (int k = 1; k < n + i; k++) {
                result.append("o ");
            }
            result.append("\n");
        }
        return result;
    }

}












