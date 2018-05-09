package com.agc.model;


/**
 * 问卷调查-题目关联表
 *
 * @author Administrator
 */
public class Question_Answer {

    private Long qa_id;
    private Long qa_q_id;    //问卷表ID
    private Long qa_qs_id;    //关联题目表ID
    private Long qa_answer_userid;    //userID
    private String qa_answer;    //
    private Long qa_create_time;    //
    private Integer qa_is_del;    //0


    public Long getQa_id() {
        return qa_id;
    }

    public void setQa_id(Long qa_id) {
        this.qa_id = qa_id;
    }

    public Long getQa_q_id() {
        return qa_q_id;
    }

    public void setQa_q_id(Long qa_q_id) {
        this.qa_q_id = qa_q_id;
    }

    public Long getQa_qs_id() {
        return qa_qs_id;
    }

    public void setQa_qs_id(Long qa_qs_id) {
        this.qa_qs_id = qa_qs_id;
    }

    public Long getQa_answer_userid() {
        return qa_answer_userid;
    }

    public void setQa_answer_userid(Long qa_answer_userid) {
        this.qa_answer_userid = qa_answer_userid;
    }

    public String getQa_answer() {
        return qa_answer;
    }

    public void setQa_answer(String qa_answer) {
        this.qa_answer = qa_answer;
    }

    public Long getQa_create_time() {
        return qa_create_time;
    }

    public void setQa_create_time(Long qa_create_time) {
        this.qa_create_time = qa_create_time;
    }

    public Integer getQa_is_del() {
        return qa_is_del;
    }

    public void setQa_is_del(Integer qa_is_del) {
        this.qa_is_del = qa_is_del;
    }
}
