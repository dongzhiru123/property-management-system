package hqu.dzr.graduation.service;

import hqu.dzr.graduation.model.*;

import java.util.List;


/**
 * 基本service
 * 比如：查询、修改管理员等
 */
public interface BaseService {

    Manager searchManagerByUserNamePassword(String phoneNumber, String password);

    int updateManagerByPhoneNumber(Manager manager);

    User searchUserByUserNamePassword(String phoneNumber, String password);

    int updateUserByPhoneNumber(User user);

    List<User> searchAllUser();

    // 物业员工
    List<Staff> searchAllStaff();

    int updateStaffByPhoneNumber(Staff staff);

    int deleteStaffByPhoneNumber(Long phoneNumber);

    List<Staff> searchStaffByName(String staffName);

    int insertStaff(Staff staff);

    // 商铺员工
    List<ShopStaff> searchAllShopStaff();

    List<ShopStaff> searchShopStaffByName(String shopStaffName, String shopStaffId);

    List<ShopStaff> searchShopStaff(String shopId);

    int updateShopStaffByPhoneNumber(ShopStaff shopStaff);

    int insertShopStaff(ShopStaff shopStaff);

    int deleteShopStaffByPhoneNumber(Long phoneNumber);

    // 商铺相关
    Shop searchShopByUserId(Long phoneNumber);

    List<Shop> searchAllShops();

    int updateShopById(Shop shop);

    int insertShop(Shop shop);

    int deleteShop(Long shopId);

    List<Shop> searchShopByUserName(String name);

    List<Shop> searchShopBuShopName(String shopName);
}
