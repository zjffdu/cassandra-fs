package org.apache.cassandra.contrib.fs.site;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class WebServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		resp.getOutputStream().write("//TODO: get the folder or file content".getBytes());
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request) == false) {
			//TODO: not a uploading
        } else { 
        	try{
                String key = null;
                ServletFileUpload upload = new ServletFileUpload();
                //  limits on both file and stream are set as a safety net.
                //  depending on which header information is available 

//                upload.setSizeMax(MAX_UPLOAD_FILE_SIZE_IN_BYTES);
//                upload.setFileSizeMax(MAX_UPLOAD_FILE_SIZE_IN_BYTES);
                FileItemIterator iter = upload.getItemIterator(request);
                while (iter.hasNext()) {
                    FileItemStream item = iter.next();
                    InputStream stream = null;
                    try {
                        stream = item.openStream();
                        if (!item.isFormField()) {  
                            key = transferFiles(stream, resp);
                        }
                    }
                    finally {
                    	if(stream != null){
                    		try{
                        		stream.close();	
                    		}
                    		catch(Exception e){}
                    	}
                    }
                }   
        	}
        	catch(Exception e){
        		//TODO: can't read or write, so return some error xml
        	}
       }            
	}

	private String transferFiles(InputStream stream, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}
}
