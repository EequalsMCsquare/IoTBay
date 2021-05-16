package com.eequalsmc2.IoTBay_Final.utils;

import java.io.PrintWriter;

public class Helper {
    public static void alert(PrintWriter out, String msg) {
        out.print("<script type='text/javascript'>alert('" + msg + "');</script>");
        out.print("<script type='text/javascript'>window.history.go(-1);</script>");
        out.flush();
        out.close();
    }
}
