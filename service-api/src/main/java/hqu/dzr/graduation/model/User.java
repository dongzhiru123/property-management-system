package hqu.dzr.graduation.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User implements Serializable {
    @TableId(value = "phone_number")
    private Long phoneNumber;
    private String userName;
    private String userPassword;
    private String addr;
    private String role;
    private String email;
    private String gender;
    private int age;
    private String userId;
    private String remark;
}
