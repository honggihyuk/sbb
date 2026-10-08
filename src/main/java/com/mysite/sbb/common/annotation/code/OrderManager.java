package com.mysite.sbb.common.annotation.code;


import jakarta.inject.Named;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Setter
@Service
public class OrderManager {

    @Autowired
    @Named("kia")
    private CarMaker maker;

    @Autowired
    public OrderManager(@Qualifier("kia") CarMaker maker) {
        this.maker = maker;
    }


    public void order() {
        Money money = new Money(1000);
        System.out.println("판매상(입금) : " + money.getAmount());
        Car car = maker.sell(money);
        System.out.println("판매상(인수) : " + car.getName());
    }
}
