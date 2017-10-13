package com.gec.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.gec.bean.Apart;
import com.gec.bean.FileBean;
import com.gec.bean.Job;
import com.gec.bean.User;
import com.gec.service.ApartService;
import com.gec.service.FileService;
import com.gec.service.JobService;

/**
 * Servlet implementation class DocumentController
 */
@WebServlet({ "/deleteFile","/addFile","/queryFile", "/uploadFile","/downloadFile" })
@MultipartConfig
public class DocumentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DocumentController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取文件名
	 * 
	 * @param part
	 * @return
	 */
	public String getFileName(Part part) 
	{
		String fileName = "";
		String ContentDisposition = part.getHeader("Content-Disposition");
		String regex = "filename=\"(.+)\"";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ContentDisposition);
		if (matcher.find()) {
			fileName = matcher.group(1);
		}
		return fileName;
	}

	public FileBean getFileBean(String title, String fileinfo, Part part, HttpServletRequest request) throws Exception 
	{

		// 获取文件区域
		Part filePart = request.getPart("uploadfile");
		// 获取文件名
		String fileName = getFileName(filePart);
		// 获取文件数据
		InputStream in = filePart.getInputStream();
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int count = 0;
		while ((count = in.read(buffer)) != -1) {
			bStream.write(buffer, 0, count);
			bStream.flush();
		}
		// 将文件数据组装成fileBean对象
		FileBean fileBean = new FileBean();
		fileBean.setFileName(fileName);
		fileBean.setRemark(fileinfo);
		fileBean.setTitle(title);
		fileBean.setCreatedate(new Date());
		fileBean.setFileBytes(bStream.toByteArray());
		User loginUser = (User) request.getSession(false).getAttribute("loginUser");
		fileBean.setUserid(loginUser.getId());

		return fileBean;

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		request.setCharacterEncoding("utf-8");
		// 获取uri以确认请求的路径
		String uri = request.getRequestURI();
		
		
		if (uri.endsWith("queryFile")) 
		{
			// 默认显示所有用户
			List<FileBean> filelist = FileService.getFileList();

			//获取搜索或删除的参数(搜素和删除的逻辑)
			String name= request.getParameter("name");
			
			//url中文参数乱码
			if (name!=null) 
			{
				name = URLDecoder.decode(name, "utf-8");
			}
			
			String search = request.getParameter("search");
			String delete = request.getParameter("delete");
			if (search!=null)
			{
				List<FileBean> qfiles= FileService.getFileListByLike(name); 
				//将搜索结果放入显示集合中
				filelist = qfiles;
			}else if (delete!=null) 
			{
				//
			}			
			
			
			request.setAttribute("filelist", filelist);
			request.getRequestDispatcher("WEB-INF/page/queryFilePage.jsp").forward(request, response);

		} else if (uri.endsWith("uploadFile"))
		{
			// 获取参数
			String title = request.getParameter("title");
			String fileinfo = request.getParameter("fileinfo");
			Part part = request.getPart("uploadfile");

			if (title != null && fileinfo != null && part != null) {

				// 组装insertFileBean
				FileBean insertFile = null;
				try {
					insertFile = getFileBean(title, fileinfo, part, request);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// 调用FileService
				boolean flag= FileService.addFile(insertFile);
				if (flag)
				{
					// 将添加成功的反馈记录到request的属性中
					request.setAttribute("info", "成功添加");
				}
				
				
			}

			request.getRequestDispatcher("WEB-INF/page/uploadFilePage.jsp").forward(request, response);
			
		}else if (uri.endsWith("addFile"))
		{
			request.getRequestDispatcher("WEB-INF/page/uploadFilePage.jsp").forward(request, response);

		}else if (uri.endsWith("downloadFile"))
		{
			//获取下载的文件id
			String fileid = request.getParameter("fileid");
			FileBean searchBean = new FileBean();
			searchBean.setId(Integer.parseInt(fileid));
			
			//调用fileService获取下载的文件对象
			FileBean outBean= FileService.getFileBeanByID(searchBean);
			byte[] buffer =  outBean.getFileBytes();
			
			//设置响应内容类型为可下载文件
			response.setContentType("APPLICATION/OCTET-STREAM");
			
			//设置文件名
			response.addHeader("Content-Disposition", "attachment;filename="+outBean.getFileName());
			
			//将字节数组写入响应输出流
			OutputStream os= response.getOutputStream();
			os.write(buffer);
			os.flush();
			
		}else if (uri.endsWith("deleteFile"))
		{
			String[] filesId = request.getParameterValues("filesId");
			for (String string : filesId) {
				FileService.deleteFileById(string);
			}
			
			response.sendRedirect("/oasystem/queryFile");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
