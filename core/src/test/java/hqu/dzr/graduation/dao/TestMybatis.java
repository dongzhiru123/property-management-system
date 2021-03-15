package hqu.dzr.graduation.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hqu.dzr.graduation.mapper.ManagerDao;
import hqu.dzr.graduation.model.Manager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
public class TestMybatis {

    @Autowired
    private ManagerDao managerDao;

    @Test
    void insert() {
        managerDao.insert(new Manager(15848148671L,
                "董智儒", "123456",
                "内蒙古呼和浩特", "ADMIN"));
    }

    @Test
    void query() {
        Manager manager = managerDao.selectOne(new QueryWrapper<Manager>()
                .eq("phone_number", 15754719736L)
                .eq("manager_password", "12345"));
        System.out.println(manager);
    }

    @Autowired
    private DataSource dataSource;

    @Test
    void testDataSource() {
        System.out.println(dataSource);
        System.out.println(dataSource.getClass().getName());
    }
}