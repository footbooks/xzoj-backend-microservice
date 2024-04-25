package com.zengjing.xzojbackendjudgeservice.judge;


import com.zengjing.xzojbackendmodel.model.entity.QuestionSubmit;

/**
 * 判题服务
 */
public interface JudgeService {
    QuestionSubmit doJudge(long questionSubmitId);
}
