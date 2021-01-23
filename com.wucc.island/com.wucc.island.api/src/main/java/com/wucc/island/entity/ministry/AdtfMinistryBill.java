package com.wucc.island.entity.ministry;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 转移支付部级单据
 * </p>
 *
 * @author wucc
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AdtfMinistryBill对象", description="转移支付部级单据")
@TableName("adtf_ministry_bill")
@NoArgsConstructor
@AllArgsConstructor
public class AdtfMinistryBill implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId("ID")
    private Long id;

    @ApiModelProperty(value = "版本号",example = "0")
    @TableField("VERSION")
    @Version
    private Integer version;

    @ApiModelProperty(value = "单据编号")
    @TableField("CODE")
    private String code;

    @ApiModelProperty(value = "单据类型")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "区划")
    @TableField("RG_CODE")
    private String rgCode;

    @ApiModelProperty(value = "年度")
    @TableField("SET_YEAR")
    private String setYear;

    @ApiModelProperty(value = "单位ID")
    @TableField("ORG_ID")
    private String orgId;

    @ApiModelProperty(value = "单位编码")
    @TableField("ORG_CODE")
    private String orgCode;

    @ApiModelProperty(value = "单位名称")
    @TableField("ORG_NAME")
    private String orgName;

    @ApiModelProperty(value = "流程状 -2初始状态 -1草稿 0待送审 1流程中 2完成")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "生效状态 0未生效 1已生效")
    @TableField("IS_EFFECT")
    private String isEffect;

    @ApiModelProperty(value = "单位类型编码")
    @TableField("ORG_TYPE_CODE")
    private String orgTypeCode;

    @ApiModelProperty(value = "单位类型名称")
    @TableField("ORG_TYPE_NAME")
    private String orgTypeName;

    @ApiModelProperty(value = "中央文号编码")
    @TableField("CENTRAL_DOC_NO_CODE")
    private String centralDocNoCode;

    @ApiModelProperty(value = "中央文号")
    @TableField("CENTRAL_DOC_NO_NAME")
    private String centralDocNoName;

    @ApiModelProperty(value = "文件标题")
    @TableField("DOC_TITLE")
    private String docTitle;

    @ApiModelProperty(value = "拨付方式编码")
    @TableField("APPROPRIATE_TYPE_CODE")
    private String appropriateTypeCode;

    @ApiModelProperty(value = "拨付方式名称")
    @TableField("APPROPRIATE_TYPE_NAME")
    private String appropriateTypeName;

    @ApiModelProperty(value = "主题词")
    @TableField("DOC_THEME_WORD")
    private String docThemeWord;

    @ApiModelProperty(value = "中央下达金额",example = "0.0")
    @TableField("RELEASE_AMOUNT")
    private BigDecimal releaseAmount;

    @ApiModelProperty(value = "发文日期")
    @TableField("RELEASE_DOC_DATE")
    private Date releaseDocDate;

    @ApiModelProperty(value = "发文单位编码")
    @TableField("RELEASE_DOC_ORG_CODE")
    private String releaseDocOrgCode;

    @ApiModelProperty(value = "发文单位名称")
    @TableField("RELEASE_DOC_ORG_NAME")
    private String releaseDocOrgName;

    @ApiModelProperty(value = "内容简介")
    @TableField("DOC_CONTENT_INTRODUCTION")
    private String docContentIntroduction;

    @ApiModelProperty(value = "审核人员ID")
    @TableField("AUDITOR_ID")
    private String auditorId;

    @ApiModelProperty(value = "审核人员名称")
    @TableField("AUDITOR")
    private String auditor;

    @ApiModelProperty(value = "审核时间")
    @TableField("AUDITORTIME")
    private Date auditortime;

    @ApiModelProperty(value = "制单人ID")
    @TableField("CREATOR_ID")
    private String creatorId;

    @ApiModelProperty(value = "编制人")
    @TableField("CREATOR")
    private String creator;

    @ApiModelProperty(value = "制单日期")
    @TableField("CREATIONTIME")
    private Date creationtime;

    @ApiModelProperty(value = "最后修改人ID")
    @TableField("MODIFIER_ID")
    private String modifierId;

    @ApiModelProperty(value = "最后修改人")
    @TableField("MODIFIER")
    private String modifier;

    @ApiModelProperty(value = "最后修改时间")
    @TableField("MODIFIEDTIME")
    private Date modifiedtime;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "系统类型：adtf全国转移支付，gui贵州转移支付")
    @TableField("SYS_TYPE")
    private String sysType;

    @ApiModelProperty(value = "公密号编码")
    @TableField("PUBLIC_NO_CODE")
    private String publicNoCode;

    @ApiModelProperty(value = "公密号")
    @TableField("PUBLIC_NO_NAME")
    private String publicNoName;

    @ApiModelProperty(value = "经费年度")
    @TableField("FUNDS_YEAR")
    private Integer fundsYear;

    @ApiModelProperty(value = "本次下达金额",example = "0.0")
    @TableField("RELEASETHIS_AMOUNT")
    private BigDecimal releasethisAmount;

    @ApiModelProperty(value = "中央已下达金额",example = "0.0")
    @TableField("RELEASED_AMOUNT")
    private BigDecimal releasedAmount;


}
