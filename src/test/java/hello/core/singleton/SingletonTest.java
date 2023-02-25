package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class SingletonTest {
    @Test
    @DisplayName("Pure DI Container - no Spring")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        MemberService ms1 = appConfig.memberService();
        MemberService ms2 = appConfig.memberService();

        // appConfig 에서 memberService 를 호출할 때마다 jvm에 객체를 새로 생성함을 확인.
        Assertions.assertThat(ms1).isNotSameAs(ms2);
    }

    @Test
    @DisplayName("Use SingletonService")
    void singletonService(){
        SingletonService ss1 = SingletonService.getInstance();
        SingletonService ss2 = SingletonService.getInstance();

        // compare instance
        Assertions.assertThat(ss1).isSameAs(ss2);
    }

    @Test
    @DisplayName("Spring Container")
    void springContainer(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService ms1 = ac.getBean("memberService", MemberService.class);
        MemberService ms2 = ac.getBean("memberService", MemberService.class);

        Assertions.assertThat(ms1).isSameAs( ms2);
    }
}
