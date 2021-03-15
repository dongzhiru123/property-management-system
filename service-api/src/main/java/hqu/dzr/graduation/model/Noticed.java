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
@TableName(value = "noticed")
public class Noticed implements Serializable {
    @TableId(value = "noticed_id")
    private Long noticedId;
    private Long noticeId;
    private String phoneNumber;
    private String readed;
    private String remark;
}
