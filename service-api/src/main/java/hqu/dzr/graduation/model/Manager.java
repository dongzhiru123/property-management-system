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
@TableName(value = "manager")
public class Manager implements Serializable {
    @TableId(value = "phone_number")
    private Long phoneNumber;
    private String managerName;
    private String managerPassword;
    private String managerAddr;
    private String role;
}
