package com.task.kamrul.service;

import com.task.kamrul.bean.BaseResponse;
import com.task.kamrul.bean.ItemBean;
import com.task.kamrul.dao.ItemDao;
import com.task.kamrul.entity.Item;
import com.task.kamrul.mapper.ItemMapper;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Md. Kamrul Hasan
 */
@Service
public class ItemServiceImpl {
    
    static Logger log = Logger.getLogger(ItemServiceImpl.class);
    
    @Autowired
    ItemDao itemDao;
    
    @Autowired
    ItemMapper itemMapper;
    
     public BaseResponse createOrEditItem(ItemBean itemBean) {
        log.info("In ItemServiceImpl.createOrEditItem() method ..!");
        BaseResponse response = new BaseResponse();
      

        try {
            /* Convert Item Bean to Entity*/
            Item itemEntity = itemMapper.mapItemEntityFromBean(itemBean);
            /* get ItemId from DB for add entity */
            if (itemEntity.getItemId() == 0) {
               int itemId=itemDao.getItemId();
                itemEntity.setItemId(itemId);
            }
            itemDao.createOrUpdate(itemEntity);
            
            response.setStatus(true);
        } catch (Exception ex) {           
            response.setStatus(false);
            response.getMessages().add(ex.getMessage());
            log.info(ex.getMessage());
        }
        return response;
    }

    public List<ItemBean> searchItem(ItemBean itemBean) {
        log.info("In ItemServiceImpl.searchItem() method ..!");

        List<ItemBean> itemBeanList = null;

        /* Convert Item Bean to Entity*/
        Item item = itemMapper.mapItemEntityFromBean(itemBean);

        /* Search Item List from dao*/
        List<Item> itemList = itemDao.searchItemByObject(item);
        if (itemList != null) {
            itemBeanList = new ArrayList<>();
            for (Item itemEntity : itemList) {
                /* Convert Item Bean From Entity*/
                ItemBean itemBeanObj = itemMapper.mapItemBeanFromEntity(itemEntity);
                /*added to list*/
                if (itemBeanObj != null) {
                    itemBeanList.add(itemBeanObj);
                }

            }
        }

        return itemBeanList;
    }

    
}
