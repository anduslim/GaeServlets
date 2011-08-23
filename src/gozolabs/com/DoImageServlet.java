package gozolabs.com;

import gozolabs.com.dao.IMAGEDao;
import gozolabs.com.entities.AroundImages;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.google.appengine.api.datastore.Blob;

public class DoImageServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			// Get the image representation
		    ServletFileUpload upload = new ServletFileUpload();
		    upload.setSizeMax(100000);
		    FileItemIterator iter = upload.getItemIterator(req);
		    res.setContentType("text/plain");
		    PrintWriter out = res.getWriter();

		    while (iter.hasNext()) {
		    	FileItemStream imageItem = iter.next();
		    	InputStream imgStream = imageItem.openStream();
		    	
		    	if (imageItem.isFormField()) {
		    		System.out.println("Got a form field: " + imageItem.getFieldName());
		    	} else {
		    		
		    		String fieldName = imageItem.getFieldName();
		    		String fileName = imageItem.getName();
		    		String contentType = imageItem.getContentType();

		    		out.println("--------------");
		    		out.println("fileName = " + fileName);
		    		out.println("field name = " + fieldName);
		    		out.println("contentType = " + contentType);
		    		
		    		String fileContents = null;
		    // construct our entity objects
		    Blob imageBlob = new Blob(IOUtils.toByteArray(imgStream));
		    AroundImages myImage = new AroundImages(imageItem.getName(), imageBlob);
	
	
		    // persist image
		    PersistenceManager pm = PMF.get().getPersistenceManager();
		    pm.makePersistent(myImage);
		    pm.close();
		    	}
		    }
	
		    // respond to query

		     out.println("OK!");
		} catch (SizeLimitExceededException e) {
			try {
				res.getWriter().println("You exceeded the maximu size ("
				+ e.getPermittedSize() + ") of the file ("
				+ e.getActualSize() + ")");
			} catch (IOException e1) {
				e1.printStackTrace();
			}   	
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String name = checkNull(req.getParameter("name"));
		List<AroundImages> results;
		// find desired image
	    PersistenceManager pm = PMF.get().getPersistenceManager();
	    //Query query = pm.newQuery("select image from AroundImages ");
	    Query query = pm.newQuery(AroundImages.class);
	    System.out.println("name" + name);
	    if (!name.isEmpty()) {
	    	query.setFilter("filename == name");
	    	query.declareParameters("String name");
		    results = (List<AroundImages>)query.execute(name);
		    System.out.println("Query with name:" + name);
	    }
	    else
	    {
		    results = (List<AroundImages>)query.execute();
		    System.out.println("General Query:");
	    }
	    System.out.println("Out:" + query.toString());
	    
	    if (!results.isEmpty()) {
	    //List<AroundImages> results = IMAGEDao.INSTANCE.listImages();
		    Blob image = results.iterator().next().getImage();
		    
		    // serve the first image
		    res.setContentType("image/jpeg");
		    try {
				res.getOutputStream().write(image.getBytes());
		        //res.sendRedirect("/aroundImages.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    } else
			try {
				res.getWriter().println("No such item ");
			} catch (IOException e) {
				e.printStackTrace();
			}
	 

	}
	
	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
	
}
