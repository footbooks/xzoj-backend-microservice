package com.zengjing.xzojbackendquestionservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengjing.xzojbackendmodel.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.zengjing.xzojbackendmodel.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.zengjing.xzojbackendmodel.model.entity.QuestionSubmit;
import com.zengjing.xzojbackendmodel.model.entity.User;
import com.zengjing.xzojbackendmodel.model.vo.QuestionSubmitVO;

/**
 * <p>
 * 题目提交 服务类
 * </p>
 *
 * @author zengjing
 * @since 2024-04-14
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     *
     * 题目提交
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);
    /**
     * 获取查询条件
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);


    /**
     * 获取题目封装
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);

}
