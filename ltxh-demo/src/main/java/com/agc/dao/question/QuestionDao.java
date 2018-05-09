package com.agc.dao.question;

import java.util.List;
import java.util.Map;

import com.agc.core.common.Page;
import com.agc.core.common.Pageable;
import org.springframework.stereotype.Repository;

import com.agc.dao.BaseDao;
import com.agc.dto.AnswerDto;
import com.agc.model.Question;
import com.agc.model.Question_Survey;

@Repository
public class QuestionDao extends BaseDao<Question, Integer>{

	public Page<Question> getQuestionPage(Pageable pb) {
		String sql = "SELECT q.*,(SELECT COUNT(DISTINCT qa.qa_answer_userid) FROM question_answer qa WHERE qa.qa_q_id = q.q_id)answer_num"
				+ " FROM question q  WHERE q.q_is_del = 0 ORDER BY q_create_time DESC";
		Page<Question> page = getPage(sql, pb, Question.class);
		return page;
	}

	public Page<Question_Survey> getSurveyPage(Pageable pb) {
		String sql = "SELECT q.* FROM question_survey q  WHERE q.qs_is_del = 0 ";
		Map<String,Object> o = pb.getQmp();
		if(o != null){
			if(o.get("qs_subject") != null){
				sql += " AND q.qs_subject LIKE ?";
				pb.addQueryVal("%"+o.get("qs_subject")+"%");
			}
			if(o.get("exist") != null){
				sql += " AND (SELECT COUNT(1) FROM question_relation qr WHERE qr.qr_q_id = ? AND qr.qr_qs_id = q.qs_id) ";
				if("1".equals(o.get("exist").toString())){
					sql +=" > 0 ";
				}else{
					sql +=" = 0 ";
				}
				pb.addQueryVal(o.get("q_id"));
			}
		}
		sql +=" ORDER BY q.qs_create_time DESC ";
		Page<Question_Survey> page = getPage(sql, pb, Question_Survey.class);
		return page;
	}

	//	public Page<Question_Answer> getAnswerList(Pageable pb) {
	//		String sql = "SELECT qa.* FROM question_answer qa  WHERE qa.qa_is_del = 0 ORDER BY qa.qa_create_time DESC";
	//		Page<Question_Answer> page = getPage(sql, pb, Question_Answer.class);
	//		return page;
	//	}

	public int getHasExist(Integer q_id, Long qs_id) {
		String sql = "SELECT COUNT(1) FROM question_relation WHERE qr_q_id = ? AND qr_qs_id = ?";
		int exist = jtp.queryForObject(sql,int.class,q_id,qs_id);
		return exist;
	}

	public int deleteRelSurvey(Integer q_id, Integer qs_id) {
		String sql = "DELETE FROM  question_relation WHERE qr_q_id = ? AND qr_qs_id = ? ";
		int ret = jtp.update(sql, q_id,qs_id);
		return ret;
	}

	public List<AnswerDto> getAnswerList(Integer q_id) {
		String sql = "SELECT q.q_name,qs.qs_subject qs_name,"+
				"(SELECT COUNT(1) FROM question_answer qa WHERE qa.qa_is_del = 0 AND qa.qa_qs_id=qs.qs_id AND qa.qa_q_id=q.q_id AND qa.qa_answer LIKE '%A%')a_num,"+
				"(SELECT COUNT(1) FROM question_answer qa WHERE qa.qa_is_del = 0 AND qa.qa_qs_id=qs.qs_id AND qa.qa_q_id=q.q_id AND qa.qa_answer LIKE '%B%')b_num,"+
				"(SELECT COUNT(1) FROM question_answer qa WHERE qa.qa_is_del = 0 AND qa.qa_qs_id=qs.qs_id AND qa.qa_q_id=q.q_id AND qa.qa_answer LIKE '%C%')c_num,"+
				"(SELECT COUNT(1) FROM question_answer qa WHERE qa.qa_is_del = 0 AND qa.qa_qs_id=qs.qs_id AND qa.qa_q_id=q.q_id AND qa.qa_answer LIKE '%D%')d_num,"+
				"(SELECT COUNT(1) FROM question_answer qa WHERE qa.qa_is_del = 0 AND qa.qa_qs_id=qs.qs_id AND qa.qa_q_id=q.q_id AND qa.qa_answer LIKE '%E%')e_num"+
				" FROM question_relation qr "+
				" INNER JOIN question q ON q.q_id = qr.qr_q_id "+
				" INNER JOIN question_survey qs ON qs.qs_id = qr.qr_qs_id "+
				" WHERE qr.qr_q_id = ? "+
				" ORDER BY qs.qs_id ASC ";
		List<AnswerDto> ret = getList(sql, AnswerDto.class, q_id);
		return ret;
	}


}
