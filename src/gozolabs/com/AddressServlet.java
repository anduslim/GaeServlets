package gozolabs.com;

import gozolabs.com.dao.ADDRESSDao;
import gozolabs.com.entities.Addresses;

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
public class AddressServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
	    try {

		    String buildingname = checkNull(req.getParameter("buildingname"));
		    String streetname = checkNull(req.getParameter("streetname"));
		    String unitno = checkNull(req.getParameter("unitno"));
		    String block = checkNull(req.getParameter("block"));
		    String postalcode = checkNull(req.getParameter("postalcode"));
		    
		    ADDRESSDao.INSTANCE.add(new Addresses(buildingname, unitno, block, streetname, postalcode));
		    
		    res.setHeader("Refresh", "2; /") ;
		    res.getWriter().println("All okay!");
		    
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String name = checkNull(req.getParameter("name"));
		List<Addresses> results;
		
	    if (!name.isEmpty()) {
		    results = ADDRESSDao.INSTANCE.getAddressByBuilding(name);
	    }
	    else
	    {
		    results = ADDRESSDao.INSTANCE.listAddresses();
	    }
	    
	    if (!results.isEmpty()) {
	    	Iterator<Addresses> itr = results.iterator();
	    	List addList = new LinkedList();
	    	while (itr.hasNext()) {
		    	Map item = new HashMap();
		    	Addresses address = itr.next();
		    	item.put("id",address.getId());
		    	item.put("buildingname", address.getBuildingName());
		    	item.put("block", address.getBlock());
		    	item.put("streetname", address.getStreetName());
		    	item.put("unitno", address.getUnitNo());
		    	item.put("postalcode", address.getPostalCode());
	    		addList.add(item);
	    	}
	    
	    	System.out.println("List of addresses: " + addList);
    	
	    	res.setContentType("text/plain");

	    	try {
		    	JSONObject JAdd = new JSONObject().put("addresses", addList);
		    	String myString = JAdd.toString();
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
