package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    @Test
    void statefulService(){
        StatefulService ss1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService ss2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA: userA ordered 10000
        ss1.order("userA", 10000);
        // ThreadB: userB ordered 20000
        ss2.order("userB", 20000);
        // ThreadA: userA got order result
        int priceA = ss1.getPrice();

        Assertions.assertThat(priceA).isNotEqualTo(10000).isEqualTo(20000);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}