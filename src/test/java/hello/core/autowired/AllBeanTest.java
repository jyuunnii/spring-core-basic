package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

class AllBeanTest {
    @Test
    void findAllBean(){
      ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);
        int priceA = discountService.discount(member, 10000, "fixDiscountPolicy");
        Assertions.assertThat(priceA).isEqualTo(1000);
        int priceB = discountService.discount(member, 20000, "rateDiscountPolicy" );
        Assertions.assertThat(priceB).isEqualTo(2000);
    }

    static class DiscountService {
      private final Map<String, DiscountPolicy> map;
      private final List<DiscountPolicy> list;

      public DiscountService(Map<String, DiscountPolicy> map, List<DiscountPolicy> list) {
        System.out.println("map = " + map); // map = {fixDiscountPolicy=hello.core.discount.FixDiscountPolicy@38f116f6, rateDiscountPolicy=hello.core.discount.RateDiscountPolicy@5286c33a}
        System.out.println("list = " + list); // list = [hello.core.discount.FixDiscountPolicy@38f116f6, hello.core.discount.RateDiscountPolicy@5286c33a]
        this.map = map;
        this.list = list;
      }

        public int discount(Member member, int price, String discountPolicyCode) {
            DiscountPolicy discountPolicy = map.get(discountPolicyCode);
            return discountPolicy.discount(member, price);
        }
    }

}
