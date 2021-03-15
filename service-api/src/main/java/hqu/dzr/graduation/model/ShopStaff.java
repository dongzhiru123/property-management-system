package hqu.dzr.graduation.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "shop_staff")
public class ShopStaff implements Serializable {
    @TableId(value = "phone_number")
    private Long phoneNumber;
    private String staffName;
    private Long shopId;
    private String shopName;
    private String addr;
    private String email;
    private String gender;
    private Integer age;
    private Integer status;
    private Double salary;
    private String staffId;
    private String remark;
}
