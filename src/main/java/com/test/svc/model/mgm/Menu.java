package com.test.svc.model.mgm;

import java.util.Date;

public class Menu {
    private Integer menuId;

    private String menuName;

    private String menuLogo;

    private String menuUrl;

    private Integer menuSeq;

    private String recSt;

    private Date recCrtTs;

    private Date recUpdTs;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuLogo() {
        return menuLogo;
    }

    public void setMenuLogo(String menuLogo) {
        this.menuLogo = menuLogo == null ? null : menuLogo.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public Integer getMenuSeq() {
        return menuSeq;
    }

    public void setMenuSeq(Integer menuSeq) {
        this.menuSeq = menuSeq;
    }

    public String getRecSt() {
        return recSt;
    }

    public void setRecSt(String recSt) {
        this.recSt = recSt == null ? null : recSt.trim();
    }

    public Date getRecCrtTs() {
        return recCrtTs;
    }

    public void setRecCrtTs(Date recCrtTs) {
        this.recCrtTs = recCrtTs;
    }

    public Date getRecUpdTs() {
        return recUpdTs;
    }

    public void setRecUpdTs(Date recUpdTs) {
        this.recUpdTs = recUpdTs;
    }
}