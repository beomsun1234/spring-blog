package com.bs.springblog.controller;


import com.bs.springblog.config.auth.dto.SessionUser;
import com.bs.springblog.controller.dto.*;
import com.bs.springblog.domain.Member.Member;
import com.bs.springblog.domain.posts.Post;
import com.bs.springblog.service.PostService;
import com.bs.springblog.service.ReplyService;
import com.bs.springblog.validator.PostValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("board")
public class PostController {
    private final HttpSession httpSession;
    private final PostService postService;
    private final ReplyService replyService;
    private final PostValidator postValidator;
    /**
     *
     * @param model
     * @return
     * 게시판 조회
     */
    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable,
                       @RequestParam(required = false,defaultValue = "") String searchText){
        log.info("board/list open");
        Page<PostReponseDto> posts = postService.findByTitleContainingOrContentContaining(searchText,searchText,pageable);
        int startPage = Math.max(1, posts.getPageable().getPageNumber() - 4);
        int endPage = Math.min(posts.getTotalPages(), posts.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("posts",posts);
        return "board/list";
    }

    /**
     * open form
     * @return
     */
    @GetMapping("/form")
    public String form(Model model){
        log.info("open postForm");
        SessionUser member = (SessionUser)httpSession.getAttribute("member");
        if (member == null){
            //맴버가 없으면 로그인페이지로
            log.info("로그인해야합니다");
            return "member/join";
        }
        model.addAttribute("member", member);
        model.addAttribute("postForm", new PostForm());
        return "board/form";
    }

    /**
     * postForm
     * @return
     */
//    @PostMapping("/form")
//    public String createForm(@Valid PostForm postForm, BindingResult bindingResult){
//        postValidator.validate(postForm, bindingResult);
//        if (bindingResult.hasErrors()){
//            return "board/form";
//        }
//        log.info("open postForm");
//        postService.save(postForm);
//        return "redirect:/board/list";
//    }

    /**
     * 상세페이지
     * @param
     * @return
     */
    @GetMapping("/detail/{id}")
    public String openDetail(Model model, @PathVariable Long id){
        //작성자만 글 수정 가능
        // 그럴려면 작성자 id와 세션id를 비교하여 아니면 못쓰게
        PostReponseDto findPost = postService.findOneById(id);
        SessionUser loginMember = (SessionUser) httpSession.getAttribute("member");
        List<ReplyResponseDto> reply = replyService.findByPostId(id);
        model.addAttribute("boardId", id);
        model.addAttribute("postForm", findPost);
        model.addAttribute("reply", reply);
        if (loginMember == null){
            //로그인 안할시 게스트 계정생성 보기만가능하게하기
            log.info("guest로그인");
            Long guestId = Long.valueOf(0);
            Guest guest = Guest.builder()
                    .id(guestId)
                    .name("guest")
                    .build();
            model.addAttribute("loginMember", guest);
        }
        else {
            model.addAttribute("loginMember", loginMember);
        }
        return "/board/detail";
    }

    /**
     * update
     *
     * @param id
     * @return+
     * @throws Exception
     */
    @GetMapping("/list/{id}")
    public String openUpdateForm(Model model, @PathVariable Long id){
        PostReponseDto findPost = postService.findOneById(id);
        log.info("open updateForm");
        model.addAttribute("postForm", findPost);
        model.addAttribute("id", id);
        return "/board/updateForm";
    }
//    @PostMapping("/list/{id}")
//    public String updatePost(PostUpdateRequestDto postForm, @PathVariable Long id) throws Exception {
//        postService.update(id,postForm);
//        return "redirect:/board/list";
//    }
}
