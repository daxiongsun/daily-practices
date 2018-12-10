package com.ngsky.tools.domain;

import javax.persistence.*;
/**
 * <dl>
 * <dt>User</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 10/30/2018 10:32 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
@Entity
@Table(name = "user", schema = "user", catalog = "")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "mname")
    private String mname;
    @Column(name = "mpath")
    private String mpath;
    @Column(name = "mdesc")
    private String mdesc;

    public User(String mname, String mpath, String mdesc) {
        this.mname = mname;
        this.mpath = mpath;
        this.mdesc = mdesc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMpath() {
        return mpath;
    }

    public void setMpath(String mpath) {
        this.mpath = mpath;
    }

    public String getMdesc() {
        return mdesc;
    }

    public void setMdesc(String mdesc) {
        this.mdesc = mdesc;
    }
}
