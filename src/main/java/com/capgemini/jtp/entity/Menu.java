package com.capgemini.jtp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.io.Serializable;
import java.util.List;


/**
 * create by: MmmLll_Shen
 * description:
 * create time: 11:18 2019/9/18
 */

public class Menu implements Serializable {

    private int nodeId;
    private String displayName;
    private String nodeURL;
    private String path;
    private Object component;
    private int displayOrder;
    private int parentNodeId;
    private List<Menu> children;
    private List<Role> roles;



    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonIgnore
    public String getNodeURL() {
        return nodeURL;
    }

    public void setNodeURL(String nodeURL) {
        this.nodeURL = nodeURL;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public Object getComponent() {
        return component;
    }

    public void setComponent(Object component) {
        this.component = component;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    //@JsonIgnore注释掉
    public int getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(int parentNodeId) {
        this.parentNodeId = parentNodeId;
    }
    
    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @JsonIgnore
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
