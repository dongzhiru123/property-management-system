package hqu.dzr.graduation.model;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName(value = "repair")
public class Repair implements Serializable {
    @TableId(value = "repair_id", type = IdType.AUTO)
    private Long repairId;
    private Long phoneNumber;
    private String repairName;
    private String email;
    /**
     * 几种状态：
     * 1.待处理
     * 2.处理中
     * 3.报修单处理完成
     */
    private String repairStatus;
    private Long staffPhoneNumber;
    private String staffName;
    private String repairContext;
    private String remark;
}
