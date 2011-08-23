package gozolabs.com;

import gozolabs.com.dao.PROMOTIONDao;
import gozolabs.com.entities.AroundImages;
import gozolabs.com.entities.Category;
import gozolabs.com.entities.Merchants;
import gozolabs.com.entities.Promotions;

import java.io.IOException;
import java.util.Date;
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
public class PromotionServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
	    try {

		    Category category = new Category(checkNull(req.getParameter("category")));
		    String title = checkNull(req.getParameter("title"));
		    String description = checkNull(req.getParameter("description"));
		    Date startdate = new Date(req.getParameter("startdate"));
		    Date enddate = new Date(checkNull(req.getParameter("enddate")));
		    boolean islive = new Boolean(checkNull(req.getParameter("islive")));
		    List<AroundImages> images = null;//checkNull(req.getParameter("images"));
		    Merchants merchant = null;//req.getParameter("merchant"));
		    
		    
		    PROMOTIONDao.INSTANCE.add(new Promotions(title, category, description, startdate, enddate, islive, images, merchant));

		    res.setHeader("Refresh", "2; /") ;
		    res.getWriter().println("All okay!");
		    
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String id = checkNull(req.getParameter("id"));
		List<Promotions> results;
		
	    if (!id.isEmpty()) {
		    results = PROMOTIONDao.INSTANCE.getPromotionsById(id);
	    }
	    else
	    {
		    results = PROMOTIONDao.INSTANCE.listPromotion();
	    }
	    
	    if (!results.isEmpty()) {
	    	Iterator<Promotions> itr = results.iterator();
	    	List promoList = new LinkedList();
	    	while (itr.hasNext()) {
		    	Map item = new HashMap();
		    	Promotions promotion = itr.next();
		    	item.put("id",promotion.getId());
		    	item.put("category", promotion.getCategory());
		    	item.put("description", promotion.getDescription());
		    	item.put("startdate", promotion.getStartDate());
		    	item.put("enddate", promotion.getEndDate());
		    	item.put("islive", promotion.isLive());
		    	Map imageitem = new HashMap();
		    	Iterator<AroundImages> imageitr = promotion.getImages().iterator();
		    	while (imageitr.hasNext())
		    	{
		    		imageitem.put("image", imageitr.next());
		    	}
		    	item.put("images", imageitem);
		    	//item.put("merchants", promotion.getTnc());
	    	    //System.out.println("ID:" + cate.getId() + " name:" + cate.getName());
		    	promoList.add(item);
	    	}
	    	System.out.println("List of Promotions: " + promoList);
	    	
		    res.setContentType("text/plain");

		    try {
		    	JSONObject JPromo = new JSONObject().put("promotions", promoList);
		    	String myString = JPromo.toString();
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
