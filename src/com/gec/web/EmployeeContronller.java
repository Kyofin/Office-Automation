//package com.gec.web;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.URLDecoder;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.gec.bean.Apart;
//import com.gec.bean.Employee;
//import com.gec.bean.Job;
//import com.gec.dao.DaoFactory;
//import com.gec.service.ApartService;
//import com.gec.service.EmployeeService;
//import com.gec.service.JobService;
//
///**
// * Servlet implementation class EmployeeContronller
// */
//@WebServlet({"/queryEmployee","/addEmployee","/checkcard","/deleteEmployee"})
//public class EmployeeContronller extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public EmployeeContronller() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		request.setCharacterEncoding("utf-8");
//		//��ȡuri��ȷ�������·��
//		String uri = request.getRequestURI();
//		if (uri.endsWith("queryEmployee"))
//		{
//			//Ĭ����ʾ����ְҵ
//			List<Employee> employeeslist = EmployeeService.getEmployeeList();
//			//��ȡȫ��ְλ
//			List<Job> jobs = JobService.getJobList();
//			request.setAttribute("jobs",jobs);
//			//��ȡȫ������
//			List<Apart> departs = ApartService.getApartList();
//			request.setAttribute("departs", departs);
//			
//			//��ȡ������ɾ���Ĳ���(���غ�ɾ�����߼�)
//			String jobid= request.getParameter("job");
//			String name = request.getParameter("name");
//			String strcardid = request.getParameter("cardid");
//			String sex = request.getParameter("sex");
//			String phone = request.getParameter("phone");
//			String departid = request .getParameter("depart");
//			
//			//url���Ĳ�������
//			if (name!=null) 
//			{
//				name = URLDecoder.decode(name, "utf-8");
//			}
//			
//			
//			String search = request.getParameter("search");
//			String delete = request.getParameter("delete");
//			if (search!=null)
//			{
//				List<Employee> qEmployees= EmployeeService.getEmployeeListByLike(jobid, name, strcardid, sex, phone, departid);
//				//���������������ʾ������
//				employeeslist = qEmployees;
//			}else if (delete!=null) 
//			{
//				//
//			}			
//			
//			
//			request.setAttribute("employeeslist", employeeslist);
//			request.getRequestDispatcher("WEB-INF/page/queryEmployeePage.jsp").forward(request, response);
//			
//		}else if (uri.endsWith("addEmployee"))
//		{
//			//��ȡȫ��ְλ
//			List<Job> jobs = JobService.getJobList();
//			request.setAttribute("jobs",jobs);
//			//��ȡȫ������
//			List<Apart> departs = ApartService.getApartList();
//			request.setAttribute("departs", departs);
//			
//			//��ȡ����
//			String name = request.getParameter("name");
//			String creditid = request.getParameter("creditid");
//			String sex = request.getParameter("sex");
//			String job = request.getParameter("job");
//			String education = request.getParameter("education");
//			String email = request.getParameter("email");
//			String phone = request.getParameter("phone");
//			String tel = request.getParameter("tel");
//			String party = request.getParameter("party");
//			String qq = request.getParameter("qq");
//			String address = request.getParameter("address");
//			String postcode = request.getParameter("postcode");
//			String birthday = request.getParameter("birthday");
//			String race = request.getParameter("race");
//			String major = request.getParameter("major");
//			String hobby = request.getParameter("hobby");
//			String remark = request.getParameter("remark");
//			String depart = request.getParameter("depart");
//			
//			String[] params = {depart,remark,hobby,major,race,birthday,postcode,address,name,creditid,sex,job,education,email,phone,tel,party,qq};
//			
//			boolean pos= true;
//			for (String string : params) 
//			{
//				if (string==null) {
//					pos = false;
//				}
//			}
//			
//			if (pos)
//			{
//
//				//��װinsertEmployee
//				Employee insertEmployee = new Employee();
//				insertEmployee.setName(name);
//				insertEmployee.setCardid(creditid);
//				insertEmployee.setSex(sex);
//				insertEmployee.setJobid(Integer.parseInt(job));
//				insertEmployee.setEducation(education);
//				insertEmployee.setEmail(email);
//				insertEmployee.setPhone(phone);
//				insertEmployee.setTel(tel);
//				insertEmployee.setParty(party);
//				insertEmployee.setQq(qq);
//				insertEmployee.setAddress(address);
//				insertEmployee.setPostcode(postcode);
//				insertEmployee.setBirthday(java.sql.Date.valueOf(birthday));//�������ַ���תΪdate����
//				insertEmployee.setRace(race);
//				insertEmployee.setSpeciality(major);
//				insertEmployee.setRemark(remark);
//				insertEmployee.setDepartid(Integer.parseInt(depart));
//				insertEmployee.setCreatedate(new Date());
//				
//				
//				//����EmployeeService
//				if(EmployeeService.addEmployee(insertEmployee))
//				{
//					//����ӳɹ��ķ�����¼��request��������
//					request.setAttribute("info", "�ɹ����");
//					
//				}
//				
//			}
//			
//			request.getRequestDispatcher("WEB-INF/page/addEmployeePage.jsp").forward(request, response);;
//			
//		}else if(uri.endsWith("checkcard"))
//		{
//			//ͨ��AJAX�������֤���Ƿ��Ѵ���
//			String card = request.getParameter("card");//�ύ�����֤�Ų���
//			response.setCharacterEncoding("utf-8");
//			PrintWriter out = response.getWriter();
//			boolean pos = EmployeeService.IsHavEmpByCardId(card);
//			
//			if (pos)
//			{
//				out.println("���֤�Ѵ��ڣ�����������");//������Ӧ
//			}else 
//			{
//				out.println("���֤��֤ͨ��");//������Ӧ
//			}
//			
//		}else if (uri.endsWith("deleteEmployee"))
//		{
//			String[] EmpsId = request.getParameterValues("EmpsId");
//			for (String string : EmpsId) {
//				EmployeeService.deleteApartById(string);
//			}
//			
//			response.sendRedirect("/oasystem/queryEmployee");
//		}
//		
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
