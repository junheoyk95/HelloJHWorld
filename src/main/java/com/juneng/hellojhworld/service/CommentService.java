package com.juneng.hellojhworld.service;

import com.juneng.hellojhworld.domain.BlogPost;
import com.juneng.hellojhworld.domain.Comment;
import com.juneng.hellojhworld.dto.CommentForm;
import com.juneng.hellojhworld.repository.BlogRepository;
import com.juneng.hellojhworld.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    // + @RequiredArgsConstructor 이렇게 생성하는 걸 추천 (테스트하기도 용이)
    private final CommentRepository commentRepository;

    // 댓글 생성
    public void addComment(CommentForm commentForm) {

        //commentRepository.save(comment);
        // 위 방법 말고 아래로 해봄. (강사 추천 방법)
        Comment commentE = Comment.createComment(commentForm);
        commentRepository.save(commentE);

    }

    // 조회 시 (readOnly = true) 로 설정하면 읽기 전용 트렌젝션으로 취급하여 부하를 덜 일으킴.
    @Transactional(readOnly = true)
    public List<Comment>  findCommentByPostId(String postId) {

        return commentRepository.findCommentByPostId(postId);
    }


}
