package gozolabs.com;

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
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

public class CategoryServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
	    try {

		    String name = checkNull(req.getParameter("name"));
		    System.out.println("para name:" + name);
		    CATEGORYDao.INSTANCE.add(name);

//			Category cate = new Category(name);
//		    // persist Category
//		    PersistenceManager pm = PMF.get().getPersistenceManager();
//		    pm.makePersistent(cate);
//		    pm.close();
		    res.getWriter().println("All okay!");
		    //res.sendRedirect("/around.jsp");
		    
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String name = checkNull(req.getParameter("name"));
		List<Category> results;
		
	    if (!name.isEmpty()) {
		    results = CATEGORYDao.INSTANCE.getCategory(name);
	    }
	    else
	    {
		    results = CATEGORYDao.INSTANCE.listCategory();
		    //System.out.println("General Query:" + query);
	    }

	    //System.out.print(results);
	    
	    if (!results.isEmpty()) {
	    	Iterator<Category> itr = results.iterator();
	    	List catList = new LinkedList();
	    	while (itr.hasNext()) {
		    	Map address = new HashMap();
		    	Category cate = itr.next();
	    		address.put("id",cate.getId());
	    		address.put("name", cate.getName());
	    	    System.out.println("ID:" + cate.getId() + " name:" + cate.getName());
	    		catList.add(address);
	    	}
	    	System.out.println("List of categories: " + catList);
	    	
		    res.setContentType("text/plain");

		    try {
			    //PrintWriter out = res.getWriter();
				//out.print(jsonString);
		    	JSONObject JCat = new JSONObject().put("category", catList);
		    	String myString = JCat.toString();
				res.getWriter().println(myString);
		        //res.sendRedirect("/aroundImages.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
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
