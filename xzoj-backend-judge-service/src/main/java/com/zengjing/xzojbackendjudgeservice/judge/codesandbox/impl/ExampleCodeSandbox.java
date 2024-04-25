package com.zengjing.xzojbackendjudgeservice.judge.codesandbox.impl;


import com.zengjing.xzojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.zengjing.xzojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.zengjing.xzojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import com.zengjing.xzojbackendmodel.model.codesandbox.JudgeInfo;
import com.zengjing.xzojbackendmodel.model.enums.JudgeInfoMessageEnum;
import com.zengjing.xzojbackendmodel.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * 示例代码沙箱
 */
public class ExampleCodeSandbox implements CodeSandbox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest excuteCodeRequest) {
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        List<String> inputList = excuteCodeRequest.getInputList();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("示例代码沙箱");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
