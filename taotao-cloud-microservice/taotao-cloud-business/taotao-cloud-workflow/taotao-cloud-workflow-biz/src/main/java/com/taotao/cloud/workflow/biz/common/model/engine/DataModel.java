package com.taotao.cloud.workflow.biz.common.model.engine;
import com.taotao.cloud.workflow.biz.common.database.model.entity.DbLinkEntity;
import com.taotao.cloud.workflow.biz.common.model.visiual.TableModel;
import com.taotao.cloud.workflow.biz.common.model.visiual.fields.FieLdsModel;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataModel {

	private Map<String, Object> dataNewMap;
	private List<FieLdsModel> fieLdsModelList;
	private List<TableModel> tableModelList;
	private String mainId;
	private DbLinkEntity link;
	private Boolean convert;

}