package hqu.dzr.graduation.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import hqu.dzr.graduation.model.*;
import hqu.dzr.graduation.service.BaseService;
import hqu.dzr.graduation.service.ComplaintService;
import hqu.dzr.graduation.service.NoticeService;
import hqu.dzr.graduation.service.RepairService;
import hqu.dzr.graduation.util.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Reference
    private BaseService baseService;

    @Reference
    private RepairService repairService;

    @Reference
    private ComplaintService complaintService;

    @Reference
    private NoticeService noticeService;

    /** --------------------------------首页------------------------------ */
    @GetMapping("/index.html")
    public String mainPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        System.out.println("loginUserName = " + user.getUserName());
        List<ShopStaff> staffs = baseService.searchShopStaff(user.getRemark());
        model.addAttribute("staffs", staffs);
        return "user/index";
    }

    @GetMapping("/modify")
    public String toModify() {
        return "user/modify";
    }

    @PostMapping("/modify")
    public String modify(HttpSession session, Model model,
                         @RequestParam("userPassword") String password,
                         @RequestParam("userAddr") String userAddr,
                         @RequestParam("gender") String gender,
                         @RequestParam("age") Integer age,
                         @RequestParam("userEmail") String email) {
        // 判断密码是否错误
        boolean isError = false;
        if (Utils.isEmptyOrHasSpaceString(password)) {
            isError = true;
            model.addAttribute("emptyPassword", "密码不可以为空且不能带有空格哦!");
        }
        if (Utils.isEmptyOrHasSpaceString(userAddr)) {
            isError = true;
            model.addAttribute("emptyAddr", "家庭住址不可以为空且不能带有空格哦!");
        }
        if (Utils.isEmptyOrHasSpaceString(gender)) {
            isError = true;
            model.addAttribute("emptyGender", "性别不可以为空且不能带有空格哦!");
        }
        if (Utils.isEmptyOrHasSpaceString(email)) {
            isError = true;
            model.addAttribute("emptyEmail", "邮箱不可以为空且不能带有空格哦!");
        }
        if (isError) {
            return "user/modify";
        }

        User user = (User) session.getAttribute("loginUser");

        user.setGender(gender);
        user.setAge(age);
        user.setUserPassword(password);
        user.setEmail(email);
        user.setAddr(userAddr);

        baseService.updateUserByPhoneNumber(user);
        session.setAttribute("loginUser", user);

        return "user/modify";
    }

    /** --------------------------------我的商铺------------------------------ */

    @GetMapping("/shopmodify.html")
    public String toShop(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        Shop shop = baseService.searchShopByUserId(user.getPhoneNumber());
        model.addAttribute("shop", shop);
        return "user/shopmodify";
    }

    @PostMapping("/modifyshop")
    public String toModifyShop(Shop shop) {
        System.out.println("shop = " + shop);
        baseService.updateShopById(shop);
        return "redirect:/user/shopmodify.html";
    }

    /** --------------------------------我的员工------------------------------ */

    @GetMapping("/staff.html")
    public String toStaff(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        List<ShopStaff> shopStaffs = baseService.searchShopStaff(user.getRemark());
        model.addAttribute("staffs", shopStaffs);
        return "user/staff";
    }

    // TODO:编辑员工信息，还未完成
    @GetMapping("/editstaff")
    public String toEditStaff(@RequestParam("id") Long id) {
        System.out.println("editstaff + " + id);
        return "redirect:/user/staff.html";
    }

    @GetMapping("/deletestaff")
    public String deleteStaff(@RequestParam("id") Long id) {
        baseService.deleteShopStaffByPhoneNumber(id);
        return "redirect:/user/staff.html";
    }

    @GetMapping("/searchstaff")
    public String searchStaff(HttpSession session, @RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        User user = (User) session.getAttribute("loginUser");
        List<ShopStaff> staffs = baseService.searchShopStaffByName(keyword, user.getRemark());
        model.addAttribute("staffs", staffs);
        return "user/staff";
    }

    // TODO:新增用户，还未完成
    @GetMapping("/addstaff")
    public String toAddStaff() {
        return "user/staff";
    }

    /** --------------------------------我的建议、投诉------------------------------ */

    @GetMapping("/complaint.html")
    public String toComplaint(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        List<Complaint> complaints = complaintService.searchComplaintsById(user.getPhoneNumber());
        model.addAttribute("complaints", complaints);
        return "user/complaint";
    }

    // TODO:新增投诉，还未完成
    @GetMapping("/addcomplaint")
    public String toAddComplaint() {
        return "user/complaint";
    }

    @GetMapping("/deletecomplaint")
    public String deleteComplaint(@RequestParam("id") Long id) {
        complaintService.deleteComplaintById(id);
        return "redirect:/user/complaint.html";
    }

    @GetMapping("/searchcomplaint")
    public String searchComplaintByText(HttpSession session, Model model, @RequestParam("keyword") String keyword) {
        User user = (User) session.getAttribute("loginUser");
        List<Complaint> complaints = complaintService.searchComplaintByIdAndText(keyword, user.getPhoneNumber());
        model.addAttribute("keyword", keyword);
        model.addAttribute("complaints", complaints);
        return "user/complaint";
    }

    /** --------------------------------公告------------------------------ */

    @GetMapping("/notice.html")
    public String toNotice(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        List<NoticeDetailed> detaileds = noticeService.searchNoticedByPhoneNumber(String.valueOf(user.getPhoneNumber()));
        model.addAttribute("detaileds", detaileds);
        System.out.println("detaileds = " + detaileds);
        return "user/notice";
    }

    @GetMapping("/editnotice")
    public String readed(HttpSession session, @RequestParam("id") Long id) {
        User user = (User) session.getAttribute("loginUser");
        noticeService.updateNoticedStatus(id, String.valueOf(user.getPhoneNumber()));
        return "redirect:/user/notice.html";
    }

    @GetMapping("/deletenotice")
    public String deleteNotice(HttpSession session, @RequestParam("id") Long id) {
        User user = (User) session.getAttribute("loginUser");
        noticeService.deleteNoticedByIdAndPhone(id, String.valueOf(user.getPhoneNumber()));
        return "redirect:/user/notice.html";
    }

    /** --------------------------------我的访客------------------------------ */

    @GetMapping("/visitor.html")
    public String toVisitor() {
        return "user/visitor";
    }

    /** --------------------------------我的报修------------------------------ */

    @GetMapping("/repairs.html")
    public String toRepairs(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        List<Repair> repairs = repairService.searchRepairsById(user.getPhoneNumber());
        model.addAttribute("repairs", repairs);
        return "user/repairs";
    }

    // TODO:新增报修，还未完成
    @GetMapping("/addrepair")
    public String toAddRepair() {
        return "user/repairs";
    }

    @GetMapping("/searchrepair")
    public String searchRepair(HttpSession session, Model model, @RequestParam("keyword") String keyword) {
        User user = (User) session.getAttribute("loginUser");
        List<Repair> repairs = repairService.searchRepairsByIdAndText(keyword, user.getPhoneNumber());
        model.addAttribute("keyword", keyword);
        model.addAttribute("repairs", repairs);
        return "user/repairs";
    }

    @GetMapping("/deleterepair")
    public String deleteRepair(@RequestParam("id") Long id) {
        repairService.deleteRepairById(id);
        return "redirect:/user/repairs.html";
    }

    /** --------------------------------我的车位------------------------------ */

    @GetMapping("/stall.html")
    public String toStall() {
        return "user/stall";
    }

    /** --------------------------------租赁------------------------------ */

    @GetMapping("/rent.html")
    public String toRent() {
        return "user/rent";
    }

    /** --------------------------------到期预警------------------------------ */

    @GetMapping("/expire.html")
    public String toExpire() {
        return "user/expire";
    }

    /** --------------------------------我的费用------------------------------ */

    // 水费
    @GetMapping("/cost1.html")
    public String toCost1() {
        return "user/cost1";
    }

    // 电费
    @GetMapping("/cost2.html")
    public String toCost2() {
        return "user/cost2";
    }

    // 公摊费
    @GetMapping("/cost3.html")
    public String toCost3() {
        return "user/cost3";
    }
}
