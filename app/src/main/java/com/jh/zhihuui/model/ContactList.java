package com.jh.zhihuui.model;

import java.io.Serializable;
import java.util.List;

public class ContactList implements Serializable {

    public int id;
    public int parentId;   // 有父类为具体数字， 无的话就是0；
    public int img;
    public String content;

    public List<ContactList> child;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<ContactList> getChild() {
        return child;
    }

    public void setChild(List<ContactList> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "ContactList{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", img=" + img +
                ", content='" + content + '\'' +
                ", child=" + child +
                '}';
    }
}
