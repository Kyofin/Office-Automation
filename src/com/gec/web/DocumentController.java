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
	 * ��ȡ�ļ���
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

		// ��ȡ�ļ�����
		Part filePart = request.getPart("uploadfile");
		// ��ȡ�ļ���
		String fileName = getFileName(filePart);
		// ��ȡ�ļ�����
		InputStream in = filePart.getInputStream();
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int count = 0;
		while ((count = in.read(buffer)) != -1) {
			bStream.write(buffer, 0, count);
			bStream.flush();
		}
		// ���ļ�������װ��fileBean����
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
		// ��ȡuri��ȷ�������·��
		String uri = request.getRequestURI();
		
		
		if (uri.endsWith("queryFile")) 
		{
			// Ĭ����ʾ�����û�
			List<FileBean> filelist = FileService.getFileList();

			//��ȡ������ɾ���Ĳ���(���غ�ɾ�����߼�)
			String name= request.getParameter("name");
			
			//url���Ĳ�������
			if (name!=null) 
			{
				name = URLDecoder.decode(name, "utf-8");
			}
			
			String search = request.getParameter("search");
			String delete = request.getParameter("delete");
			if (search!=null)
			{
				List<FileBean> qfiles= FileService.getFileListByLike(name); 
				//���������������ʾ������
				filelist = qfiles;
			}else if (delete!=null) 
			{
				//
			}			
			
			
			request.setAttribute("filelist", filelist);
			request.getRequestDispatcher("WEB-INF/page/queryFilePage.jsp").forward(request, response);

		} else if (uri.endsWith("uploadFile"))
		{
			// ��ȡ����
			String title = request.getParameter("title");
			String fileinfo = request.getParameter("fileinfo");
			Part part = request.getPart("uploadfile");

			if (title != null && fileinfo != null && part != null) {

				// ��װinsertFileBean
				FileBean insertFile = null;
				try {
					insertFile = getFileBean(title, fileinfo, part, request);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// ����FileService
				boolean flag= FileService.addFile(insertFile);
				if (flag)
				{
					// ����ӳɹ��ķ�����¼��request��������
					request.setAttribute("info", "�ɹ����");
				}
				
				
			}

			request.getRequestDispatcher("WEB-INF/page/uploadFilePage.jsp").forward(request, response);
			
		}else if (uri.endsWith("addFile"))
		{
			request.getRequestDispatcher("WEB-INF/page/uploadFilePage.jsp").forward(request, response);

		}else if (uri.endsWith("downloadFile"))
		{
			//��ȡ���ص��ļ�id
			String fileid = request.getParameter("fileid");
			FileBean searchBean = new FileBean();
			searchBean.setId(Integer.parseInt(fileid));
			
			//����fileService��ȡ���ص��ļ�����
			FileBean outBean= FileService.getFileBeanByID(searchBean);
			byte[] buffer =  outBean.getFileBytes();
			
			//������Ӧ��������Ϊ�������ļ�
			response.setContentType("APPLICATION/OCTET-STREAM");
			
			//�����ļ���
			response.addHeader("Content-Disposition", "attachment;filename="+outBean.getFileName());
			
			//���ֽ�����д����Ӧ�����
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
