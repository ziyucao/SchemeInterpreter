package com.ecnu;

import com.ecnu.basic.SchemeList;
import com.ecnu.process.LexicalAnalyze;
import com.ecnu.process.PreProcessor;
import com.ecnu.utils.ErrorInfo;
import com.ecnu.utils.Frame;
import com.ecnu.utils.Tree;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import static com.ecnu.process.Eval.*;

public class Result {

    Frame env = create_global_frame();
    public  String Start(String input) {
        JsonObject jsonObject = new JsonObject();
        LexicalAnalyze lexicalAnalyze = new LexicalAnalyze();
        SchemeList list = lexicalAnalyze.lexical_analyze(PreProcessor.preProcess(input));
        setSpecial_forms();
        while (!list.isEmpty()) {
            try {
                list.setNowList(new ArrayList<>());
                read_eval_print_loop(list, env,jsonObject);
            } catch (Exception e) {
            } finally {
                list.print_(jsonObject);
                Tree tree = new Tree(list);
                jsonObject.add("tree",tree.toJson());
                ErrorInfo.toJson(jsonObject);
                System.out.println(jsonObject+"");
                ErrorInfo.clean();
            }
        }
        return jsonObject + "";
    }

}
