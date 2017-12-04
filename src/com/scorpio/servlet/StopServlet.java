package com.scorpio.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scorpio.util.QuartzManager;

@WebServlet("/StopServlet")
public class StopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StopServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jobName = "job_201711291532";
		String triggerName = "trigger_201711291532";
		QuartzManager.removeJob(jobName, triggerName);
		response.getWriter().append("job name: " + jobName + " stop.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public static void main(String[] args) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
		String string =sf.format(new Date());
		System.out.println(string);
	}
}
