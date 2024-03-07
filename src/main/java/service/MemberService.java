package service;

import domain.Member;
import dto.Logindto;
import dto.Memberdto;
import dto.Signupdto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.MemberRepository;
//security 설정

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordencoder;


    /**
     * 회원가입
     */
    public Memberdto join(Signupdto signupdto){
        //중복 이메일 예외 처리
        if(memberRepository.findByEmail(signupdto.getEmail()).isPresent()){
            throw new RuntimeException("중복된 이메일입니다.");
        }

        //가입

        Member member = new Member();
        member.setEmail(signupdto.getEmail());
        member.setNickname(signupdto.getNickname());
        member.setPassword(passwordencoder.encode(signupdto.getPassword())); //암호화


        Memberdto memberdto = new Memberdto();
        memberdto.setEmail(member.getEmail());
        memberdto.setNickname(member.getNickname());
        memberdto.setPassword(member.getPassword());

        return memberdto;
    }

    /**
     * 로그인
     */

    public Memberdto login(Logindto logindto){
        Member member = memberRepository.findByEmail(logindto.getEmail()).orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다"));
        // 예외 처리

        //비밀번호 오류시 작성코드
        if(!passwordencoder.matches(logindto.getPassword(), member.getPassword())){
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        Memberdto memberdto = new Memberdto();
        memberdto.setEmail(logindto.getEmail());
        memberdto.setPassword(logindto.getPassword());

        return memberdto;

    }


}
