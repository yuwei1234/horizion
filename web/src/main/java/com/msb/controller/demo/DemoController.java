package com.msb.controller.demo;

import com.msb.domain.User;
import com.msb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/user")
    public String demo(HttpServletRequest request){
        User user = new User();
        user.setUserName("yuwei");
        user.setPhone("11111113");
        user.setBranchCode("54512121");
        iUserService.save(user);
        return "hello";
    }
}
