package hello.core.common;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// myLogger에 가짜 프록시클래스를 만들어서 주입(provider 주입하듯) : 그래서 돌리면 [myLogger = class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$8912c8a0] 이런 결과 나옴
public class MyLogger {

    private String uuid;
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    private String requestURL;
    public void log(String message) {
        System.out.println("[" + uuid + "] " + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create :" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close :" + this);
    }
}
