package gozolabs.com;

import gozolabs.com.dao.CATEGORYDao;
import gozolabs.com.dao.MERCHANTDao;
import gozolabs.com.entities.Addresses;
import gozolabs.com.entities.AroundImages;
import gozolabs.com.entities.Category;
import gozolabs.com.entities.LoyaltyPromotions;
import gozolabs.com.entities.Merchants;
import gozolabs.com.entities.Promotions;

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
public class MerchantServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
	    try {

		    String name = checkNull(req.getParameter("name"));
			String contactno = checkNull(req.getParameter("contactno"));
			String description = checkNull(req.getParameter("description"));
			boolean hasloyalty = Boolean.parseBoolean(checkNull(req.getParameter("hasloyalty")));
			LoyaltyPromotions loyaltypromotion = null; //merchant.getLoyaltyPromo();
			List <Promotions> promotions = null; //merchant.getPromotions();
			List <Addresses> addresses = null; //merchant.getAddresses();
			List <AroundImages> brandimages = null; //merchant.getBrandImages();
			List <AroundImages> images = null; //merchant.getImages();
		    System.out.println("para name:" + name);
		    
		    MERCHANTDao.INSTANCE.add(new Merchants(name, contactno, description, addresses, brandimages, images, hasloyalty, loyaltypromotion, promotions));

		    res.setHeader("Refresh", "2; /") ;
		    res.getWriter().println("All okay!");
		    
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String inId = checkNull(req.getParameter("id"));
		List<Merchants> results;
		
	    if (!inId.isEmpty()) {
		    results = MERCHANTDao.INSTANCE.getMerchantById(inId);
	    }
	    else
	    {
		    results = MERCHANTDao.INSTANCE.listMerchant();
	    }

	    //System.out.print(results);
	    
	    if (!results.isEmpty()) {
	    	Iterator<Merchants> itr = results.iterator();
	    	List merchantList = new LinkedList();
	    	while (itr.hasNext()) {
		    	Map item = new HashMap();
		    	Merchants merchant = itr.next();
		    	item.put("id",merchant.getId());
		    	item.put("name", merchant.getName());
		    	item.put("contactno", merchant.getContactNo());
		    	item.put("description", merchant.getDescription());
		    	item.put("hasloyalty", merchant.isHasLoyalty());
		    	Map loyaltypromoitem = new HashMap();
		    	loyaltypromoitem.put("id", merchant.getLoyaltyPromo().getId());
		    	loyaltypromoitem.put("reward", merchant.getLoyaltyPromo().getReward());
		    	loyaltypromoitem.put("streetname", merchant.getLoyaltyPromo().getStreetName());
		    	loyaltypromoitem.put("block", merchant.getLoyaltyPromo().getBlock());
		    	loyaltypromoitem.put("postalcode", merchant.getLoyaltyPromo().getPostalCode());
		    	loyaltypromoitem.put("tnc", merchant.getLoyaltyPromo().getTnc());
		    	item.put("loyaltypromotion", loyaltypromoitem);
	    	    //System.out.println("ID:" + cate.getId() + " name:" + cate.getName());
		    	merchantList.add(item);
	    	}
	    	System.out.println("List of merchants: " + merchantList);
	    	
		    res.setContentType("text/plain");

		    try {
		    	JSONObject JCat = new JSONObject().put("merchant", merchantList);
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
