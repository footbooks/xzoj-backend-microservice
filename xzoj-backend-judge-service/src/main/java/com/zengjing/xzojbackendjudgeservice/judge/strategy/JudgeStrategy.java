package com.zengjing.xzojbackendjudgeservice.judge.strategy;


import com.zengjing.xzojbackendmodel.model.codesandbox.JudgeInfo;

/**
 * 判题策略
 */
public interface JudgeStrategy {
    JudgeInfo doJudge(JudgeContext judgeContext);
}
