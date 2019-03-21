package br.ufpe.cin.dashbench.configuration;

import android.content.Intent;

public class IpAddress {
    public static String ip = "192.168.25.9";
    public static String port = "3000";

    /**
     * Expects the main activity to have been initialized with the
     * following extra parameters:
     * - ipAddress the host ip address to which communication will be directed
     * - port the host port
     * @param mainActivityIntent
     */
    public static void setEndpointAddress(Intent mainActivityIntent) {
        String ipAddress = mainActivityIntent.getStringExtra("ipAddress");
        String port = mainActivityIntent.getStringExtra("port");
        if( (ipAddress!=null) && (!"".equals(ipAddress)) ){
            IpAddress.ip=ipAddress;
        }
        if( (port!=null) && (!"".equals(port)) ){
            IpAddress.port=port;
        }
    }
}
