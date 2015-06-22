package com.schremser.timetracker;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bluemax on 22.06.15.
 */
@WebServlet(name = "WebjarsServlet")
public class WebjarsServlet extends HttpServlet {
    private Logger log = Logger.getLogger(WebjarsServlet.class.getName());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("POST-REQ: " + request.getContextPath());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("GET-REQ: " + request.getContextPath());
    }
}
