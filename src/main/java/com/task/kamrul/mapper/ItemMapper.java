package com.task.kamrul.mapper;

import com.task.kamrul.bean.ItemBean;
import com.task.kamrul.entity.Item;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Md. Kamrul Hasan Shamim
 */
@Component
public class ItemMapper {

    static Logger log = Logger.getLogger(ItemMapper.class);

    public Item mapItemEntityFromBean(ItemBean itemBean) {
        log.info("In ItemMapper.mapItemEntityFromBean() method ..!");

        Item itemEntity = new Item();

        if (itemBean.getItemId() != 0) {
            itemEntity.setItemId(itemBean.getItemId());
        }
        itemEntity.setItemName(itemBean.getItemName());
        itemEntity.setItemDesc(itemBean.getItemDesc());
        if (itemBean.getItemDate() != null) {
            itemEntity.setItemDate(itemBean.getItemDate());
        }
        return itemEntity;
    }

    public ItemBean mapItemBeanFromEntity(Item itemEntity) {
        log.info("In ItemMapper.mapItemBeanFromEntity() method ..!");

        ItemBean itemBean = new ItemBean();

        if (itemEntity.getItemId() != 0) {
            itemBean.setItemId(itemEntity.getItemId());
        }
        itemBean.setItemName(itemEntity.getItemName());
        itemBean.setItemDesc(itemEntity.getItemDesc());
        if (itemEntity.getItemDate() != null) {
            itemBean.setItemDate(itemEntity.getItemDate());
        }
        return itemBean;
    }

}
