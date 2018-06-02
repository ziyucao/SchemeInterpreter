package com.ecnu.analyze;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.ecnu.basic.*;
import com.ecnu.fa.DFA;
import com.ecnu.fa.FA;
import com.ecnu.fa.element.State;
import com.ecnu.fa.element.Transition;
import com.ecnu.fa.runner.DigitDFARunner;

public class LexicalAnalyze {

    static DFA identifierCheckDFA;
    static DFA binaryCheckDFA;
    static DFA octalCheckDFA;
    static DFA decimalCheckDFA;
    static DFA hexCheckDFA;
    static DFA stringCheckDFA;
    DigitDFARunner for_2;

    //dfa for identifier check
    static {
        FA identifierCheckNFA = new FA();

        State s0 = new State(0, false);
        s0.addTransition(new Transition(0, 1, '0'));
        s0.addTransition(new Transition(0, 1, '!'));
        s0.addTransition(new Transition(0, 1, '$'));
        s0.addTransition(new Transition(0, 1, '%'));
        s0.addTransition(new Transition(0, 1, '&'));
        s0.addTransition(new Transition(0, 1, '*'));
        s0.addTransition(new Transition(0, 1, '/'));
        s0.addTransition(new Transition(0, 1, ':'));
        s0.addTransition(new Transition(0, 1, '<'));
        s0.addTransition(new Transition(0, 1, '='));
        s0.addTransition(new Transition(0, 1, '>'));
        s0.addTransition(new Transition(0, 1, '?'));
        s0.addTransition(new Transition(0, 1, '~'));
        s0.addTransition(new Transition(0, 1, '_'));
        s0.addTransition(new Transition(0, 1, '^'));
        identifierCheckNFA.addState(s0);

        State s1 = new State(1, true);
        s1.addTransition(new Transition(1, 1, '0'));
        s1.addTransition(new Transition(1, 1, '!'));
        s1.addTransition(new Transition(1, 1, '$'));
        s1.addTransition(new Transition(1, 1, '%'));
        s1.addTransition(new Transition(1, 1, '&'));
        s1.addTransition(new Transition(1, 1, '*'));
        s1.addTransition(new Transition(1, 1, '/'));
        s1.addTransition(new Transition(1, 1, ':'));
        s1.addTransition(new Transition(1, 1, '<'));
        s1.addTransition(new Transition(1, 1, '='));
        s1.addTransition(new Transition(1, 1, '>'));
        s1.addTransition(new Transition(1, 1, '?'));
        s1.addTransition(new Transition(1, 1, '~'));
        s1.addTransition(new Transition(1, 1, '_'));
        s1.addTransition(new Transition(1, 1, '^'));
        s1.addTransition(new Transition(1, 2, '.'));
        s1.addTransition(new Transition(1, 2, '+'));
        s1.addTransition(new Transition(1, 2, '-'));
        s1.addTransition(new Transition(1, 2, '@'));
        s1.addTransition(new Transition(1, 2, '1'));
        identifierCheckNFA.addState(s1);

        State s2 = new State(2, true);
        s2.addTransition(new Transition(2, 1, '0'));
        s2.addTransition(new Transition(2, 1, '!'));
        s2.addTransition(new Transition(2, 1, '$'));
        s2.addTransition(new Transition(2, 1, '%'));
        s2.addTransition(new Transition(2, 1, '&'));
        s2.addTransition(new Transition(2, 1, '*'));
        s2.addTransition(new Transition(2, 1, '/'));
        s2.addTransition(new Transition(2, 1, ':'));
        s2.addTransition(new Transition(2, 1, '<'));
        s2.addTransition(new Transition(2, 1, '='));
        s2.addTransition(new Transition(2, 1, '>'));
        s2.addTransition(new Transition(2, 1, '?'));
        s2.addTransition(new Transition(2, 1, '~'));
        s2.addTransition(new Transition(2, 1, '_'));
        s2.addTransition(new Transition(2, 1, '^'));
        s2.addTransition(new Transition(2, 2, '.'));
        s2.addTransition(new Transition(2, 2, '+'));
        s2.addTransition(new Transition(2, 2, '-'));
        s2.addTransition(new Transition(2, 2, '@'));
        s2.addTransition(new Transition(2, 2, '1'));
        identifierCheckNFA.addState(s2);

        identifierCheckDFA = new DFA(identifierCheckNFA);
    }

    //dfa for binary number check
    static {
        binaryCheckDFA = constructDigitFA(2);
    }

