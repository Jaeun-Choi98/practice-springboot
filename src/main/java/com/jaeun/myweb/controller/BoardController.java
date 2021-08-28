package com.jaeun.myweb.controller;

import com.jaeun.myweb.model.Board;
import com.jaeun.myweb.repository.BoardRepository;
import com.jaeun.myweb.service.BoardService;
import com.jaeun.myweb.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
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
    private BoardValidator boardValidator;
    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 3) Pageable page,
                       @RequestParam (required = false, defaultValue = "") String searchText){
        //Page<Board> boards = boardRepository.findAll(page);

        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText,searchText,page);
        int startPage = Math.max(1, boards.getPageable().getPageNumber()-4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber()+4);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("boards",boards);
        return "board/list";
    }

    //순서 : 웹브라우저에 localhost:9090/form을 입력-> form함수로 맵핑 -> 템플릿에 새로운 (이름이 board인)Board객체 생성
    //       -> templates/board/form.html에 있는 form태그에서 submit속성을 가진 버튼이 눌러지면 post method가 호출
    //       -> greetingSubmit함수와 맵핑 -> board객체에 데이터가 저장 -> redirect키워드를 통해 list함수로 다시 조회
    //       -> view에 뿌려줌.
    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id){
        if(id ==null){
            model.addAttribute("board",new Board());
        }else{
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board",board);
        }

        return "board/form" ;
    }

    @PostMapping("/form")
    public String postForm(@Valid Board board, BindingResult bindingResult, Authentication authentication){
        boardValidator.validate(board,bindingResult);
        if(bindingResult.hasErrors()){
            return "board/form";
        }else {
            String userName = authentication.getName();
            boardService.save(userName,board);
            //boardRepository.save(board);
            return "redirect:/board/list";  //redirect키워드 -> list함수로 다시 연결(조회)되면서 veiw에 뿌려주게 된다.
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
