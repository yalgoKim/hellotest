package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody // 화면이 없이 문자 반환하고파 reponsebody를 사용
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString(); // 고객이 어떤 url로 요청했는지 알 수 있음

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL); // 그다음에 담아서

        myLogger.log("controller test"); // 로그를 찍음 (이때 이미 uuid와 URL은 만들어져있음)
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";

        // 각각 요청마다 각각 객체를 따로만들어주는게 핵심임
    }

}
