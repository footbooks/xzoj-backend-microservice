package com.zengjing.xzojbackendmodel.model.dto.question;


import com.zengjing.xzojbackendcommon.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 查询请求
 */
@Data
public class QuestionQueryRequest extends PageRequest implements Serializable {

    private Long id;

    private String title;

    private String content;

    private List<String> tags;

    private String answer;

    private List<JudgeCase> judgeCase;

    private JudgeConfig judgeConfig;

    private Long userId;

    private static final long serialVersionUID = 1L;
}