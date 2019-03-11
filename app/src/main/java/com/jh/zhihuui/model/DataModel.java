package com.jh.zhihuui.model;



import com.jh.zhihuui.R;

import java.util.ArrayList;
import java.util.List;

public class DataModel {

    public static List<ContactList> getData() {

        List<ContactList> contactLists = new ArrayList<>();

        // 第一个数据，无父目录，无子目录
        ContactList contactList0 = new ContactList();
        contactList0.id = 1;
        contactList0.img = R.drawable.icon_portrait;
        contactList0.parentId = 0;
        contactList0.content = "根目录0";

        // 第二个数据，父目录，有2个子目录
        ContactList contactList1 = new ContactList();
        contactList1.id = 2;
        contactList1.img = R.drawable.icon_organization;
        contactList1.parentId = 1;
        contactList1.content = "根目录1";
        contactList1.child = new ArrayList<>();

        // 添加父目录的2组数据；
        ContactList contactList11 = new ContactList();
        contactList11.id = 3;
        contactList11.img = R.drawable.icon_portrait;
        contactList11.parentId = 0;
        contactList11.content = "子目录1";

        ContactList contactList12 = new ContactList();
        contactList12.id = 4;
        contactList12.img = R.drawable.icon_organization;
        contactList12.parentId = 1;
        contactList12.content = "子目录2";
        contactList12.child = new ArrayList<>();

        ContactList contactList123 = new ContactList();
        contactList123.id = 4;
        contactList123.img = R.drawable.icon_portrait;
        contactList123.parentId = 0;
        contactList123.content = "子目录2-3";
        contactList123.child = new ArrayList<>();

        ContactList contactList2 = new ContactList();
        contactList2.id = 1;
        contactList2.img = R.drawable.icon_portrait;
        contactList2.parentId = 0;
        contactList2.content = "根目录2";

        contactList1.child.add(contactList11);
        contactList1.child.add(contactList12);
        contactList12.child.add(contactList123);

        contactLists.add(contactList0);
        contactLists.add(contactList1);
        contactLists.add(contactList2);
        return contactLists;
    }
}
