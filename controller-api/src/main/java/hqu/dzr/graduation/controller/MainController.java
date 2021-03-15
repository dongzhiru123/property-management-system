package hqu.dzr.graduation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import hqu.dzr.graduation.model.Manager;
import hqu.dzr.graduation.model.User;
import hqu.dzr.graduation.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Reference
    private BaseService baseService;

    @GetMapping(value = {"/"})
    public String toLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("userName") String iphoneNumber,
                          @RequestParam("passWord") String passWord,
                          HttpSession session,
                          Model model) {

        Manager manager = baseService.searchManagerByUserNamePassword(iphoneNumber, passWord);
        System.out.println("manager= " + manager);
        if (manager != null) {
            session.setAttribute("loginUser", manager);
            return "redirect:/manager/index.html";
        }
        User user = baseService.searchUserByUserNamePassword(iphoneNumber, passWord);
        if (user != null) {
            session.setAttribute("loginUser", user);
            return "redirect:/user/index.html";
        }
        else {
            model.addAttribute("msg", "账号或密码错误!!!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
