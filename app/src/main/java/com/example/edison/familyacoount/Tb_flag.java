package com.example.edison.familyacoount;

public class Tb_flag {
    private int _id;
    private String flag;
    public Tb_flag(){
        super();
    }
    public Tb_flag(int id, String flag){
        this._id = id;
        this.flag = flag;
    }

    public int getId(){
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFlag(){
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
