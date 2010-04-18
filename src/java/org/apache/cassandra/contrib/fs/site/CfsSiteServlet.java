package org.apache.cassandra.contrib.fs.site;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cassandra.contrib.fs.CassandraFileSystem;
import org.apache.commons.io.IOUtils;
import org.apache.thrift.transport.TTransportException;

public class CfsSiteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = getPath(request);
		try {
			read(resp.getOutputStream(),path);
		} catch (CfsSiteException e) {
			outputException(resp, e);
		}
	}
	private void outputException(HttpServletResponse resp,CfsSiteException ex){
		resp.setContentType("text/xml");
		try {
			//TODO: use more advanced xml generation
			resp.getWriter().write("<Error>\n");
				resp.getWriter().write("<Code>\n");
					resp.getWriter().write(ex.getErrorCode());
				resp.getWriter().write("</Code>\n");
				
				resp.getWriter().write("<Message>\n");
					resp.getWriter().write(ex.getMessage()); //TODO: replace special chars
				resp.getWriter().write("</Message>\n");
			resp.getWriter().write("</Error>");	
		}
		catch (IOException e) {
			e.printStackTrace();
		}	
	}
	private void writeSuccessful(HttpServletResponse resp,String msg){
		resp.setContentType("text/xml");
		try {
			//TODO: use more advanced xml generation
			resp.getWriter().write("<Response>\n");
				resp.getWriter().write("<Code>\n");
					resp.getWriter().write("OK");
				resp.getWriter().write("</Code>\n");
				
				resp.getWriter().write("<Message>\n");
					resp.getWriter().write(msg); //TODO: replace special chars
				resp.getWriter().write("</Message>\n");
			resp.getWriter().write("</Response>");	
		}
		catch (IOException e) {
			e.printStackTrace();
		}	
	}
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
        String path = getPath(request);
        try {
        	store(request.getInputStream(), path);
		} 
        catch (CfsSiteException e) {
			outputException(resp,e);
		}   
	}
	private String getPath(HttpServletRequest request){
		return request.getRequestURI();
	}
	private String store(InputStream stream, String path) throws CfsSiteException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			IOUtils.copy(stream, baos);
		} catch (IOException e) {
			throw new CfsSiteException("BrokenHttpTransfer",e.getMessage());
		}
		finally{
			if(baos != null){
				try {
					baos.close();
				} catch (IOException e) {}
			}
		}
		try {
			CassandraFileSystem.getInstance().createFile(path, baos.toByteArray());
		} catch (TTransportException e) {
			throw CfsSiteException.fromTTransportException(e);
		} catch (IOException e) {
			throw new CfsSiteException("IOException",e.getMessage());
		}
		return path;
	}
	
	private String read(OutputStream stream, String path) throws CfsSiteException{
		InputStream is = null;
		try {
			byte[] bs = CassandraFileSystem.getInstance().readFile(path);
			if(bs == null){
				throw new CfsSiteException("NotExistFile",path);
			}
			//output
			IOUtils.copy(new ByteArrayInputStream(bs), stream);
		} catch (TTransportException e) {
			throw CfsSiteException.fromTTransportException(e);
		} catch (IOException e) {
			throw new CfsSiteException("IOException",e.getMessage());
		}
		finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {}
			}
		}
		return path;
	}
}
