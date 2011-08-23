package gozolabs.com;

import gozolabs.com.dao.ADDRESSDao;
import gozolabs.com.dao.CATEGORYDao;
import gozolabs.com.entities.AroundImages;
import gozolabs.com.entities.Category;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONObject;

public class AddressServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
	    try {

		    String buildingname = checkNull(req.getParameter("buildingname"));
		    String streetname = checkNull(req.getParameter("streetname"));
		    String unitno = checkNull(req.getParameter("unitno"));
		    String block = checkNull(req.getParameter("block"));
		    String postalcode = checkNull(req.getParameter("postalcode"));
		    
		    ADDRESSDao.INSTANCE.add(buildingname, streetname, unitno, block, postalcode);
		    
		    res.sendRedirect("/around.jsp");
		    
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String name = checkNull(req.getParameter("name"));
		List<Category> results;
		CATEGORYDao.INSTANCE.getCategory(name);
		
	    if (name != null) {
		    results = CATEGORYDao.INSTANCE.getCategory(name);
	    }
	    else
	    {
		    results = CATEGORYDao.INSTANCE.listCategory();
	    }
	    
	    if (!results.isEmpty()) {
	    	Iterator<Category> itr = results.iterator();
	    	List catList = new LinkedList();
	    	while (itr.hasNext()) {
		    	Map address = new HashMap();
		    	Category cate = itr.next();
	    		address.put("Id",cate.getId());
	    		address.put("name", cate.getName());
	    		catList.add(address);
	    	}

	    	String[] jsonString = JSONObject.getNames(catList);
	    	
		    // serve the first image
		    res.setContentType("application/json");

		    try {
			    PrintWriter out = res.getWriter();
				out.print(jsonString);
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
