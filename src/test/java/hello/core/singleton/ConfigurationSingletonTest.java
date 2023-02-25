package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ConfigurationSingletonTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void configurationTest(){
        MemberServiceImpl ms = ac.getBean("memberService", MemberServiceImpl.class); // test method 호출하기 위해 구현 클래스 사용. (지양)
        OrderServiceImpl os = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository mr = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository mr1 = ms.getMemberRepository();
        MemberRepository mr2 = os.getMemberRepository();

        // mr == mr1 == mr2
        Assertions.assertThat(mr1).isSameAs(mr2);
        Assertions.assertThat(mr1).isSameAs(mr);
        Assertions.assertThat(mr2).isSameAs(mr);
    }
}
