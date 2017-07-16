package org.qianrenxi.core.system.setting.service;

import java.util.Arrays;
import java.util.List;

import org.qianrenxi.core.system.setting.entity.DictItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSettingService {

	
	private static final String DICT_GROUP_EMAIL_IDENTIFIER = "email";
	private static final String DICT_ITEM_EMAIL_ENABLE = "email.enable";
	
	@Autowired
	DictItemService dictItemService;
	@Autowired
	DictGroupService dictGroupService;
	public void saveEmailSetting(DictItem[] dictItems){
		dictItemService.save(Arrays.asList(dictItems));
	}
	/**
	 * 查询发送邮件相关的配置
	 * @return {@link DictItem} 列表
	 */
	public List<DictItem> findEmailSetting(){
		return dictItemService.findByDictGroupIdentifier(DICT_GROUP_EMAIL_IDENTIFIER);
	}
	/**
	 * 查询邮件服务器配置是否启用
	 * @return {@link Boolean#TRUE} or {@link Boolean#FALSE}
	 */
	public Boolean isEnable(){
			DictItem emailEnableItem = dictItemService.findByKey(DICT_ITEM_EMAIL_ENABLE);
			return Boolean.valueOf(emailEnableItem.getValue());
	}
}
