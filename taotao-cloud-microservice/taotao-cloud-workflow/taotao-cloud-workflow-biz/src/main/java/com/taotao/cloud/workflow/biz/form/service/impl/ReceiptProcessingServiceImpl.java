package com.taotao.cloud.workflow.biz.form.service.impl;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;

import com.taotao.cloud.common.utils.common.JsonUtil;
import com.taotao.cloud.workflow.biz.engine.service.FlowTaskService;
import com.taotao.cloud.workflow.biz.form.entity.ReceiptProcessingEntity;
import com.taotao.cloud.workflow.biz.form.mapper.ReceiptProcessingMapper;
import com.taotao.cloud.workflow.biz.form.service.ReceiptProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 收文处理表
 *
 */
@Service
public class ReceiptProcessingServiceImpl extends ServiceImpl<ReceiptProcessingMapper, ReceiptProcessingEntity> implements ReceiptProcessingService {

    @Autowired
    private BillRuleService billRuleService;
    @Autowired
    private FlowTaskService flowTaskService;
    @Autowired
    private FileManageUtil fileManageUtil;

    @Override
    public ReceiptProcessingEntity getInfo(String id) {
        QueryWrapper<ReceiptProcessingEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ReceiptProcessingEntity::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    @DSTransactional
    public void save(String id, ReceiptProcessingEntity entity) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            this.save(entity);
            billRuleService.useBillNumber("WF_ReceiptProcessingNo");
            //添加附件
            List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
            fileManageUtil.createFile(data);
        } else {
            entity.setId(id);
            this.updateById(entity);
            //更新附件
            List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
            fileManageUtil.updateFile(data);
        }
        //流程信息
        ModelUtil.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(),entity);
    }

    @Override
    @DSTransactional
    public void submit(String id, ReceiptProcessingEntity entity, Map<String, List<String>> candidateList) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            this.save(entity);
            billRuleService.useBillNumber("WF_ReceiptProcessingNo");
            //添加附件
            List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
            fileManageUtil.createFile(data);
        } else {
            entity.setId(id);
            this.updateById(entity);
            //更新附件
            List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
            fileManageUtil.updateFile(data);
        }
        //流程信息
        ModelUtil.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity,null, candidateList);
    }

    @Override
    public void data(String id, String data) {
        ReceiptProcessingForm receiptProcessingForm = JsonUtil.getJsonToBean(data, ReceiptProcessingForm.class);
        ReceiptProcessingEntity entity = JsonUtil.getJsonToBean(receiptProcessingForm, ReceiptProcessingEntity.class);
        entity.setId(id);
        this.saveOrUpdate(entity);
    }
}