package com.agc.service.question;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.agc.core.common.Page;
import com.agc.core.common.Pageable;
import org.springframework.stereotype.Service;

import com.agc.constant.C;
import com.agc.dao.question.QuestionDao;
import com.agc.dto.AnswerDto;
import com.agc.model.Question;
import com.agc.model.Question_Relation;
import com.agc.model.Question_Survey;
import com.agc.service.BaseService;

@Service
public class QuestionService extends BaseService{

	@Resource
	private QuestionDao qDao;

	/**
	 * 问卷分页
	 * @param pageCurrent
	 * @return
	 */
	public Page<Question> getQuestionPage(Integer pageCurrent) {
		Pageable pageable = getPageable(pageCurrent, null);
		Page<Question> page = qDao.getQuestionPage(pageable);
		return page;
	}

	/**
	 * 问卷题目分页
	 * @param pageCurrent
	 * @return
	 */
	public Page<Question_Survey> getSurveyPage(Integer pageCurrent) {
		Pageable pageable = getPageable(pageCurrent, null);
		Page<Question_Survey> page = qDao.getSurveyPage(pageable);
		return page;
	}

	/**
	 * 问卷关联题目分页
	 * @param pageCurrent
	 * @param q_id
	 * @return
	 */
	public Page<Question_Survey> getSurveyPage(Integer pageCurrent, Map<String,Object> map) {
		Pageable pageable = getPageable(pageCurrent, map);
		Page<Question_Survey> page = qDao.getSurveyPage(pageable);
		Integer q_id = Integer.parseInt(map.get("q_id").toString());
		//查看是否已经关联
		List<Question_Survey> surveyList = page.getContent();
		if(isNotEmpty(surveyList)){
        for (Question_Survey survey : surveyList) {
        	Long qs_id = survey.getQs_id();
        	int exist = qDao.getHasExist(q_id,qs_id);
        	survey.setExist(exist);
		}			
		}
		return page;
	}
	
	
	/**
	 * 问卷答题分页
	 * @param pageCurrent
	 * @return
	 */
//	public Page<Question_Answer> getAnswerList(Integer pageCurrent) {
//		Pageable pageable = getPageable(pageCurrent, null);
//		Page<Question_Answer> page = qDao.getAnswerList(pageable);
//		return page;
//	}

	
	/**
	 * 删除问卷
	 * @param params
	 * @return
	 */
	public String delQuestion(Map<String, Object> params) {
		int ret = qDao.updateById(params, Question.class, "q_id");
		if(ret < 0) return "500";
		return C.DELETE_OK;
	}

	/**
	 * 获取问卷
	 * @param q_id
	 * @return
	 */
	public Question getQuestionById(Integer q_id) {
		Question q = qDao.getById(q_id, Question.class, "q_id");
		return q;
	}

	
	/**
	 * 新增问卷
	 * @param params
	 * @return
	 */
	public String saveQuestion(Map<String, Object> params) {
		params.put("q_create_time", System.currentTimeMillis());
		int ret = qDao.insertBean(params, Question.class);
		if(ret < 0)return "500";
		return C.ADD_OK;
	}

	/**
	 * 编辑问卷
	 * @param params
	 * @return
	 */
	public String updateQuestion(Map<String, Object> params) {
		int ret = qDao.updateById(params, Question.class, "q_id");
		if(ret < 0)return "500";
		return C.UPDATE_OK;
	}

	/**
	 * 获取题目
	 * @param qs_id
	 * @return
	 */
	public Question_Survey getSurveyById(Integer qs_id) {
		Question_Survey survey = qDao.getById(qs_id, Question_Survey.class, "qs_id");
		return survey;
	}

	/**
	 * 新增题目
	 * @param params
	 * @return
	 */
	public String saveSurvey(Map<String, Object> params) {
		params.put("qs_create_time", System.currentTimeMillis());
		int ret = qDao.insertBean(params, Question_Survey.class);
		if(ret < 0)return "500";
		return C.ADD_OK;
	}

	/**
	 * 编辑题目
	 * @param params
	 * @return
	 */
	public String updateSurvey(Map<String, Object> params) {
		int ret = qDao.updateById(params, Question_Survey.class, "qs_id");
		if(ret < 0)return "500";
		return C.UPDATE_OK;
	}

	
	/**
	 * 删除题目
	 * @param params
	 * @return
	 */
	public String delSurvey(Map<String, Object> params) {
		int ret = qDao.updateById(params, Question_Survey.class, "qs_id");
		if(ret < 0) return "500";
		return C.DELETE_OK;
	}

	
	/**
	 * 关联题目
	 * @param q_id
	 * @param qs_id
	 * @param op
	 * @return
	 */
	public String relSurvey(Integer q_id, Integer qs_id, Integer op) {
		int ret = 0;
		if(op == 0){
			//取消关联
			ret = qDao.deleteRelSurvey(q_id,qs_id);
		}else{
			//关联
			Question_Relation qr = new Question_Relation();
			qr.setQr_q_id(q_id.longValue());
			qr.setQr_qs_id(qs_id.longValue());
			ret = qDao.insertBean(qr);
		}
		if(ret < 0) return "500";
		return C.OP_OK;
	}

	/**
	 * 查看答题情况
	 * @param q_id
	 * @return
	 */
	public List<AnswerDto> getAnswerList(Integer q_id) {
		
		return qDao.getAnswerList(q_id);
	}

	
	
}
