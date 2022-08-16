package com.monott.board.answer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.monott.DataNotFoundException;
import com.monott.board.question.Question;
import com.monott.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
   
	private final AnswerRepository answerRepository;
	
	//답변글을 저장하는 서비스 
	public void create( Question question , String content , SiteUser author) {
		//Answer 클래스에 setter 값 넣고 저장소에 save한다
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate( LocalDate.now() );
		answer.setQuestion(question);
		answer.setAuthor(author);
		this.answerRepository.save(answer);
	}
	//아이디를 입력 받아서 답변한 사람의 아이디로 조회하기 
	public Answer getAnswer(Integer id) {
		Optional<Answer> answer = this.answerRepository.findById(id);
		if(answer.isPresent()) {
			return answer.get();
		} else {
			throw new DataNotFoundException("answer not found!!");
		}
	}
	
	public void modify(Answer answer, String content) {
		answer.setContent(content);
		answer.setModifyDate(LocalDateTime.now());
		this.answerRepository.save(answer);
	}
	
	public void delete(Answer answer) {
		this.answerRepository.delete(answer);
	}
	
	 public void vote(Answer answer, SiteUser siteUser) {
	        answer.getVoter().add(siteUser);
	        this.answerRepository.save(answer);
	    }
	
}