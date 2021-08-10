package com.bs.springblog.controller;


import com.bs.springblog.controller.dto.PostForm;
import com.bs.springblog.controller.dto.PostReponseDto;
import com.bs.springblog.controller.dto.PostUpdateRequestDto;
import com.bs.springblog.domain.Member.Member;
import com.bs.springblog.domain.posts.Post;
import com.bs.springblog.service.PostService;
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


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("board")
public class PostController {
    private final PostService postService;
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
    @GetMapping("/list/{id}")
    public String openPost(Model model, @PathVariable Long id){
        PostReponseDto findPost = postService.findOneById(id);
        log.info("open updateForm");
        model.addAttribute("postForm", findPost);
        model.addAttribute("id", id);
        return "/board/updateForm";
    }

    /**
     * update
     * @param postForm
     * @param id
     * @return
     * @throws Exception
     */
//    @PostMapping("/list/{id}")
//    public String updatePost(PostUpdateRequestDto postForm, @PathVariable Long id) throws Exception {
//        postService.update(id,postForm);
//        return "redirect:/board/list";
//    }
}
