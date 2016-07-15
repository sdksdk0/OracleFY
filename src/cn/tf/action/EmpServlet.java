package cn.tf.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import cn.tf.entity.Page;
import cn.tf.server.EmpService;
import cn.tf.server.IEmpService;

public class EmpServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//设置编号方式
			request.setCharacterEncoding("UTF-8");
			
			//获取客户端传入的参数
			String strPage = request.getParameter("page");//rows
			if(strPage == null || strPage.trim().length()==0){
				strPage = "1";
			}
			Integer currPageNO = Integer.parseInt(strPage);
			
			//调用业务层
			IEmpService iEmpService = new EmpService();
			Page page = iEmpService.show(currPageNO);
			
			//创建Map集合
			Map<String,Object> map = new LinkedHashMap<String,Object>();
			map.put("total",page.getAllRecordNO());
			map.put("rows",page.getEmpList());
			
			//使用第三方工具将map转成json文本
			JSONArray jsonArray = JSONArray.fromObject(map);
			String jsonJAVA = jsonArray.toString();
			
			//去掉二边的空格
			jsonJAVA = jsonJAVA.substring(1,jsonJAVA.length()-1);
			System.out.println("jsonJAVA=" + jsonJAVA);
			
			//以字符流的方式，将json字符串输出到客户端
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.write(jsonJAVA);
			pw.flush();
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
