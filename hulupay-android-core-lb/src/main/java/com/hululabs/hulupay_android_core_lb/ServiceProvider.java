package com.hululabs.hulupay_android_core_lb;

public class ServiceProvider {
    private String name ;

    private String institution ;

    private String actioncode ;

    private boolean is_active ;

    private String parameter ;

    private String description ;

    private int serviceproviderFee ;

    private int hulupayFee ;

    private int id ;

    public String getName() {
        return name;
    }

    public String getInstitution() {
        return institution;
    }

    public String getActioncode() {
        return actioncode;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public String getParameter() {
        return parameter;
    }

    public String getDescription() {
        return description;
    }

    public int getServiceproviderFee() {
        return serviceproviderFee;
    }

    public int getHulupayFee() {
        return hulupayFee;
    }

    public int getId() {
        return id;
    }
}
