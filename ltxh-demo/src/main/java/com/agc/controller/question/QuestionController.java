package com.agc.controller.question;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.agc.core.common.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agc.controller.BaseController;
import com.agc.dto.AnswerDto;
import com.agc.model.Question;
import com.agc.model.Question_Survey;
import com.agc.service.question.QuestionService;

@RequestMapping("question")
@Controller
public class QuestionController extends BaseController{

	@Resource
	private QuestionService qs;
	
	/**
	 * 问卷分页
	 * @param model
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("getQuestionList")
	public String getQuestionList(Model model,@RequestParam(defaultValue = "1")Integer pageCurrent){
		Page<Question> page = qs.getQuestionPage(pageCurrent);
		model.addAttribute("pageBean", page);
		return "question/question-list";
	}
	
	/**
	 * 问卷题目分页
	 * @param model
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("getSurveyList")
	public String getSurveyList(Model model,@RequestParam(defaultValue = "1")Integer pageCurrent){
		Page<Question_Survey> page = qs.getSurveyPage(pageCurrent);
		model.addAttribute("pageBean", page);
		return "question/survey-list";
	}
	
	
	/**
	 * 关联题目
	 * @param model
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("relateSurvey")
	public String relateSurvey(Model model,@RequestParam(defaultValue = "1")Integer pageCurrent,Integer q_id,Integer exist,
			String qs_subject){
		model.addAttribute("exist", exist);
		model.addAttribute("qs_subject", qs_subject);
		model.addAttribute("q_id", q_id);
		Map<String, Object> map = getMap();
		map.put("exist", exist);
		map.put("qs_subject", qs_subject);
		map.put("q_id", q_id);
		Page<Question_Survey> page = qs.getSurveyPage(pageCurrent,map);
		model.addAttribute("pageBean", page);
		return "question/relSurvey-list";
	}
	
	/**
	 * 问卷答题分页
	 * @param model
	 * @param pageCurrent
	 * @return
	 */
//	@RequestMapping("getAnswerList")
//	public String getAnswerList(Model model,@RequestParam(defaultValue = "1")Integer pageCurrent){
//		Page<Question_Answer> page = qs.getAnswerList(pageCurrent);
//		model.addAttribute("pageBean", page);
//		return "question/answer-list";
//	}
	
	/**
	 * 删除问卷
	 * @param params
	 * @return
	 */
	@RequestMapping(value="delQuestion",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String delQuestion(@RequestParam Map<String, Object> params){

		return qs.delQuestion(params);
	}
	
	
	/**
	 * 删除题目
	 * @param params
	 * @return
	 */
	@RequestMapping(value="delSurvey",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String delSurvey(@RequestParam Map<String, Object> params){

		return qs.delSurvey(params);
	}
	
	/**
	 * 跳转至问卷新增页面
	 * @return
	 */
	@RequestMapping("addQuestion")
	public String addQuestion(){

		return "question/question-add";
	}
	
	/**
	 * 跳转至题目新增页面
	 * @return
	 */
	@RequestMapping("addSurvey")
	public String addSurvey(){

		return "question/survey-add";
	}
	
	
	/**
	 * 跳转至问卷编辑
	 * @return
	 */
	@RequestMapping("editQuestion")
	public String editQuestion(Integer q_id,Model model){
		Question question = qs.getQuestionById(q_id);
        model.addAttribute("question",question);
		return "question/question-edit";
	}
	

	/**
	 * 跳转至问卷编辑
	 * @return
	 */
	@RequestMapping("editSurvey")
	public String editSurvey(Integer qs_id,Model model){
		Question_Survey survey = qs.getSurveyById(qs_id);
        model.addAttribute("survey",survey);
		return "question/survey-edit";
	}
	
	
	/**
	 * 新增题目
	 * @param params
	 * @return
	 */
	@RequestMapping(value="saveSurvey",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String saveSurvey(@RequestParam Map<String, Object> params,String qs_right_answer){
		//springMVC checkbox bug
		params.put("qs_right_answer", qs_right_answer);
		return qs.saveSurvey(params);
	}
	
	/**
	 * 新增问卷
	 * @param params
	 * @return
	 */
	@RequestMapping(value="saveQuestion",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String saveQuestion(@RequestParam Map<String, Object> params){

		return qs.saveQuestion(params);
	}
	
	/**
	 * 编辑问卷
	 * @param params
	 * @return
	 */
	@RequestMapping(value="updateQuestion",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String updateQuestion(@RequestParam Map<String, Object> params){

		return qs.updateQuestion(params);
	}
	
	
	/**
	 * 编辑题目
	 * @param params
	 * @return
	 */
	@RequestMapping(value="updateSurvey",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String updateSurvey(@RequestParam Map<String, Object> params,String qs_right_answer){
		//springMVC checkbox bug
		params.put("qs_right_answer", qs_right_answer);
		return qs.updateSurvey(params);
	}
	
	/**
	 * 关联题目
	 * @param q_id
	 * @param qs_id
	 * @param op
	 * @return
	 */
	@RequestMapping(value="relSurvey",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String relSurvey(Integer q_id,Integer qs_id,Integer op){
		return qs.relSurvey(q_id,qs_id,op);
	}
	
	/**
	 * 查看答题情况
	 * @param q_id
	 * @param model
	 * @return
	 */
	@RequestMapping("getAnswerList")
	public String getAnswerList(Integer q_id,Model model){
		List<AnswerDto> as = qs.getAnswerList(q_id);
		model.addAttribute("as", as);
		return "question/answer-list";
	}
	
}
