package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// DI Container
public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImpl(memoryRepository());
    }

    // Dependency Injection
    public MemberRepository memoryRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memoryRepository(), discountPolicy());
    }

    // Dependency Injection
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
