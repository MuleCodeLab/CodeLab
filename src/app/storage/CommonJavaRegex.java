package app.storage;

import com.syed.core.Regex;

import java.util.List;
import java.util.ArrayList;

public class CommonJavaRegex {

    public static List<Regex> asList() {
        int size = Math.min(comments.length, regex.length);
        List<Regex> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new Regex(regex[i], comments[i]));
        }
        return  list;
    }

    private static final String[] comments = {
            "created an if statement",
            "created an if statement",
            "created an else if statement",
            "created an else if statement",
            "created a for loop",
            "created a for loop",
            "created a while loop",
            "created a while loop",
            "created a while loop",
            "created a do while loop",
            "used the keyword switch",
            "created cases for your switch statement",
            "imported the java util library",
            "created a scanner",
            "used .next",
            "used .nextInt",
            "used .nextDouble",
            "used .nextLine",
            "used .nextFloat",
            "used .hasNextInt",
            "used .hasNextDouble",
            "used .hasNextLine",
            "used .hasNextFloat",
            "used Integer.parseInt",
            "used Double.parseDouble",
            "used Float.parseFloat",
            "used Long.parseLong",
            "used Integer.toString",
            "used Double.toString",
            "used Float.toString",
            "used Long.toString",
            "implemented a print statement",
            "used the length method",
            "used the String charAt method",
            "used the String substring method",
            "used the toUpperCase method",
            "used the toLowerCase method",
            "used the equals method",
            "used the split method",
            "uses the matches method",
            "used the addition operator",
            "used the sum operator",
            "used the subtraction operator",
            "used the subtraction operator",
            "used the multiplication operator",
            "used the product operator",
            "used the division operator",
            "used the divisor operator",
            "used the modulus operator",
            "used the modulator operator",
            "used the decrement operator",
            "used the increment operator"
    };

    private static final String[] regex = {
            "if\\s*\\(.*\\(\\=\\=\\|\\=\\=\\=\\|\\<\\|\\<\\=\\|\\>\\|\\>\\=\\|\\!\\=\\).*\\)",
            "if\\s*\\(\\s*[_a-zA-Z0-9$]*\\s*\\)",
            "else\\s*if\\s*\\(\\s*.*\\(\\=\\|\\=\\=\\|\\=\\=\\=\\|\\<\\|\\<\\=\\|\\>\\|\\>\\=\\|\\!\\=\\)\\s*.*\\)",
            "else\\s*if\\s*\\(\\s*[_a-zA-Z0-9$]*\\s*\\)",
            "for\\s*\\([^;]*\\s*;[^;]*\\s*;[^)]*\\s*\\)",
            "for\\s*(\\s*String.*:.*)",
            "for\\s*\\([^;]*\\s*;[^;]*\\s*;[^)]*\\s*\\)\\|while\\s*\\(\\s*[_a-zA-Z0-9$]*\\s*\\(\\=\\|\\=\\=\\|\\=\\=\\=\\|\\<\\|\\<\\=\\|\\>\\|\\>\\=\\|\\!\\=\\)\\s*[_a-zA-Z0-9$]*\\s*\\)",
            "while\\s*\\(\\s*[_a-zA-Z0-9$]*\\s*\\(\\=\\|\\=\\=\\|\\=\\=\\=\\|\\<\\|\\<\\=\\|\\>\\|\\>\\=\\|\\!\\=\\)\\s*[_a-zA-Z0-9$]*\\s*\\)",
            "while\\s*\\(\\s*[_a-zA-Z0-9$]*\\s*\\)",
            "do\\s*{\\(\\s*.*\\)*}\\s*while\\s*(.*\\(\\=\\|\\=\\=\\|\\=\\=\\=\\|\\<\\|\\<\\=\\|\\>\\|\\>\\=\\|\\!=\\)\\.*)\\s*\\;",
            "switch\\s*\\(\\s*.*\\)\\s*",
            "\\s*case.*:",
            "import\\s\\+java\\.util\\.[^;]*\\s*;",
            "System\\.in",
            "next()",
            "nextInt()",
            "nextDouble()",
            "nextLine()",
            "nextFloat()",
            "hasNextInt()",
            "hasNextDouble()",
            "hasNextLine()",
            "hasNextFloat()",
            "Integer\\.parseInt(.*)",
            "Double\\.parseDouble(.*)",
            "Float\\.parseFloat(.*)",
            "Long\\.parseLong(.*)",
            "Integer\\.toString(.*)",
            "Double\\.toString(.*)",
            "Float\\.toString(.*)",
            "Long\\.toString(.*)",
            "System\\.out\\.print\\(ln\\)\\{0,1\\}\\s*(.*);",
            "\\.length() OR \\.length",
            "\\.charAt(.*)",
            "\\.substring(.*)",
            "\\.toUpperCase()",
            "\\.toLowerCase()",
            ".*\\.equals\\s*\\(\\s*.*\\)",
            ".*\\.split\\s*\\(\\s*.*\\)",
            ".matches\\s\\+*(.\\+)",
            ".*+.*",
            ".*\\+\\=.*",
            ".*-.*",
            ".*\\-\\=.*",
            ".*\\*.*",
            ".*\\*\\=.*",
            ".*\\/.*",
            ".*\\/\\=.*",
            ".*\\%.*",
            ".*\\%\\=.*",
            ".*\\-\\-.*",
            ".*\\+\\+.*"
    };
}
