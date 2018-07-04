package com.ecnu;

import com.ecnu.basic.SchemeList;
import com.ecnu.process.LexicalAnalyze;
import com.ecnu.process.PreProcessor;
import com.ecnu.process.SchemeReader;
import com.ecnu.utils.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Scanner;

import static com.ecnu.process.Eval.create_global_frame;
import static com.ecnu.process.Eval.read_eval_print_loop;
import static com.ecnu.process.Eval.setSpecial_forms;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String input = null;
      //  Frame env = create_global_frame();
        Result result = new Result();
        while (true) {
            //try {
                input = in.nextLine();
                if (input.equals("end")) {
                    break;
                }
//                LexicalAnalyze lexicalAnalyze = new LexicalAnalyze();
//                SchemeList list = lexicalAnalyze.lexical_analyze(PreProcessor.preProcess(input));
//               // list.print();
//                setSpecial_forms();
//
//                while(!list.isEmpty()) {
//                    list.setNowList(new ArrayList<>());
//                    read_eval_print_loop(list, env);
//                    Tree tree = new Tree(list);
//                    System.out.println(tree.toJson());
//                    System.out.println(ErrorInfo.getErrorInfo());
//                    System.out.println(ErrorInfo.getErrorPosition());
//                    ErrorInfo.clean();
//                }
//            } catch (Exception e) {
//                System.out.println(ErrorInfo.getErrorInfo());
//            }
//            finally {
//                ErrorInfo.clean();
//            }
            result.Start(input);
        }
        in.close();
    }
}

