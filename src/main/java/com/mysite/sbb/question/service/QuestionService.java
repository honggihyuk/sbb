package com.mysite.sbb.question.service;

import com.mysite.sbb.question.dto.QuestionDto;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionService {

//    @Autowired
    private final QuestionRepository questionRepository;

    public Page<Question> getList(int page) {
//        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "created");
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("created")));
        Page<Question> paging = questionRepository.findAll(pageable); //page 잡아오기
//        List<Question> questionList = questionRepository.findAll();
        return paging;
    }

    public Question getQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 엔티티가 없습니다."));
        return question;
    }

    public void createQuestion(String subject, String content) {
        Question question = Question.builder()
                .subject(subject)
                .content(content)
                .created(LocalDateTime.now())
                .build();

        questionRepository.save(question);
    }

    public void createQuestion(QuestionDto questionDto) {
        Question question = Question.builder()
                .subject(questionDto.getSubject())
                .content(questionDto.getContent())
                .created(LocalDateTime.now())
                .build();

        questionRepository.save(question);
    }
}
