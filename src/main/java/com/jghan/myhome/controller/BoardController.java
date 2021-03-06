package com.jghan.myhome.controller;

import com.jghan.myhome.model.Board;
import com.jghan.myhome.repository.BoardRepository;
import com.jghan.myhome.service.BoardService;
import com.jghan.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText){ //get요청할때 파라미터 전달
//        Page <Board> boards = boardRepository.findAll(pageable);
        Page <Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4); //현재 페이지 번호 -4, Math.max: 두 인자중 큰 값 리턴
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4); //현재 페이지 번호 -4, Math.min: 두 인자중 작은값 리턴

        System.out.println(boards.getPageable());
        System.out.println(boards.getPageable().getPageNumber());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false)Long id){
        if(id ==null){
            model.addAttribute("board", new Board()); //새 글작성
        }else {
            Board board = boardRepository.findById(id).orElse(null); // 값이 없을경우  null반환
            model.addAttribute("board", board);
        }

        return "board/form";
    }

    @PostMapping("/form")
    public String postForm(@Valid Board board, BindingResult bindingResult, Authentication authentication){
        boardValidator.validate(board, bindingResult);
        //인증정보가 담긴다.
        if(bindingResult.hasErrors()) {
            return "board/form";
        }
//        Authentication a = SecurityContextHolder.getContext().getAuthentication(); :서비스 클래스 에서 인증정보 가져오고 싶을때

        String username = authentication.getName();
        boardService.save(username, board);
        return "redirect:/board/list";
    }


}
