package com.task.kamrul.controller;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Kamrul Hasan
 */
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    static Logger log = Logger.getLogger(LoginController.class);

    private String userId;
    private String cred;

    public LoginController() {
    }

    public void checkAuthorization() {
        log.info("In LoginController.checkAuthorization() method ..!");
        FacesContext context = FacesContext.getCurrentInstance();
        if (userId.equalsIgnoreCase("admin") && cred.equalsIgnoreCase("12345")) {
            FacesContext
                    .getCurrentInstance()
                    .getApplication()
                    .getNavigationHandler()
                    .handleNavigation(FacesContext.getCurrentInstance(),
                            "null", "/dashboard.xhtml?faces-redirect=true");

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentication Failed!", "Enter valid data."));
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCred() {
        return cred;
    }

    public void setCred(String cred) {
        this.cred = cred;
    }

}
