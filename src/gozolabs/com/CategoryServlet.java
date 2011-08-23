package gozolabs.com;

import gozolabs.com.dao.CATEGORYDao;
import gozolabs.com.entities.Category;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class CategoryServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
	    try {

		    String name = checkNull(req.getParameter("name"));
		    System.out.println("para name:" + name);
		    
		    CATEGORYDao.INSTANCE.add(new Category(name));

		    res.setHeader("Refresh", "2; /") ;
		    res.getWriter().println("All okay!");
		    
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String name = checkNull(req.getParameter("name"));
		List<Category> results;
		
	    if (!name.isEmpty()) {
		    results = CATEGORYDao.INSTANCE.getCategory(name);
	    }
	    else
	    {
		    results = CATEGORYDao.INSTANCE.listCategory();
	    }

	    //System.out.print(results);
	    
	    if (!results.isEmpty()) {
	    	Iterator<Category> itr = results.iterator();
	    	List catList = new LinkedList();
	    	while (itr.hasNext()) {
		    	Map item = new HashMap();
		    	Category cate = itr.next();
		    	item.put("id",cate.getId());
		    	item.put("name", cate.getName());
	    	    //System.out.println("ID:" + cate.getId() + " name:" + cate.getName());
	    		catList.add(item);
	    	}
	    	System.out.println("List of categories: " + catList);
	    	
		    res.setContentType("text/plain");

		    try {
		    	JSONObject JCat = new JSONObject().put("category", catList);
		    	String myString = JCat.toString();
				res.getWriter().println(myString);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
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
