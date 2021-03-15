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
@TableName(value = "notice")
public class Notice implements Serializable {
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Long noticeId;
    private String noticeTitle;
    private String date;
    private String context;
    private String remark;
}
