package com.zengjing.xzojbackendjudgeservice.judge.codesandbox.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.zengjing.xzojbackendcommon.common.ErrorCode;
import com.zengjing.xzojbackendcommon.exception.BusinessException;
import com.zengjing.xzojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.zengjing.xzojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.zengjing.xzojbackendmodel.model.codesandbox.ExecuteCodeResponse;

/**
 * 远程代码沙箱(实际调用代码沙箱）
 */
public class RemoteCodeSandbox implements CodeSandbox {
    private final static String AUTH_REQUEST_HEADER = "auth";
    private final static String AUTH_REQUEST_SECRET = "secretKey";

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest excuteCodeRequest) {
        System.out.println("远程代码沙箱");
        String url = "http://localhost:8130/executeCode";
        String json = JSONUtil.toJsonStr(excuteCodeRequest);
        //调用第三方接口
        String responseBody = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER,AUTH_REQUEST_SECRET)
                .body(json)
                .execute()
                .body();
        if (StrUtil.isBlank(responseBody)){
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR,"返回参数为空");
        }
        ExecuteCodeResponse executeCodeResponse = JSONUtil.toBean(responseBody, ExecuteCodeResponse.class);
        return executeCodeResponse;
    }
}