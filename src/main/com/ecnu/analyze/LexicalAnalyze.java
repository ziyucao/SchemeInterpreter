package com.ecnu.analyze;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.ecnu.basic.*;

public class LexicalAnalyze {

    public static SchemeList lexical_analyze(ArrayList<String> input)
    {
        List<SchemeToken> list = new LinkedList<>();
        for (String s : input)
        {
            if (s.equals("("))
            {
                list.add(new SchemeParenthesis('(', SchemeParenthesis.LEFT_PARENTHESIS));
            }
            else if (s.equals(")"))
            {
                list.add(new SchemeParenthesis(')', SchemeParenthesis.LEFT_PARENTHESIS));
            }
            else if (s.equals("'") || s.equals("`") || s.equals(",") || s.equals(",@"))
            {
                list.add(new SchemeQuoted(','));
            }
            else if (s.startsWith("\"") && s.endsWith("\""))
            {
                String content = s.subSequence(1, s.length() - 2).toString();
                /**
                 * content jin xing zheng ze pi pei, ke yong fang fa :isStringCharacter()
                 */
            }
            else if (s.startsWith("#"))
            {
                if (s.equals("#t") || s.equals("#T")) {
                    SchemeBoolean sb = new SchemeBoolean(true);
                    list.add(sb);
                }
                else if (s.equals("#f") || s.equals("#F"))
                {
                    SchemeBoolean sb = new SchemeBoolean(false);
                    list.add(sb);
                }
                else if (s.startsWith("#\\"))
                {
                    if (s.equals("#\\space"))
                    {
                        list.add(new SchemeString("#\\space"));
                    }
                    else if (s.equals("#\\newline"))
                    {
                        list.add(new SchemeString("#\\newline"));
                    }
                    else if (s.length() == 3)
                    {
                        list.add(new SchemeString(s));
                    }
                }
                else
                {
                    /**
                     * pan duan shi fou wei 2 jin zhi, 8 jin zhi, 10 jin zhi huo 16 jin zhi
                     * ke yong fang fa:
                     * isBinaryNumber()   isOctalNumber()   isHexNumber()
                     */

                }
            }
            else if (s.equals("+"))
            {
                list.add(new SchemeIdentifier("+"));
            }
            else if (s.equals("-"))
            {
                list.add(new SchemeIdentifier("-"));
            }
            else if (s.equals("..."))
            {
                list.add(new SchemeIdentifier("..."));
            }
            else if (s.startsWith("+") || s.startsWith("-") || s.startsWith("."))
            {
                /**
                 * pan duan shi fou wei 10 jin zhi
                 * ke yong fang fa:
                 * isDecimalNumber()
                 */
            }
            else
            {
                /**
                 * pan duan shi fou wei biao zhi fu
                 * ke yong fang fa:
                 * isLetter()
                 */
            }
        }

        return new SchemeList(list);
    }

    private static boolean isBinaryNumber(char c)
    {
        return (c == '0' || c== '1');
    }

    private static boolean isOctalNumber(char c)
    {
        return (c >= '0' && c <= '7');
    }

    private static boolean isDecimalNumber(char c)
    {
        return (c >= '0' && c <= '9');
    }

    private static boolean isHexNumber(char c)
    {
        return  (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }

    private static boolean isLetter(char c)
    {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private static boolean isStringCharacter(char c)
    {
        return !(c == '\\' || c == '"');
    }
}
