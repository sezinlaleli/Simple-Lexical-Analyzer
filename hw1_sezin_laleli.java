// Importing required classes

import java.util.*;
import java.io.*;

public class hw1_sezin_laleli {

    //to use at lexicalNamer
    public static String int_lit = "";
    public static String identifier = "";
    public static String operator_error = "";
    public static String identifier_error = "";

    //to use it while writing file
    public static String output = "";

    public static void main(String[] args) {

        //get input and output file names from command line arguments
        String input = args[0];
        output = args[1];

        //overwrite file
        // create a new writer
        PrintWriter pw = null;
        try {
            //if file not exist then create
            pw = new PrintWriter(new FileOutputStream(output));
            pw.print("");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        pw.close();

        try {
            FileInputStream fis = new FileInputStream(input);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                lexicalParter(line);
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //method for lexical analyzing
    public static void lexicalParter(String line) throws IOException {
        int len = line.length();
        char[] s = line.toCharArray();
        int not_error = 0;

        int_lit = "";
        identifier = "";
        for (int i = 0; i < len; i++) {
            not_error = 0;
            //identifier
            if (isLetter(s[i])) {
                identifier = "";
                if ((i > 0) && (i < (len - 1))) {
                    if (!isLetter(s[i - 1]) && !isNumber(s[i - 1])) {
                        if (!isLetter(s[i + 1]) && !isNumber(s[i + 1])) {
                            identifier = Character.toString(s[i]);
                            lexicalNamer("identifier");
                            not_error = 1;
                        }
                    }
                } else if ((i == 0) && (i < (len - 1))) {
                    if (!isLetter(s[i + 1]) && !isNumber(s[i + 1])) {
                        identifier = Character.toString(s[i]);
                        lexicalNamer("identifier");
                        not_error = 1;
                    }
                } else if ((i > 0) && (i == len - 1)) {
                    if (!isLetter(s[i - 1]) && !isNumber(s[i - 1])) {
                        identifier = Character.toString(s[i]);
                        lexicalNamer("identifier");
                        not_error = 1;
                    }
                } else if (len == 1) {
                    identifier = Character.toString(s[i]);
                    lexicalNamer("identifier");
                    not_error = 1;
                }
                //for
                if (s[i] == 'f') {
                    if (i + 2 < len) {
                        if (s[i + 1] == 'o' && s[i + 2] == 'r') {
                            lexicalNamer("for");
                            i += 2;
                            not_error = 1;
                        }
                    }
                }
                //char
                else if (s[i] == 'c') {
                    if (i + 3 < len) {
                        if (s[i + 1] == 'h' && s[i + 2] == 'a' && s[i + 3] == 'r') {
                            lexicalNamer("char");
                            i += 3;
                            not_error = 1;
                        }
                    }
                }
                //int
                else if (s[i] == 'i') {
                    if (i + 2 < len) {
                        if (s[i + 1] == 'n' && s[i + 2] == 't') {
                            lexicalNamer("int");
                            i += 2;
                            not_error = 1;
                        }
                    }
                }
                //return
                else if (s[i] == 'r') {
                    if (i + 5 < len) {
                        if (s[i + 1] == 'e' && s[i + 2] == 't' && s[i + 3] == 'u' && s[i + 4] == 'r' && s[i + 5] == 'n') {
                            lexicalNamer("return");
                            i += 5;
                            not_error = 1;
                        }
                    }
                }
                if (not_error == 0) {
                    identifier_error = identifier_error + s[i];
                    if (i < len - 1) {
                        for (int j = i + 1; j < len; j++) {
                            if (isLetter(s[j]) || isNumber(s[j])) {
                                identifier_error = identifier_error + s[j];
                            } else {
                                break;
                            }
                        }
                    }
                    lexicalNamer("unknown identifier");
                    i += identifier_error.length() - 1;
                    identifier_error = "";
                    System.exit(0);
                }
            }

            //unknown operator
            if (!operatorCheck(s[i])) {
                operator_error = Character.toString(s[i]);
                lexicalNamer("unknown operator");
                operator_error = "";
                System.exit(0);
            }
            //left bracket
            else if (s[i] == '(') {
                lexicalNamer("(");
            }
            //right bracket
            else if (s[i] == ')') {
                lexicalNamer(")");
            }
            //assignment
            else if (s[i] == '=') {
                if (i - 1 >= 0) {
                    if (!((s[i - 1] == '<' || s[i - 1] == '>'))) {
                        lexicalNamer("=");
                    }
                } else {
                    lexicalNamer("=");
                }

            }
            //semicolon
            else if (s[i] == ';') {
                lexicalNamer(";");
            }
            //greater
            else if (s[i] == '>') {
                //less or equals
                if ((i + 1 < len)) {
                    if (s[i + 1] == '=') {
                        lexicalNamer(">=");
                        i += 1;
                    } else {
                        lexicalNamer(">");
                    }
                } else {
                    lexicalNamer(">");
                }
            }
            //less
            else if (s[i] == '<') {
                //less or equals
                if ((i + 1 < len)) {
                    if (s[i + 1] == '=') {
                        lexicalNamer("<=");
                        i += 1;
                    } else {
                        lexicalNamer("<");

                    }
                } else {
                    lexicalNamer("<");
                }
            }
            //left curly bracket
            else if (s[i] == '{') {
                lexicalNamer("{");
            }
            //left curly bracket
            else if (s[i] == '}') {
                lexicalNamer("}");
            }

            //subtraction
            else if (s[i] == '-') {
                lexicalNamer("-");
            }
            //division
            else if (s[i] == '/') {
                lexicalNamer("/");
            }
            //multiply
            else if (s[i] == '*') {
                lexicalNamer("*");
            }
            //addition
            else if (s[i] == '+') {
                lexicalNamer("+");
            }
            //integer constant
            else if (isNumber(s[i])) {
                int_lit = int_lit + s[i];
                if (i < len - 1) {
                    for (int j = i + 1; j < len; j++) {
                        if (isNumber(s[j])) {
                            int_lit = int_lit + s[j];
                        } else {
                            break;
                        }
                    }
                }
                lexicalNamer("integer constant");
                i += int_lit.length() - 1;
                int_lit = "";
            }
        }

    }

    public static void lexicalNamer(String key) throws IOException {

        // Creating an empty HashMap
        Map<String, String> map = new HashMap<String, String>();

        String s = "";

        // Inserting entries in the Map
        // using put() method
        map.put("for", "FOR_STATEMENT");
        map.put("(", "LPARANT");
        map.put(")", "RPARANT");
        map.put("int", "INT_TYPE");
        map.put("char", "CHAR_TYPE");
        map.put("=", "ASSIGNM");
        map.put(";", "SEMICOLON");
        map.put(">", "GREATER");
        map.put("<", "LESS");
        map.put(">=", "GRE_EQ");
        map.put("<=", "LESS_EQ");
        map.put("{", "LCURLYB");
        map.put("}", "RCURLYB");
        map.put("return", "RETURN_STMT");
        map.put("-", "SUBT");
        map.put("/", "DIV");
        map.put("*", "MULT");
        map.put("+", "ADD");
        map.put("identifier", "An identifier consists of a single letter");
        map.put("integer constant", "INT_LIT");
        map.put("unknown operator", "error");
        map.put("unknown identifier", "error");

        String value = map.get(key);

        // create a new writer
        PrintWriter pw = null;
        try {
            //if file not exist then create and write end of file
            pw = new PrintWriter(new FileOutputStream(output, true));

            if (key.equals("identifier")) {
                pw.printf("%-37s %s %n", "Next token is identifier", "Next lexeme is " + identifier);
            } else if (key.equals("integer constant")) {
                pw.printf("%-37s %s %n", "Next token is INT_LIT", "Next lexeme is " + int_lit);
            } else if (key.equals("unknown operator")) {
                pw.println("UNKNOWN OPERATOR: " + operator_error);
            } else if (key.equals("unknown identifier")) {
                pw.println("UNKNOWN IDENTIFIER: " + identifier_error);
            } else {
                pw.printf("%-37s %s %n", "Next token is " + value, "Next lexeme is " + key);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        pw.close();


    }

    public static boolean isNumber(char ch) {
        int c = Character.getNumericValue(ch);
        for (int i = 0; i <= 9; i++) {
            if (i == c) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLetter(char ch) {
        char c;

        for (c = 'A'; c <= 'Z'; ++c) {
            if (c == ch) {
                return true;
            }
        }
        for (c = 'a'; c <= 'z'; ++c) {
            if (c == ch) {
                return true;
            }
        }
        return false;
    }

    public static boolean operatorCheck(char ch) {
        char[] operators = {'/', '*', '-', '+', ';', '=', '<', '>', '(', ')', '{', '}'};
        if (isLetter(ch) || isNumber(ch) || (ch == ' ')) {
            return true;
        }
        for (int i = 0; i < operators.length; i++) {
            if (ch == operators[i]) {
                return true;
            }
        }

        return false;
    }

}