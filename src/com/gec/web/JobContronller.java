//package com.gec.web;
//
//import java.io.IOException;
//import java.net.URLDecoder;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.gec.bean.Apart;
//import com.gec.bean.Job;
//import com.gec.service.ApartService;
//import com.gec.service.JobService;
//
///**
// * Servlet implementation class JobContronller
// */
//@WebServlet({"/queryJob","/addJob","/deleteJob"})
//public class JobContronller extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public JobContronller() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//	{
//		request.setCharacterEncoding("utf-8");
//		//��ȡuri��ȷ�������·��
//		String uri = request.getRequestURI();
//		if (uri.endsWith("queryJob"))
//		{
//			//Ĭ����ʾ����ְҵ
//			List<Job> jobslist = JobService.getJobList();
//			
//			//��ȡ������ɾ���Ĳ���(���غ�ɾ�����߼�)
//			String jobname= request.getParameter("jobname");
//			
//			//url���Ĳ�������
//			if (jobname!=null) 
//			{
//				jobname = URLDecoder.decode(jobname, "utf-8");
//			}
//			
//			String search = request.getParameter("search");
//			String delete = request.getParameter("delete");
//			if (search!=null)
//			{
//				List<Job> qJobs= JobService.getJobListByLike(jobname);
//				//���������������ʾ������
//				jobslist = qJobs;
//			}else if (delete!=null) 
//			{
//				//
//			}			
//			
//			request.setAttribute("jobslist", jobslist);
//			request.getRequestDispatcher("WEB-INF/page/queryJobPage.jsp").forward(request, response);;
//			
//		}else if (uri.endsWith("addJob"))
//		{
//			//��ȡ����
//			String jobname = request.getParameter("jobname");
//			String remark = request.getParameter("remark");
//			
//			if (remark!=null&&jobname!=null)
//			{
//				//��װinsertJob
//				Job insertJob = new Job();
//				insertJob.setMark(remark);
//				insertJob.setName(jobname);
//				
//				//����JobService
//				JobService.addJob(insertJob);
//				
//				//����ӳɹ��ķ�����¼��request��������
//				request.setAttribute("info", "�ɹ����");
//			}
//			
//			request.getRequestDispatcher("WEB-INF/page/addJobPage.jsp").forward(request, response);;
//			
//		}else if (uri.endsWith("deleteJob"))
//		{
//			String[] jobsId = request.getParameterValues("jobid");
//			for (String string : jobsId) {
//				JobService.deleteJobById(string);
//			}
//			
//			response.sendRedirect("/oasystem/queryJob");
//		}
//		
//		
//	
//	
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
