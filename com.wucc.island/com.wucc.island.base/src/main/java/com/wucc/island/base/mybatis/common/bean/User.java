package com.wucc.island.base.mybatis.common.bean;

import lombok.*;

import java.util.Date;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-01 10:31
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    private Integer id;
    private String username;
    private String password;
    private String lastName;
    private String sex;
    private Date birthDate;

}
