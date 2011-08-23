package gozolabs.com;

import gozolabs.com.dao.LOYALTYPROMOTIONDao;
import gozolabs.com.entities.LoyaltyPromotions;

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
public class LoyaltyPromotionServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
	    try {

		    String reward = checkNull(req.getParameter("reward"));
		    String tnc = checkNull(req.getParameter("tnc"));
		    String block = checkNull(req.getParameter("block"));
		    String streetname = checkNull(req.getParameter("streetname"));
		    String postalcode = checkNull(req.getParameter("postalcode"));
		    
		    LOYALTYPROMOTIONDao.INSTANCE.add(new LoyaltyPromotions(reward, tnc, block, streetname, postalcode));

		    res.setHeader("Refresh", "2; /") ;
		    res.getWriter().println("All okay!");
		    
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String id = checkNull(req.getParameter("id"));
		List<LoyaltyPromotions> results;
		
	    if (!id.isEmpty()) {
		    results = LOYALTYPROMOTIONDao.INSTANCE.getLoyaltyPromotionsById(id);
	    }
	    else
	    {
		    results = LOYALTYPROMOTIONDao.INSTANCE.listLoyaltyPromotion();
	    }
	    
	    if (!results.isEmpty()) {
	    	Iterator<LoyaltyPromotions> itr = results.iterator();
	    	List loyaltyPromoList = new LinkedList();
	    	while (itr.hasNext()) {
		    	Map item = new HashMap();
		    	LoyaltyPromotions loyalPromo = itr.next();
		    	item.put("id",loyalPromo.getId());
		    	item.put("reward", loyalPromo.getReward());
		    	item.put("block", loyalPromo.getBlock());
		    	item.put("streetname", loyalPromo.getStreetName());
		    	item.put("postalcode", loyalPromo.getPostalCode());
		    	item.put("tnc", loyalPromo.getTnc());
	    	    //System.out.println("ID:" + cate.getId() + " name:" + cate.getName());
		    	loyaltyPromoList.add(item);
	    	}
	    	System.out.println("List of loyalty Promotions: " + loyaltyPromoList);
	    	
		    res.setContentType("text/plain");

		    try {
		    	JSONObject JLoyalPromo = new JSONObject().put("loyaltypromotions", loyaltyPromoList);
		    	String myString = JLoyalPromo.toString();
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
