package com.example.edison.familyacoount;

public class Tb_outaccount {
    private int _id;
    private String money;
    private String time;
    private String type;
    private String address;
    private String mark;

    public Tb_outaccount(){
        super();
    }
    public Tb_outaccount(int id, String money, String time, String type, String address, String mark){
        this._id = id;
        this.money = money;
        this.type = type;
        this.address = address;
        this.mark = mark;
    }
    public int getId(){
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getMoney(){
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getType(){
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getMark(){
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
