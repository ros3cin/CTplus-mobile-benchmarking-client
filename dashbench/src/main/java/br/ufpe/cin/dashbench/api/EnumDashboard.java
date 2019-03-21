package br.ufpe.cin.dashbench.api;

import br.ufpe.cin.dashbench.configuration.IpAddress;

public enum EnumDashboard {
    WHAT_NOW ("/what_now"),
    DONE("/done"),
    CLEAN_BATTERY ("/cleanbattery"),
    LOG_DATA("/logdata");

    private final String nameAction;

    EnumDashboard(String name) {
        nameAction = name;
    }

    public String getName(){
        return this.name();
    }

    public String toString(){
        return "http://"+IpAddress.ip+":"+IpAddress.port+nameAction;
    }
}
