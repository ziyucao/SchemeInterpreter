package com.ecnu;

import com.ecnu.basic.SchemeList;
import com.ecnu.process.LexicalAnalyze;
import com.ecnu.process.PreProcessor;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        LexicalAnalyze lexicalAnalyze = new LexicalAnalyze();
        SchemeList list = lexicalAnalyze.lexical_analyze(PreProcessor.preProcess(input));
        list.print();
        in.close();
    }
}
