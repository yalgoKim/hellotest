package hello.core.singletone;

import hello.core.AppConfig;
import hello.core.beanfind.ApplicationContextBasicFindTest;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletoneTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        //1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }
    
    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletoneServiceTest() {
        
        //private으로 생성자를 막아두어 컴파일 오류가 발생한다.
        // new SingletoneService();
        
        // 1. 조회 : 호출할 때 마다 같은 객체를 반환
        SingletoneService singletoneService1 = SingletoneService.getInstance();
        // 2. 조회 : 호출할 때 마다 같은 객체를 반환
        SingletoneService singletoneService2 = SingletoneService.getInstance();
        
        // 참조값이 같은 것을 확인
        System.out.println("singletoneService1 = " + singletoneService1);
        System.out.println("singletoneService2 = " + singletoneService2);

        // singletoneService1 == singletoneService2
        assertThat(singletoneService1).isSameAs(singletoneService2);
        // same 은 == 객체 인스턴스가 같은지 비교
        // equal는 = 자바 equal 비교
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);

        //2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
