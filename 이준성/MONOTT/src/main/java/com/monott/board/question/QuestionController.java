package com.monott.board.question;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.monott.board.answer.AnswerForm;
import com.monott.user.SiteUser;
import com.monott.user.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
 /*  1. question/list�� ��û�ϸ� question_list.html�� �����ϱ� 
	@RequestMapping("/question/list")
	public String list() {
		return "question_list";
	} 
	
	*/
	
	//private final QuestionRepository questionRepository;
	
	private final QuestionService questionService;
	private final UserService userService;
	
	 @RequestMapping("/list")
	    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
	            @RequestParam(value = "kw", defaultValue = "") String kw) {
	        Page<Question> paging = this.questionService.getList(page, kw);
	        model.addAttribute("paging", paging);
	        model.addAttribute("kw", kw);
	        return "question_list";
	    }
	
	@RequestMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String questionCreate( @Valid QuestionForm questionForm,
			                      BindingResult bindingResult,
			                      Principal principal )                      
    {
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser );
		return "redirect:/question/list";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String questionModify( QuestionForm questionForm, @PathVariable("id") Integer id,  Principal principal  ){
		 Question question = this.questionService.getQuestion(id); //�����Ϸ��� ���̵� �����ͺ��̽� �ȿ� �ִ��� �˻� 
		 if( !question.getAuthor().getUsername().equals(principal.getName() ) ) {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "���������� �����ϴ�.");
		 }
		 questionForm.setSubject(question.getSubject());
		 questionForm.setContent(question.getContent());
		 return "question_form";
		 
	}
	
	
	@PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult, 
                                 Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        Question question = this.questionService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "���������� �����ϴ�.");
        }
        this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String questionDelete( Principal principal,  @PathVariable("id") Integer id)
	{
		Question question = this.questionService.getQuestion(id);
		if( !question.getAuthor().getUsername().equals(principal.getName()  ) ) 
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "���������� �����ϴ�.");
		}
		this.questionService.delete(question);
		return "redirect:/question/list";
	}
	
	
	
	
	
}