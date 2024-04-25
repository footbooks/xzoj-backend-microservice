package com.zengjing.xzojbackendjudgeservice.judge.codesandbox;

import com.zengjing.xzojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.zengjing.xzojbackendjudgeservice.judge.strategy.JavaLanguageJudgeStrategy;
import com.zengjing.xzojbackendjudgeservice.judge.strategy.JudgeContext;
import com.zengjing.xzojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.zengjing.xzojbackendmodel.model.codesandbox.JudgeInfo;
import com.zengjing.xzojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {
    /**
     * 执行判题
     */
    public JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if(language.equals("java")){
            judgeStrategy=new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
