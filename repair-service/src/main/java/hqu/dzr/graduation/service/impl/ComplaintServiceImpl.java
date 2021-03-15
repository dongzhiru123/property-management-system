package hqu.dzr.graduation.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hqu.dzr.graduation.mapper.ComplaintDao;
import hqu.dzr.graduation.model.Complaint;
import hqu.dzr.graduation.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintDao complaintDao;

    /**
     * 添加投诉意见单
     * @param complaint
     * @return
     */
    @Override
    public int addComplaint(Complaint complaint) {
        return complaintDao.insert(complaint);
    }

    /**
     * 更新投诉意见单
     * @param complaint
     * @return
     */
    @Override
    public int updateComplaint(Complaint complaint) {
        return complaintDao.updateById(complaint);
    }

    /**
     * 查询所有的投诉以及意见单
     * @return
     */
    @Override
    public List<Complaint> searchAllComplaint() {
        return complaintDao.selectList(null);
    }

    /**
     * 通过投诉人的名字进行模糊查询
     * @param name
     * @return
     */
    @Override
    public List<Complaint> searchByName(String name) {
        return complaintDao.selectList(new QueryWrapper<Complaint>()
                .like("complaint_name", name));
    }

    /**
     * 通过投诉意见单的Id更新投诉意见单的状态
     * @param id
     * @return
     */
    @Override
    public int updateComplaintStatusById(Long id) {
        Complaint complaint = complaintDao.selectById(id);
        complaint.setComplaintStatus("投诉处理完成");
        return complaintDao.updateById(complaint);
    }

    /**
     * 查询某个用户的投诉建议单
     * @param id
     * @return
     */
    @Override
    public List<Complaint> searchComplaintsById(Long id) {
        return complaintDao.selectList(new QueryWrapper<Complaint>()
                .eq("phone_number", id));
    }

    /**
     * 根据报修内容查询
     * @param text
     * @param id
     * @return
     */
    @Override
    public List<Complaint> searchComplaintByIdAndText(String text, Long id) {
        return complaintDao.selectList(new QueryWrapper<Complaint>()
                .eq("phone_number", id)
                .like("complaint_context", text));
    }

    /**
     * 通过投诉建议单的Id删除
     * @param id
     * @return
     */
    @Override
    public int deleteComplaintById(Long id) {
        return complaintDao.deleteById(id);
    }
}
