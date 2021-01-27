package com.task.kamrul.controller;

import com.task.kamrul.bean.BaseResponse;
import com.task.kamrul.bean.ItemBean;
import com.task.kamrul.service.ItemServiceImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author Md. Kamrul Hasan
 */
@ManagedBean
@ViewScoped
public class ItemController implements Serializable {

    private ItemBean itemBean;
    private ItemBean itemSrcBean;
    private List<ItemBean> itemBeanList;

    @ManagedProperty(value = "#{itemServiceImpl}")
    private ItemServiceImpl itemServiceImpl;

    public ItemController() {
        
         FacesContext context = FacesContext.getCurrentInstance();
        /*For Simple Edit page, get the value*/
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        String view_id = viewRoot.getViewId();
        switch (view_id) {
            case "/item/EditItem.xhtml":
                itemBean = (ItemBean) context.getExternalContext().getSessionMap().get("itemBean");
                break;
        }
    }
    
   

    public void searchItem() {
        FacesContext context = FacesContext.getCurrentInstance();
        itemBeanList = itemServiceImpl.searchItem(itemSrcBean);

        if (itemBeanList != null) {
            if (itemBeanList.isEmpty()) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data not found.!", ""));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data not found.!", ""));
        }
    }

    public void putIteamForEdit(ItemBean itemBeanObj) {
        FacesContext context = FacesContext.getCurrentInstance();
        this.itemBean = itemBeanObj;
        /*For Simple Edit redirect to page and value*/
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        String view_id = viewRoot.getViewId();
        switch (view_id) {
            case "/dashboard.xhtml":
                context.getExternalContext().getSessionMap().put("itemBean", itemBean);
                FacesContext
                        .getCurrentInstance()
                        .getApplication()
                        .getNavigationHandler()
                        .handleNavigation(FacesContext.getCurrentInstance(),
                                "null", "/item/EditItem.xhtml?faces-redirect=true");
                break;
        }
    }

     public void redirectToAddIteam() {
        /*For Simple Edit redirect to page and value*/
         FacesContext
                        .getCurrentInstance()
                        .getApplication()
                        .getNavigationHandler()
                        .handleNavigation(FacesContext.getCurrentInstance(),
                                "null", "/item/AddItem.xhtml?faces-redirect=true");
    }
    public void createOrEditItem(int buttonOption) {
        /* buttonOption means  1=Add, 2=Edit, 3=CreateOrUpdate*/
        FacesContext context = FacesContext.getCurrentInstance();

        BaseResponse response = itemServiceImpl.createOrEditItem(itemBean);

        if (response.isStatus()) {
            /* refresh clean the screen value*/
            refresh();
            /* Set the sucess message*/
            if (buttonOption == 1) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save SuccessFully", ""));
            } else if (buttonOption == 2) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Edit SuccessFully", ""));
            } else if (buttonOption == 3) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add/Edit SuccessFully", ""));
            }
            /* After Add and Edit redirect to search page */
            if (buttonOption == 1 || buttonOption == 2) {
                FacesContext
                        .getCurrentInstance()
                        .getApplication()
                        .getNavigationHandler()
                        .handleNavigation(FacesContext.getCurrentInstance(),
                                "null", "/dashboard.xhtml?faces-redirect=true");
            }
        } else {
            /* Set the faild message*/
            if (buttonOption == 1) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Save Faield.!", ""));
            } else if (buttonOption == 2) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Edit Faield.!", ""));
            } else if (buttonOption == 3) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Add/Edit Faield.!", ""));
            }
            for (String msg : response.getMessages()) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, ""));
            }
        }
    }

    public void refresh() {
        itemBean = new ItemBean();
    }

    public ItemBean getItemBean() {
        if (itemBean == null) {
            itemBean = new ItemBean();
        }
        return itemBean;
    }

    public void setItemBean(ItemBean itemBean) {
        this.itemBean = itemBean;
    }

    public ItemBean getItemSrcBean() {
        if (itemSrcBean == null) {
            itemSrcBean = new ItemBean();
        }
        return itemSrcBean;
    }

    public void setItemSrcBean(ItemBean itemSrcBean) {
        this.itemSrcBean = itemSrcBean;
    }

    public List<ItemBean> getItemBeanList() {
        if (itemBeanList == null) {
            itemBeanList = new ArrayList<>();
        }
        return itemBeanList;
    }

    public void setItemBeanList(List<ItemBean> itemBeanList) {
        this.itemBeanList = itemBeanList;
    }

    public ItemServiceImpl getItemServiceImpl() {
        if (itemServiceImpl == null) {
            itemServiceImpl = new ItemServiceImpl();
        }
        return itemServiceImpl;
    }

    public void setItemServiceImpl(ItemServiceImpl itemServiceImpl) {
        this.itemServiceImpl = itemServiceImpl;
    }

}
