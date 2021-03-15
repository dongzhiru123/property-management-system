package hqu.dzr.graduation.controller.manager;

import com.alibaba.dubbo.config.annotation.Reference;
import hqu.dzr.graduation.model.*;
import hqu.dzr.graduation.service.BaseService;
import hqu.dzr.graduation.service.ComplaintService;
import hqu.dzr.graduation.service.RepairService;
import hqu.dzr.graduation.util.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Reference
    private BaseService baseService;

    @Reference
    private RepairService repairService;

    @Reference
    private ComplaintService complaintService;

    /** --------------------------------管理员相关------------------------------ */

    @GetMapping("/index.html")
    public String toMainPage(HttpSession session, Model model) {
        Manager manager = (Manager) session.getAttribute("loginUser");
        System.out.println("loginUserName = " + manager.getManagerName());
        List<Staff> staffs = baseService.searchAllStaff();
        model.addAttribute("staffs", staffs);
        return "manager/index";
    }

    @GetMapping("/modify")
    public String toModify() {
        return "manager/modify";
    }

    @PostMapping("/modify")
    public String modify(HttpSession session, Model model,
                         @RequestParam("managerPassword") String password,
                         @RequestParam("managerAddr") String managerAddr) {
        // 判断密码是否错误
        boolean isError = false;
        if (Utils.isEmptyOrHasSpaceString(password)) {
            isError = true;
            model.addAttribute("emptyPassword", "密码不可以为空且不能带有空格哦!");
        }
        if (Utils.isEmptyOrHasSpaceString(managerAddr)) {
            isError = true;
            model.addAttribute("emptyAddr", "家庭住址不可以为空且不能带有空格哦!");
        }
        if (isError) {
            return "manager/modify";
        }

        Manager manager = (Manager) session.getAttribute("loginUser");
        manager.setManagerPassword(password);
        manager.setManagerAddr(managerAddr);

        baseService.updateManagerByPhoneNumber(manager);
        session.setAttribute("loginUser", manager);

        return "manager/modify";
    }

    /** --------------------------------员工相关------------------------------ */

    @GetMapping("/staff.html")
    public String toStaffManage(Model model) {
        List<Staff> staffs = baseService.searchAllStaff();
        model.addAttribute("staffs", staffs);
        return "manager/staff";
    }

    //TODO:还没有完成
    // 添加员工
    @GetMapping("/addstaff")
    public String toAddStaff() {
        return "manager/staff";
    }

    @GetMapping("/searchstaff")
    public String searchStaff(@RequestParam("keyword") String keyword, Model model) {
        System.out.println("keyword" + keyword);
        model.addAttribute("keyword", keyword);
        List<Staff> staffs = baseService.searchStaffByName(keyword);
        model.addAttribute("staffs", staffs);
        return "manager/staff";
    }

    //TODO:还没有完成
    // 编辑员工
    @GetMapping("/editstaff")
    public String toModifyStaff(@RequestParam("id") Long id) {
        System.out.println("id = " + id);
        return "manager/staff";
    }

    @GetMapping("/deletestaff")
    public String deleteStaff(@RequestParam("id") Long id) {
        baseService.deleteStaffByPhoneNumber(id);
        return "redirect:/manager/staff.html";
    }

    /** --------------------------------报修相关------------------------------ */

    @GetMapping("/repairs.html")
    public String toRepairs(Model model) {
        List<Repair> repairs = repairService.searchAllRepair();
        model.addAttribute("repairs", repairs);
        return "manager/repairs";
    }

    //TODO: 选择报修单处理员工,将员工状态设置为进行为。并且将报修的id加入到员工的remark中
    //  未做
    @GetMapping("/editrepair")
    public String toSelectStaffRepair(@RequestParam("id") Long id) {
        System.out.println("id = " + id);
        return "redirect:/manager/repairs.html";
    }

    @GetMapping("/repairstatus")
    public String modifyRepairStatus(@RequestParam("id") Long id) {
        System.out.println("id = " + id);
        repairService.updateRepairStatusById(id);
        return "redirect:/manager/repairs.html";
    }

    @GetMapping("/searchrepair")
    public String searchRepair(@RequestParam("keyword") String keyword, Model model) {
        System.out.println("keyword" + keyword);
        model.addAttribute("keyword", keyword);
        List<Repair> repairs = repairService.searchByName(keyword);
        model.addAttribute("repairs", repairs);
        return "manager/repairs";
    }

    /** --------------------------------投诉相关------------------------------ */

    @GetMapping("/complaint.html")
    public String toComplaint(Model model) {
        List<Complaint> complaints = complaintService.searchAllComplaint();
        model.addAttribute("complaints", complaints);
        return "manager/complaint";
    }

    //TODO: 选择投诉建议单处理员工
    //  未做
    @GetMapping("/editcomplaint")
    public String toSelectStaffComplaint(@RequestParam("id") Long id) {
        System.out.println("id = " + id);
        return "redirect:/manager/complaint.html";
    }

    @GetMapping("/complaintstatus")
    public String modifyComplaintStatus(@RequestParam("id") Long id) {
        System.out.println("id = " + id);
        complaintService.updateComplaintStatusById(id);
        return "redirect:/manager/complaint.html";
    }

    @GetMapping("/searchcomplaint")
    public String searchComplaint(@RequestParam("keyword") String keyword, Model model) {
        System.out.println("keyword" + keyword);
        model.addAttribute("keyword", keyword);
        List<Complaint> complaints = complaintService.searchByName(keyword);
        model.addAttribute("complaints", complaints);
        return "manager/complaint";
    }

    /** --------------------------------公告相关------------------------------ */

    @GetMapping("/notice.html")
    public String toNotice() {
        return "manager/notice";
    }

    /** --------------------------------租赁相关------------------------------ */

    @GetMapping("/notrent.html")
    public String toNotRent() {
        return "manager/notrent";
    }

    @GetMapping("/rent.html")
    public String toRent() {
        return "manager/rent";
    }

    /** --------------------------------访客记录------------------------------ */

    @GetMapping("/visitor.html")
    public String toVisitor() {
        return "manager/visitor";
    }

    /** --------------------------------商铺相关------------------------------ */

    @GetMapping("/shop.html")
    public String toShop() {
        return "manager/shop";
    }

    /** --------------------------------车位相关------------------------------ */

    @GetMapping("/stall.html")
    public String toStall() {
        return "manager/stall";
    }

    /** --------------------------------费用记录------------------------------ */

    @GetMapping("/cost.html")
    public String toCost() {
        return "manager/cost";
    }

    /** --------------------------------到期预警------------------------------ */

    @GetMapping("/expire.html")
    public String toExpire() {
        return "manager/expire";
    }

    /** --------------------------------操作日志------------------------------ */

    @GetMapping("/log.html")
    public String toLog() {
        return "manager/log";
    }
}
