package com.fps.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class HttpStatusException {
    public static void httpStatusEx(int code, String msg, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(code);
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(msg.getBytes());
        out.flush();
        out.close();
    }
}
