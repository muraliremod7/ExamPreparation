package com.sreedharcce.prabhu.exampreparation.model;

/**
 * Created by Prabhu on 13-10-2017.
 */

public class SpinnerModelclass {

    public SpinnerModelclass(){

    }

    public SpinnerModelclass(String bid, String bacr, String bname) {
        this.bid = bid;
        this.bacr = bacr;
        this.bname = bname;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBacr() {
        return bacr;
    }

    public void setBacr(String bacr) {
        this.bacr = bacr;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    private String bid, bacr, bname;
}
