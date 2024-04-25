package com.zengjing.xzojbackendquestionservice.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zengjing.xzojbackendmodel.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.zengjing.xzojbackendmodel.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.zengjing.xzojbackendmodel.model.entity.QuestionSubmit;
import com.zengjing.xzojbackendmodel.model.enums.QuestionSubmitLanguageEnum;
import com.zengjing.xzojbackendmodel.model.enums.QuestionSubmitStatusEnum;
import com.zengjing.xzojbackendmodel.model.vo.QuestionSubmitVO;
import com.zengjing.xzojbackendquestionservice.mapper.QuestionSubmitMapper;
import com.zengjing.xzojbackendquestionservice.service.QuestionService;
import com.zengjing.xzojbackendquestionservice.service.QuestionSubmitService;
import com.zengjing.xzojbackendcommon.common.ErrorCode;
import com.zengjing.xzojbackendcommon.constant.CommonConstant;
import com.zengjing.xzojbackendcommon.exception.BusinessException;
import com.zengjing.xzojbackendcommon.utils.SqlUtils;
import com.zengjing.xzojbackendmodel.model.entity.User;
import com.zengjing.xzojbackendmodel.model.vo.UserVO;
import com.zengjing.xzojbackendmodel.model.entity.Question;
import com.zengjing.xzojbackendserviceclient.service.JudgeFeignClient;
import com.zengjing.xzojbackendserviceclient.service.UserFeignClient;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * <p>
 * 题目提交 服务实现类
 * </p>
 *
 * @author zengjing
 * @since 2024-04-14
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit> implements QuestionSubmitService {

    @Resource
    private QuestionService questionService;
    @Resource
    private UserFeignClient userFeignClient;
    @Resource
    @Lazy
    private JudgeFeignClient judgeFeignClient;
    /**
     * 提交题目
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        //判断编程语言是否合法
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum enumByValue = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (enumByValue==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"编程语言违法");
        }
        Long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        long userId = loginUser.getId();
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setLanguage(language);
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");
        boolean result = this.save(questionSubmit);
        if(!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"提交题目失败");
        }
        Long questionSubmitId = questionSubmit.getId();
        CompletableFuture.runAsync(()->{
            judgeFeignClient.doJudge(questionSubmitId);
        });
        return questionSubmitId;
    }

    /**
     * 获取查询包装类
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest) {
        QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
        if (questionSubmitQueryRequest == null) {
            return queryWrapper;
        }
        Long questionId = questionSubmitQueryRequest.getQuestionId();
        String language = questionSubmitQueryRequest.getLanguage();
        Integer status = questionSubmitQueryRequest.getStatus();
        Long userId = questionSubmitQueryRequest.getUserId();
        String sortField = questionSubmitQueryRequest.getSortField();
        String sortOrder = questionSubmitQueryRequest.getSortOrder();
        // 拼接查询条件
        queryWrapper.like(StringUtils.isNotBlank(language), "language", language);
        queryWrapper.like(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.like(ObjectUtils.isNotEmpty(questionId), "questionId", questionId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }


    @Override
    public QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser) {
        QuestionSubmitVO questionSubmitVO = QuestionSubmitVO.objToVo(questionSubmit);
        long questionSubmitId = questionSubmit.getId();
        // 1. 关联查询用户信息
        Long userId = questionSubmit.getUserId();
        User user = null;
        if (userId != null && userId > 0) {
            user = userFeignClient.getById(userId);
        }
        UserVO userVO = userFeignClient.getUserVO(user);
        questionSubmitVO.setUserVO(userVO);
        //2.脱敏
        if(userId != loginUser.getId() && !userFeignClient.isAdmin(loginUser)){
            questionSubmitVO.setCode(null);
        }
        return questionSubmitVO;
    }

    @Override
    public Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage,User loginUser) {
        List<QuestionSubmit> questionSubmitList = questionSubmitPage.getRecords();
        Page<QuestionSubmitVO> questionSubmitVOPage = new Page<>(questionSubmitPage.getCurrent(), questionSubmitPage.getSize(), questionSubmitPage.getTotal());
        if (CollUtil.isEmpty(questionSubmitList)) {
            return questionSubmitVOPage;
        }
        List<QuestionSubmitVO> questionSubmitVOS = questionSubmitList.stream()
                .map(questionSubmit -> getQuestionSubmitVO(questionSubmit, loginUser))
                .collect(Collectors.toList());
        questionSubmitVOPage.setRecords(questionSubmitVOS);
        return questionSubmitVOPage;
    }
}
