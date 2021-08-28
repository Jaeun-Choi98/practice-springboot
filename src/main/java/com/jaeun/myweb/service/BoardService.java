package com.jaeun.myweb.service;

import com.jaeun.myweb.model.Board;
import com.jaeun.myweb.model.User;
import com.jaeun.myweb.repository.BoardRepository;
import com.jaeun.myweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Board save(String userName, Board board){
        User user = userRepository.findByUsername(userName);
        board.setUser(user);
        return boardRepository.save(board);
    }

}
