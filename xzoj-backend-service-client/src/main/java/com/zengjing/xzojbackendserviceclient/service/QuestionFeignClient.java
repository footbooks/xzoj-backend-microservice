package com.zengjing.xzojbackendserviceclient.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengjing.xzojbackendmodel.model.dto.question.QuestionQueryRequest;
import com.zengjing.xzojbackendmodel.model.entity.Question;
import com.zengjing.xzojbackendmodel.model.entity.QuestionSubmit;
import com.zengjing.xzojbackendmodel.model.vo.QuestionVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 题目 服务类
 * </p>
 *
 * @author zengjing
 * @since 2024-04-14
 */
@FeignClient(value = "xzoj-backend-question-service",path = "/api/question/inner")
public interface QuestionFeignClient{
    /**
     * 根据id获取题目信息
     */
    @GetMapping("/get/id")
    Question getQuestionById(@RequestParam("questionId") Long questionId);
    /**
     * 根据题目提交id获取题目提交信息
     */
    @GetMapping("/question_submit/get/id")
    QuestionSubmit getQuestionSbumitById(@RequestParam("questionSubmitId") Long questionSubmitId);
    /**
     * 修改题目信息
     */
    @PostMapping("/question_submit/update")
    Boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit);
}
