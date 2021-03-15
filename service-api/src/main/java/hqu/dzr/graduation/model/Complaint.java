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
@TableName("complaint")
public class Complaint implements Serializable {
    @TableId(value = "complaint_id", type = IdType.AUTO)
    private Long complaintId;
    private Long phoneNumber;
    private String complaintName;
    private String email;
    /**
     * 几种状态：
     * 1.待处理
     * 2.处理中
     * 3.投诉单处理完成
     */
    private String complaintStatus;
    private Long staffPhoneNumber;
    private String staffName;
    private String complaintContext;
    private String remark;
}
