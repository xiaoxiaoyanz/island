package com.wucc.island.entity.helloworld;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-01-16 11:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "helloworld", description = "helloworld Entity")
@TableName("hello_world")
public class HelloWorld implements Serializable {

    private static final long serialVersionUID = 6657954049311281252L;

    @TableId("ID")
    private Long id;

    @TableField("NAME")
    private String name;

    @TableField("PASSWORD")
    private String password;

    @TableField("DESCRIPTION")
    private String description;


}
