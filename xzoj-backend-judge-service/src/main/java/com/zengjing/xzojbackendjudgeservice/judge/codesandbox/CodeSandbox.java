package com.zengjing.xzojbackendjudgeservice.judge.codesandbox;


import com.zengjing.xzojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.zengjing.xzojbackendmodel.model.codesandbox.ExecuteCodeResponse;

public interface CodeSandbox {
    ExecuteCodeResponse executeCode(ExecuteCodeRequest excuteCodeRequest);
}
