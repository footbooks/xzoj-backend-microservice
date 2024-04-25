package com.zengjing.xzojbackendjudgeservice.judge.codesandbox;


import com.zengjing.xzojbackendjudgeservice.judge.codesandbox.impl.ExampleCodeSandbox;
import com.zengjing.xzojbackendjudgeservice.judge.codesandbox.impl.RemoteCodeSandbox;
import com.zengjing.xzojbackendjudgeservice.judge.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * 代码沙箱工厂（根据传入进来的Type，返回对应的代码沙箱示例）
 */
public class CodeSandboxFactory {

    public static CodeSandbox newInstance(String type){
        switch (type){
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
