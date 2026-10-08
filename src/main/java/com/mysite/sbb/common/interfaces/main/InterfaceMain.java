package com.mysite.sbb.common.interfaces.main;

import com.mysite.sbb.common.interfaces.code.OrderManager;
import com.mysite.sbb.common.interfaces.code.CarMaker;
import com.mysite.sbb.common.interfaces.code.HyundaiMaker;

public class InterfaceMain {

    public static void main(String[] args) {

        OrderManager orderManager = new OrderManager();

        CarMaker maker = new HyundaiMaker();
        orderManager.setMaker(maker);

        orderManager.order();


    }
}
