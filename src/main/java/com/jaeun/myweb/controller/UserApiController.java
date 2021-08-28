//package com.jaeun.myweb.controller;
//
//import com.jaeun.myweb.model.Board;
//import com.jaeun.myweb.model.User;
//import com.jaeun.myweb.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class UserApiController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/users")
//    List<User> all(){
//        return userRepository.findAll();
//    }
//
//    @PostMapping("/users")
//    User newuser(@RequestBody User newuser){
//        return userRepository.save(newuser);
//    }
//
//    @GetMapping("/users/{id}")
//    User one(@PathVariable Long id){
//        return userRepository.findById(id).orElse(null);
//    }
//
//    @PutMapping("/users/{id}")
//    User replaceuser(@RequestBody User newuser, @PathVariable Long id){
//        return userRepository.findById(id)
//                .map(user -> {
////                    user.setTitle(newuser.getTitle());
////                    user.setContent(newuser.getContent());
//                    user.setBoards(newuser.getBoards());
//                    for(Board board : user.getBoards()){
//                        board.setUser(user);
//                    }
//                    return userRepository.save(user);
//                })
//                .orElseGet(()->{
//                    newuser.setId(id);
//                    return userRepository.save(newuser);
//                });
//    }
//
//    @DeleteMapping("/users/{id}")
//    void deleteuser(@PathVariable Long id){
//        userRepository.deleteById(id);
//    }
//
//}
