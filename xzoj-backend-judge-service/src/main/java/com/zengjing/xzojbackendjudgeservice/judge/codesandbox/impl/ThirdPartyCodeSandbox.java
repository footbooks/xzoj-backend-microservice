package com.zengjing.xzojbackendjudgeservice.judge.codesandbox.impl;

import com.zengjing.xzojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.zengjing.xzojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.zengjing.xzojbackendmodel.model.codesandbox.ExecuteCodeResponse;

/**
 * 第三方代码沙箱（非自己开发的）
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest excuteCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
