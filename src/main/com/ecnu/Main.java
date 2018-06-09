package com.ecnu;

import com.ecnu.basic.SchemeList;
import com.ecnu.process.LexicalAnalyze;
import com.ecnu.process.PreProcessor;
import com.ecnu.process.SchemeReader;
import com.ecnu.utils.AbPair;
import com.ecnu.utils.Pair;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String input = null;
        while (true) {
            try {
                input = in.nextLine();
                if (input.equals("end")) {
                    break;
                }
                LexicalAnalyze lexicalAnalyze = new LexicalAnalyze();
                SchemeList list = lexicalAnalyze.lexical_analyze(PreProcessor.preProcess(input));
                list.print();

                AbPair abPair = SchemeReader.scheme_read(list);
                System.out.println(abPair.toString());
                System.out.println((abPair.print()));
            } catch (Exception e) {
                System.out.println("Error:::"+e.getMessage());
            }
        }
        in.close();
    }
}

