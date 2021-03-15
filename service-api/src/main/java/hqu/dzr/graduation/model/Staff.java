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
@TableName(value = "staff")
public class Staff implements Serializable {
    @TableId(value = "phone_number")
    private Long phoneNumber;
    private String staffName;
    private String staffPassword;
    private String addr;
    private String email;
    private String gender;
    private Integer age;
    private Integer status;
    private Double salary;
    private String staffId;
    private String remark;
}
