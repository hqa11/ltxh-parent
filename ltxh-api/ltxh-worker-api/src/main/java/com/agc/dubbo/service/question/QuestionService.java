package com.agc.dubbo.service.question;


import com.agc.core.common.Page;
import com.agc.dubbo.dto.AnswerDto;
import com.agc.dubbo.model.Question;
import com.agc.dubbo.model.Question_Survey;

import java.util.List;
import java.util.Map;

public interface QuestionService  {


	/**
	 * 问卷分页
	 * @param pageCurrent
	 * @return
	 */
	public Page<Question> getQuestionPage(Integer pageCurrent);

	/**
	 * 问卷题目分页
	 * @param pageCurrent
	 * @return
	 */
	public Page<Question_Survey> getSurveyPage(Integer pageCurrent);

	/**
	 * 问卷关联题目分页
	 * @param pageCurrent
	 * @param
	 * @return
	 */
	public Page<Question_Survey> getSurveyPage(Integer pageCurrent, Map<String,Object> map);
	

	
	/**
	 * 删除问卷
	 * @param params
	 * @return
	 */
	public String delQuestion(Map<String, Object> params);

	/**
	 * 获取问卷
	 * @param q_id
	 * @return
	 */
	public Question getQuestionById(Integer q_id);

	
	/**
	 * 新增问卷
	 * @param params
	 * @return
	 */
	public String saveQuestion(Map<String, Object> params) ;

	/**
	 * 编辑问卷
	 * @param params
	 * @return
	 */
	public String updateQuestion(Map<String, Object> params);

	/**
	 * 获取题目
	 * @param qs_id
	 * @return
	 */
	public Question_Survey getSurveyById(Integer qs_id);

	/**
	 * 新增题目
	 * @param params
	 * @return
	 */
	public String saveSurvey(Map<String, Object> params) ;
	/**
	 * 编辑题目
	 * @param params
	 * @return
	 */
	public String updateSurvey(Map<String, Object> params);

	
	/**
	 * 删除题目
	 * @param params
	 * @return
	 */
	public String delSurvey(Map<String, Object> params) ;

	
	/**
	 * 关联题目
	 * @param q_id
	 * @param qs_id
	 * @param op
	 * @return
	 */
	public String relSurvey(Integer q_id, Integer qs_id, Integer op);

	/**
	 * 查看答题情况
	 * @param q_id
	 * @return
	 */
	public List<AnswerDto> getAnswerList(Integer q_id);

	
	
}
