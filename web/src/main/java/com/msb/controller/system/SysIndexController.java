package com.msb.controller.system;

import com.msb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SysIndexController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/index")
    public String index(ModelMap map){



        //TODO 获取用户菜单
        return "index";
    }
}
