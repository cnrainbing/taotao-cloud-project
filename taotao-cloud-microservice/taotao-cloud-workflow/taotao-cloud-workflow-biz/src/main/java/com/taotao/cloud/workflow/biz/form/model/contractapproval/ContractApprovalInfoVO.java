package com.taotao.cloud.workflow.biz.form.model.contractapproval;


import java.math.BigDecimal;
import lombok.Data;

/**
 * 合同审批
 *
 */
@Data
public class ContractApprovalInfoVO {
    @Schema(description = "主键id")
    private String id;
    @Schema(description = "相关附件")
    private String fileJson;
    @Schema(description = "乙方单位")
    private String secondPartyUnit;
    @Schema(description = "合同分类")
    private String contractClass;
    @Schema(description = "紧急程度")
    private Integer flowUrgent;
    @Schema(description = "甲方单位")
    private String firstPartyUnit;
    @Schema(description = "结束时间")
    private Long endDate;
    @Schema(description = "合同类型")
    private String contractType;
    @Schema(description = "签约时间")
    private Long signingDate;
    @Schema(description = "甲方负责人")
    private String firstPartyPerson;
    @Schema(description = "收入金额")
    private BigDecimal incomeAmount;
    @Schema(description = "备注")
    private String description;
    @Schema(description = "填写人员")
    private String inputPerson;
    @Schema(description = "主要内容")
    private String primaryCoverage;
    @Schema(description = "流程标题")
    private String flowTitle;
    @Schema(description = "甲方联系方式")
    private String firstPartyContact;
    @Schema(description = "合同编码")
    private String contractId;
    @Schema(description = "乙方联系方式")
    private String secondPartyContact;
    @Schema(description = "合同名称")
    private String contractName;
    @Schema(description = "业务人员")
    private String businessPerson;
    @Schema(description = "流程主键")
    private String flowId;
    @Schema(description = "流程单据")
    private String billNo;
    @Schema(description = "乙方负责人")
    private String secondPartyPerson;
    @Schema(description = "开始时间")
    private Long startDate;
}