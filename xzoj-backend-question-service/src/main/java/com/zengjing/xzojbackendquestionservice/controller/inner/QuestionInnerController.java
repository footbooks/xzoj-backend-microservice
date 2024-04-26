package com.zengjing.xzojbackendquestionservice.controller.inner;

import com.zengjing.xzojbackendmodel.model.entity.Question;
import com.zengjing.xzojbackendmodel.model.entity.QuestionSubmit;
import com.zengjing.xzojbackendquestionservice.service.QuestionService;
import com.zengjing.xzojbackendquestionservice.service.QuestionSubmitService;
import com.zengjing.xzojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/inner")
public class QuestionInnerController implements QuestionFeignClient {
    @Resource
    private QuestionService questionService;
    @Resource
    private QuestionSubmitService questionSubmitService;
    /**
     * 根据id获取题目信息
     */
    @Override
    @GetMapping("/get/id")
    public Question getQuestionById(@RequestParam("questionId") Long questionId){
        return questionService.getById(questionId);
    }
    /**
     * 根据题目提交id获取题目提交信息
     */
    @Override
    @GetMapping("/question_submit/get/id")
    public QuestionSubmit getQuestionSbumitById(@RequestParam("questionSubmitId") Long questionSubmitId){
        return questionSubmitService.getById(questionSubmitId);
    }
    /**
     * 修改题目信息
     */
    @Override
    @PostMapping("/question_submit/update")
    public Boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit){
        return questionSubmitService.updateById(questionSubmit);
    }
}
