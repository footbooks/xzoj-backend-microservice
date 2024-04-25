package com.zengjing.xzojbackendjudgeservice.judge.strategy;

import com.zengjing.xzojbackendmodel.model.codesandbox.JudgeInfo;
import com.zengjing.xzojbackendmodel.model.dto.question.JudgeCase;
import com.zengjing.xzojbackendmodel.model.entity.Question;
import com.zengjing.xzojbackendmodel.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;
@Data
public class JudgeContext {
    private JudgeInfo judgeInfo;
    private List<String> inputList;
    private List<String> outputList;
    private Question question;
    private List<JudgeCase> judgeCaseList;
    private QuestionSubmit questionSubmit;
}
