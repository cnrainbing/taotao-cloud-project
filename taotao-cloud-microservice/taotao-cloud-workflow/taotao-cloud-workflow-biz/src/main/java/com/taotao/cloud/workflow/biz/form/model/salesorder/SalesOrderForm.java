package com.taotao.cloud.workflow.biz.form.model.salesorder;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 销售订单
 *
 */
@Data
public class SalesOrderForm {
    @NotBlank(message = "必填")
    @Schema(description = "流程主键")
    private String flowId;
    @NotBlank(message = "必填")
    @Schema(description = "流程标题")
    private String flowTitle;
    @NotNull(message = "必填")
    @Schema(description = "流程等级")
    private Integer flowUrgent;
    @NotBlank(message = "必填")
    @Schema(description = "流程单据")
    private String billNo;
    @Schema(description = "业务人员")
    private String salesman;
    @NotBlank(message = "必填")
    @Schema(description = "客户名称")
    private String customerName;
    @Schema(description = "联系人")
    private String contacts;
    @Schema(description = "联系电话")
    private String contactPhone;
    @Schema(description = "客户地址")
    private String customerAddres;
    @Schema(description = "发票编码")
    private String ticketNum;
    @NotBlank(message = "开票日期不能为空")
    @Schema(description = "开票日期")
    private Long ticketDate;
    @Schema(description = "发票类型")
    private String invoiceType;
    @Schema(description = "付款方式")
    private String paymentMethod;
    @Schema(description = "付款金额")
    private BigDecimal paymentMoney;
    @Schema(description = "销售日期")
    private Long salesDate;
    @Schema(description = "相关附件")
    private String fileJson;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "提交/保存 0-1")
    private String status;
    @Schema(description = "明细")
    List<SalesOrderEntryEntityInfoModel> entryList;
    @Schema(description = "候选人")
    private Map<String, List<String>> candidateList;
}