    //dfa for octal number check
    static {
        octalCheckDFA = constructDigitFA(8);
    }

    //dfa for decimal number check
    static {
        decimalCheckDFA = constructDigitFA(10);
    }

    //dfa for hex number check
    static {
        hexCheckDFA = constructDigitFA(16);
    }

    //dfa for string check
    static {
        FA stringCheckNFA = new FA();

        State s0 = new State(0, false);
        s0.addTransition(new Transition(0, 1, '\\'));
        s0.addTransition(new Transition(0, 4, '6'));
        s0.addTransition(new Transition(0, 5, 'n'));
        stringCheckNFA.addState(s0);

        State s1 = new State(1, false);
        s1.addTransition(new Transition(1, 2, '"'));
        s1.addTransition(new Transition(1, 3, '\\'));
        stringCheckNFA.addState(s1);

        State s2 = new State(2, false);
        s2.addTransition(new Transition(2, 5, 'n'));
        stringCheckNFA.addState(s2);

        State s3 = new State(3, false);
        s3.addTransition(new Transition(3, 5, 'n'));
        stringCheckNFA.addState(s3);

        State s4 = new State(4, false);
        s4.addTransition(new Transition(4, 5, 'n'));
        stringCheckNFA.addState(s4);

        State s5 = new State(5, true);
        s5.addTransition(new Transition(5, 0, 'n'));
        stringCheckNFA.addState(s5);

        stringCheckDFA = new DFA(stringCheckNFA);
    }

    public LexicalAnalyze()
    {
        for_2 = new DigitDFARunner(binaryCheckDFA);
    }

    public SchemeList lexical_analyze(ArrayList<String> input)
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

    {
        /**
         * n -> null transition (only in nfa)
         * 0 -> letters
         * 1 -> digits
         * 2 -> binary
         * 3 -> octal
         * 4 -> decimal
         * 5 -> hex
         * 6 -> any character except '\' and '"'
         */
    }

