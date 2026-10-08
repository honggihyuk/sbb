package com.mysite.sbb.question.service;

import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionService {

//    @Autowired
    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        List<Question> questionList = questionRepository.findAll();
        return questionList;
    }
}
