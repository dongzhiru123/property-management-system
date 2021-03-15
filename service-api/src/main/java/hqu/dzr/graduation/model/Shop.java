package hqu.dzr.graduation.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName(value = "shop")
public class Shop implements Serializable {
    @TableId(value = "shop_id", type = IdType.AUTO)
    private Long shopId;
    private String shopName;
    private Long phoneNumber;
    private String addr;
    private Double area;
    /**
     * 1、未租售
     * 2、已租
     * 3、已售
     */
    private String status;
    private Double rent;
    private Integer propertyId;
    private String propertyName;
    @TableField(value = "remark")
    private String principalName;
}
