package com.scorpio.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scorpio.job.MyJob;
import com.scorpio.util.TimeCreater;
import com.scorpio.util.QuartzManager;

@WebServlet("/StartServlet")
public class StartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String jobName = "job_" + TimeCreater.creat();
		String triggerName = "trigger_" + TimeCreater.creat();
		String time = "0 26 10 30 11 ?";
		HashMap<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("phone", "18225519642");
		QuartzManager.addJob(MyJob.class, jobName, triggerName, time, map);
		response.getWriter().append("job name: " + jobName + " trigger name: " + triggerName +" start.");
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
