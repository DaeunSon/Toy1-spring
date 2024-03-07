package controller;

import dto.Logindto;
import dto.Memberdto;
import dto.Signupdto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.MemberService;

@AllArgsConstructor
@RestController
@RequestMapping("api/member")
public class MemberController {

    private MemberService memberservice;
    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<Memberdto> MemberSignup(@RequestBody Signupdto signupdto){
        return new ResponseEntity<>(memberservice.join(signupdto), HttpStatus.OK);
    }
    //로그인
    @PostMapping("/login")
    public ResponseEntity<Memberdto> MemberLogin(@RequestBody Logindto logindto){
        return new ResponseEntity<>(memberservice.login(logindto), HttpStatus.OK);
    }


}