    /**
     * construct the dfa by the given base(only for 2, 8, 10 or 16)
     * @param base
     * @return dfa
     */
    private static DFA constructDigitFA(int base)
    {
        char c;
        switch (base)
        {
            case 2: c = '2'; break;
            case 8: c = '3'; break;
            case 10: c = '4'; break;
            case 16: c = '5'; break;
            default: return null;
        }
        FA nfa = new FA();

        State s0 = new State(0, false);
        s0.addTransition(new Transition(0, 1, '#'));
        s0.addTransition(new Transition(0, 7, 'n'));
        if (base == 10)
        {
            s0.addTransition(new Transition(0, 2, 'n'));
        }
        nfa.addState(s0);

        State s1 = new State(1, false);
        s1.addTransition(new Transition(1, 2, 'b'));
        nfa.addState(s1);

        State s2 = new State(2, false);
        s2.addTransition(new Transition(2, 3, '#'));
        s2.addTransition(new Transition(2, 6, 'n'));
        nfa.addState(s2);

        State s3 = new State(3, false);
        s3.addTransition(new Transition(3, 4, 'i'));
        s3.addTransition(new Transition(3, 5, 'e'));
        nfa.addState(s3);

        State s4 = new State(4, false);
        s4.addTransition(new Transition(4, 6, 'n'));
        nfa.addState(s4);

        State s5 = new State(5, false);
        s5.addTransition(new Transition(5, 6, 'n'));
        nfa.addState(s5);

        State s6 = new State(6, false);
        s6.addTransition(new Transition(6, 14, 'n'));
        nfa.addState(s6);

        State s7 = new State(7, false);
        s7.addTransition(new Transition(7, 8, '#'));
        s7.addTransition(new Transition(7, 11, 'n'));
        nfa.addState(s7);

        State s8 = new State(8, false);
        s8.addTransition(new Transition(8, 9, 'i'));
        s8.addTransition(new Transition(8, 10, 'e'));
        nfa.addState(s8);

        State s9 = new State(9, false);
        s9.addTransition(new Transition(9, 11, 'n'));
        nfa.addState(s9);

        State s10 = new State(10, false);
        s10.addTransition(new Transition(10, 11, 'n'));
        nfa.addState(s10);

        State s11 = new State(11, false);
        s11.addTransition(new Transition(11, 12, '#'));
        if (base == 10)
        {
            s11.addTransition(new Transition(11, 13, 'n'));
        }
        nfa.addState(s11);

        State s12 = new State(12, false);
        s12.addTransition(new Transition(12, 13, 'b'));
        nfa.addState(s12);

        State s13 = new State(13, false);
        s13.addTransition(new Transition(13, 14, 'n'));
        nfa.addState(s13);

        State s14 = new State(14, false);
        s14.addTransition(new Transition(14, 15, '+'));
        s14.addTransition(new Transition(14, 16, '-'));
        s14.addTransition(new Transition(14, 17, 'n'));
        nfa.addState(s14);

        State s15 = new State(15, false);
        s15.addTransition(new Transition(15, 17, 'n'));
        nfa.addState(s15);

        State s16 = new State(16, false);
        s16.addTransition(new Transition(16, 17, 'n'));
        nfa.addState(s16);

        State s17 = new State(17, false);
        s17.addTransition(new Transition(17, 18, c));
        s17.addTransition(new Transition(17, 22, c));
        if (base == 10)
        {
            s17.addTransition(new Transition(17, 31, 'n'));
        }
        nfa.addState(s17);

        State s18 = new State(18, false);
        s18.addTransition(new Transition(18, 19, c));
        s18.addTransition(new Transition(18, 19, 'n'));
        nfa.addState(s18);

        State s19 = new State(19, false);
        s19.addTransition(new Transition(19, 18, 'n'));
        s19.addTransition(new Transition(19, 20, 'n'));
        nfa.addState(s19);

        State s20 = new State(20, false);
        s20.addTransition(new Transition(20, 21, '#'));
        s20.addTransition(new Transition(20, 21, 'n'));
        nfa.addState(s20);

        State s21 = new State(21, true);
        s21.addTransition(new Transition(21, 20, 'n'));
        nfa.addState(s21);

        State s22 = new State(22, false);
        s22.addTransition(new Transition(22, 23, c));
        s22.addTransition(new Transition(22, 23, 'n'));
        nfa.addState(s22);

        State s23 = new State(23, false);
        s23.addTransition(new Transition(23, 22, 'n'));
        s23.addTransition(new Transition(23, 24, 'n'));
        nfa.addState(s23);

        State s24 = new State(24, false);
        s24.addTransition(new Transition(24, 25, '#'));
        s24.addTransition(new Transition(24, 25, 'n'));
        nfa.addState(s24);

        State s25 = new State(25, false);
        s25.addTransition(new Transition(25, 24, 'n'));
        s25.addTransition(new Transition(25, 26, '/'));
        nfa.addState(s25);

        State s26 = new State(26, false);
        s26.addTransition(new Transition(26, 27, c));
        nfa.addState(s26);

        State s27 = new State(27, false);
        s27.addTransition(new Transition(27, 28, c));
        s27.addTransition(new Transition(27, 28, 'n'));
        nfa.addState(s27);

        State s28 = new State(28, false);
        s28.addTransition(new Transition(28, 27, 'n'));
        s28.addTransition(new Transition(28, 29, 'n'));
        nfa.addState(s28);

        State s29 = new State(29, false);
        s29.addTransition(new Transition(29, 30, '#'));
        s29.addTransition(new Transition(29, 30, 'n'));
        nfa.addState(s29);

        State s30 = new State(30, true);
        s30.addTransition(new Transition(30, 29, 'n'));
        nfa.addState(s30);

        if (base == 10)
        {
            State s31 = new State(31, false);
            s31.addTransition(new Transition(31, 32, c));
            s31.addTransition(new Transition(31, 41, '.'));
            s31.addTransition(new Transition(31, 47, c));
            nfa.addState(s31);

            State s32 = new State(32, false);
            s32.addTransition(new Transition(32, 33, c));
            s32.addTransition(new Transition(32, 33, 'n'));
            nfa.addState(s32);

            State s33 = new State(33, false);
            s33.addTransition(new Transition(33, 32, 'n'));
            s33.addTransition(new Transition(33, 34, '.'));
            s33.addTransition(new Transition(33, 37, '#'));
            nfa.addState(s33);

            State s34 = new State(34, false);
            s34.addTransition(new Transition(34, 35, c));
            s34.addTransition(new Transition(34, 35, 'n'));
            nfa.addState(s34);

            State s35 = new State(35, false);
            s35.addTransition(new Transition(35, 34, 'n'));
            s35.addTransition(new Transition(35, 36, '#'));
            nfa.addState(s35);

            State s36 = new State(36, false);
            s36.addTransition(new Transition(36, 45, 'n'));
            nfa.addState(s36);

            State s37 = new State(37, false);
            s37.addTransition(new Transition(37, 38, '#'));
            s37.addTransition(new Transition(37, 38, 'n'));
            nfa.addState(s37);

            State s38 = new State(38, false);
            s38.addTransition(new Transition(38, 37, 'n'));
            s38.addTransition(new Transition(38, 39, '.'));
            nfa.addState(s38);

            State s39 = new State(39, false);
            s39.addTransition(new Transition(39, 40, '#'));
            s39.addTransition(new Transition(39, 40, 'n'));
            nfa.addState(s39);

            State s40 = new State(40, false);
            s40.addTransition(new Transition(40, 39, 'n'));
            s40.addTransition(new Transition(40, 45, 'n'));
            nfa.addState(s40);

            State s41 = new State(41, false);
            s41.addTransition(new Transition(41, 42, c));
            nfa.addState(s41);

            State s42 = new State(42, false);
            s42.addTransition(new Transition(42, 43, c));
            s42.addTransition(new Transition(42, 43, 'n'));
            nfa.addState(s42);

            State s43 = new State(43, false);
            s43.addTransition(new Transition(43, 42, 'n'));
            s43.addTransition(new Transition(43, 44, 'n'));
            nfa.addState(s43);

            State s44 = new State(44, false);
            s44.addTransition(new Transition(44, 45, '#'));
            s44.addTransition(new Transition(44, 45, 'n'));
            nfa.addState(s44);

            State s45 = new State(45, false);
            s45.addTransition(new Transition(45, 46, 'n'));
            s45.addTransition(new Transition(45, 50, 'n'));
            nfa.addState(s45);

            State s46 = new State(46, true);
            nfa.addState(s46);

            State s47 = new State(47, false);
            s47.addTransition(new Transition(47, 48, c));
            s47.addTransition(new Transition(47, 48, 'n'));
            nfa.addState(s47);

            State s48 = new State(48, false);
            s48.addTransition(new Transition(48, 47, 'n'));
            s48.addTransition(new Transition(48, 49, 'n'));
            nfa.addState(s48);

            State s49 = new State(49, false);
            s49.addTransition(new Transition(49, 50, '#'));
            s49.addTransition(new Transition(49, 50, 'n'));
            nfa.addState(s49);

            State s50 = new State(50, false);
            s50.addTransition(new Transition(50, 49, 'n'));
            s50.addTransition(new Transition(50, 51, 'n'));
            nfa.addState(s50);

            State s51 = new State(51, false);
            s51.addTransition(new Transition(51, 52, 'e'));
            s51.addTransition(new Transition(51, 53, 's'));
            s51.addTransition(new Transition(51, 54, 'f'));
            s51.addTransition(new Transition(51, 55, 'd'));
            s51.addTransition(new Transition(51, 56, 'l'));
            nfa.addState(s51);

            State s52 = new State(52, false);
            s52.addTransition(new Transition(52, 57, 'n'));
            nfa.addState(s52);

            State s53 = new State(53, false);
            s53.addTransition(new Transition(53, 57, 'n'));
            nfa.addState(s53);

            State s54 = new State(54, false);
            s54.addTransition(new Transition(54, 57, 'n'));
            nfa.addState(s54);

            State s55 = new State(55, false);
            s55.addTransition(new Transition(55, 57, 'n'));
            nfa.addState(s55);

            State s56 = new State(56, false);
            s56.addTransition(new Transition(56, 57, 'n'));
            nfa.addState(s56);

            State s57 = new State(57, false);
            s57.addTransition(new Transition(57, 58, '+'));
            s57.addTransition(new Transition(57, 59, '-'));
            s57.addTransition(new Transition(57, 60, 'n'));
            nfa.addState(s57);

            State s58 = new State(58, false);
            s58.addTransition(new Transition(58, 60, 'n'));
            nfa.addState(s58);

            State s59 = new State(59, false);
            s59.addTransition(new Transition(59, 60, 'n'));
            nfa.addState(s59);

            State s60 = new State(60, false);
            s60.addTransition(new Transition(60, 61, c));
            nfa.addState(s60);

            State s61 = new State(61, false);
            s61.addTransition(new Transition(61, 62, c));
            s61.addTransition(new Transition(61, 62, 'n'));
            nfa.addState(s61);

            State s62 = new State(62, true);
            s62.addTransition(new Transition(62, 61, 'n'));
            nfa.addState(s62);
        }

        return new DFA(nfa);
    }
}
