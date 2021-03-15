package hqu.dzr.graduation.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hqu.dzr.graduation.mapper.*;
import hqu.dzr.graduation.model.*;
import hqu.dzr.graduation.service.BaseService;
import hqu.dzr.graduation.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private ShopStaffDao shopStaffDao;

    @Autowired
    private ShopDao shopDao;

    /**
     * 通过电话号码和密码查询管理员
     * @param phoneNumber
     * @param password
     * @return
     */
    @Override
    public Manager searchManagerByUserNamePassword(String phoneNumber, String password) {
        if (!Utils.isPhoneNumberAndPasswordLegal(phoneNumber, password)) return null;

        Manager manager = managerDao.selectOne(new QueryWrapper<Manager>()
                .eq("phone_number", phoneNumber)
                .eq("manager_password", password));
        return manager;
    }

    /**
     * 通过管理员电话号码修改管理员信息
     * @param manager
     * @return
     */
    @Override
    public int updateManagerByPhoneNumber(Manager manager) {
        return managerDao.updateById(manager);
    }

    /**
     * 通过电话号码和密码查询用户
     * @param phoneNumber
     * @param password
     * @return
     */
    @Override
    public User searchUserByUserNamePassword(String phoneNumber, String password) {
        if (!Utils.isPhoneNumberAndPasswordLegal(phoneNumber, password)) return null;

        User user = userDao.selectOne(new QueryWrapper<User>()
                .eq("phone_number", phoneNumber)
                .eq("user_password", password));
        return user;
    }

    /**
     * 通过用户电话号码修改用户信息
     * @param user
     * @return
     */
    @Override
    public int updateUserByPhoneNumber(User user) {
        return userDao.updateById(user);
    }

    @Override
    public List<User> searchAllUser() {
        return userDao.selectList(null);
    }

    /**
     * 查询所有在线员工
     * @return
     */
    @Override
    public List<Staff> searchAllStaff() {
        return staffDao.selectList(null);
    }

    /**
     * 通过电话号码更新员工信息
     * @param staff
     * @return
     */
    @Override
    public int updateStaffByPhoneNumber(Staff staff) {
        return staffDao.updateById(staff);
    }

    /**
     * 通过员工Id删除员工
     * @param phoneNumber
     * @return
     */
    @Override
    public int deleteStaffByPhoneNumber(Long phoneNumber) {
        return staffDao.deleteById(phoneNumber);
    }

    /**
     * 通过姓名查询用户
     * @param staffName
     * @return
     */
    @Override
    public List<Staff> searchStaffByName(String staffName) {
        return staffDao.selectList(new QueryWrapper<Staff>().like("staff_name", staffName));
    }

    /**
     * 插入员工
     * @param staff
     * @return
     */
    @Override
    public int insertStaff(Staff staff) {
        return staffDao.insert(staff);
    }

    /**
     * 查询所有的商铺员工
     * @return
     */
    @Override
    public List<ShopStaff> searchAllShopStaff() {
        return shopStaffDao.selectList(null);
    }

    /**
     * 通过员工的名字进行模糊查询
     * @param shopStaffName
     * @return
     */
    @Override
    public List<ShopStaff> searchShopStaffByName(String shopStaffName, String shopStaffId) {
        return shopStaffDao.selectList(new QueryWrapper<ShopStaff>()
                .like("staff_name", shopStaffName)
                .eq("shop_id", shopStaffId));
    }

    /**
     * 查询对应商铺的员工
     * @param shopId
     * @return
     */
    @Override
    public List<ShopStaff> searchShopStaff(String shopId) {
        return shopStaffDao.selectList(new QueryWrapper<ShopStaff>().eq("shop_id", shopId));
    }

    /**
     * 通过员工的电话号码更新员工信息
     * @param shopStaff
     * @return
     */
    @Override
    public int updateShopStaffByPhoneNumber(ShopStaff shopStaff) {
        return shopStaffDao.updateById(shopStaff);
    }

    /**
     * 插入商铺员工
     * @param shopStaff
     * @return
     */
    @Override
    public int insertShopStaff(ShopStaff shopStaff) {
        return shopStaffDao.insert(shopStaff);
    }

    /**
     * 通过电话号码删除商铺员工
     * @param phoneNumber
     * @return
     */
    @Override
    public int deleteShopStaffByPhoneNumber(Long phoneNumber) {
        return shopStaffDao.deleteById(phoneNumber);
    }

    /**
     * 通过用户Id查询商铺
     * @param phoneNumber
     * @return
     */
    @Override
    public Shop searchShopByUserId(Long phoneNumber) {
        return shopDao.selectOne(new QueryWrapper<Shop>().eq("phone_number", phoneNumber));
    }

    /**
     * 查询所有商铺
     * @return
     */
    @Override
    public List<Shop> searchAllShops() {
        return shopDao.selectList(null);
    }

    private Shop mergeShop(Shop shop, Shop shop1) {
        if (!Utils.isEmptyOrHasSpaceString(shop1.getShopName())) shop.setShopName(shop1.getShopName());
        if (!Utils.isEmptyOrHasSpaceString(shop1.getAddr())) shop.setAddr(shop1.getAddr());
        if (shop1.getArea() != null) shop.setArea(shop1.getArea());
        if (!Utils.isEmptyOrHasSpaceString(shop1.getStatus())) shop.setStatus(shop1.getStatus());
        if (shop1.getRent() != null) shop.setRent(shop1.getRent());
        if (shop1.getPropertyId() != null) shop.setPropertyId(shop1.getPropertyId());
        if (!Utils.isEmptyOrHasSpaceString(shop1.getPropertyName())) shop.setPropertyName(shop1.getPropertyName());
        if (!Utils.isEmptyOrHasSpaceString(shop1.getPrincipalName())) shop.setPrincipalName(shop1.getPrincipalName());
        return shop;
    }

    /**
     * 更新商铺
     * @param shop
     * @return
     */
    @Override
    public int updateShopById(Shop shop) {
        Shop shop1 = searchShopByUserId(shop.getPhoneNumber());
        if (shop1 == null) return 0;
        shop = mergeShop(shop1, shop);
        return shopDao.updateById(shop);
    }

    /**
     * 插入商铺
     * @param shop
     * @return
     */
    @Override
    public int insertShop(Shop shop) {
        return shopDao.insert(shop);
    }

    /**
     * 删除商铺
     * @param shopId
     * @return
     */
    @Override
    public int deleteShop(Long shopId) {
        return shopDao.deleteById(shopId);
    }

    /**
     * 根据用户名称查询商铺
     * @param name
     * @return
     */
    @Override
    public List<Shop> searchShopByUserName(String name) {
        return shopDao.selectList(new QueryWrapper<Shop>().like("remark", name));
    }

    /**
     * 根据商铺名称查询商铺
     * @param shopName
     * @return
     */
    @Override
    public List<Shop> searchShopBuShopName(String shopName) {
        return shopDao.selectList(new QueryWrapper<Shop>().like("shop_name", shopName));
    }
}
