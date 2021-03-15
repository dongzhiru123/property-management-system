package hqu.dzr.graduation.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hqu.dzr.graduation.mapper.RepairDao;
import hqu.dzr.graduation.model.Repair;
import hqu.dzr.graduation.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairDao repairDao;

    /**
     * 添加报修单
     * @param repair
     * @return
     */
    @Override
    public int addRepair(Repair repair) {
        return repairDao.insert(repair);
    }

    /**
     * 修改报修单
     * @param repair
     * @return
     */
    @Override
    public int updateRepair(Repair repair) {
        return repairDao.updateById(repair);
    }

    /**
     * 查询所有报修单
     * @return
     */
    @Override
    public List<Repair> searchAllRepair() {
        return repairDao.selectList(null);
    }

    /**
     * 通过名字模糊查询
     * @param name
     * @return
     */
    @Override
    public List<Repair> searchByName(String name) {
        return repairDao.selectList(new QueryWrapper<Repair>()
                .like("repair_name", name));
    }

    /**
     * 修改报修单状态
     * @param id
     * @return
     */
    @Override
    public int updateRepairStatusById(Long id) {
        Repair repair = repairDao.selectById(id);
        repair.setRepairStatus("报修单处理完成");
        return repairDao.updateById(repair);
    }

    /**
     * 通过报修单的Id删除
     * @param id
     * @return
     */
    @Override
    public int deleteRepairById(Long id) {
        return repairDao.deleteById(id);
    }

    /**
     * 通过用户Id查询报修单
     * @param id
     * @return
     */
    @Override
    public List<Repair> searchRepairsById(Long id) {
        return repairDao.selectList(new QueryWrapper<Repair>()
                .eq("phone_number", id));
    }

    /**
     * 通过用户Id和报修内容查询报修单
     * @param id
     * @param text
     * @return
     */
    @Override
    public List<Repair> searchRepairsByIdAndText(String text, Long id) {
        return repairDao.selectList(new QueryWrapper<Repair>()
                .eq("phone_number", id)
                .like("repair_context", text));
    }
}
