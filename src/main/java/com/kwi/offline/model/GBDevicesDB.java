package com.kwi.offline.model;

import java.sql.*;
import java.util.*;

import com.kwi.jdbc.JDBCConnector;
import com.kwi.offline.*;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * The handler class used to fetch the data required.
 * 
 * @author Dinuka Arseculeratne
 *
 */
 
public class GBDevicesDB {
     
     
    public static List<String> selectGBDevices()
    {
    	
		Connection con = JDBCConnector.getJDBCConnection("call");	
		StringBuffer buf = new StringBuffer();
		String query ="SELECT deviceid FROM gb_devices"; 
        PreparedStatement ps=null;
        ResultSet rs=null;
        //Map referenceData=null;
    
        try{
             ps = con.prepareStatement(query);
             rs=ps.executeQuery();
              
             //Map<String,String> gbdevices = new LinkedHashMap<String,String>();
             List<String> gbdevices = new ArrayList<String>();   
    		
             //ArrayList<GBDevice> gbdevices = new ArrayList<GBDevice>();
             
             while(rs.next())
             {
            	 GBDevice c = new GBDevice(rs.getString("deviceid"));
               
            	 gbdevices.add(c.getDeviceId());
            }
             
       		//referenceData.put("gbDevices", gbdevices);          
            return gbdevices;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            try {
            	ps.close();        	
            	rs.close();
            } catch(Exception e) {}

            }
        }
    
    public static List<GBDevice> selectGBDeviceInfo()
    {
    	
		Connection con = JDBCConnector.getJDBCConnection("call");	
		StringBuffer buf = new StringBuffer();
		String query ="SELECT clientname,device_token_gdop,deviceid FROM gb_devices a,gb_devices_offline_pushkit b where b.device_id_gdop=a.deviceid "; 
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<GBDevice> gbdeviceinfo=null;
    
        try{
             ps = con.prepareStatement(query);
             rs=ps.executeQuery();
              
             gbdeviceinfo = new ArrayList<GBDevice>();   
    		
             while(rs.next())
             {
            	 System.out.println("rs size");
            	 GBDevice c = new GBDevice(rs.getString("clientname"),rs.getString("deviceid"),rs.getString("device_token_gdop"));
            	 gbdeviceinfo.add(c);
            }
             
            return gbdeviceinfo;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            try {
            	ps.close();        	
            	rs.close();
            } catch(Exception e) {}

            }
        }
    
 
    /**
     * This method will fetch the gbdevice list. 
     * 
     * @return
     */
    public static JQGridDTO<GBDevice> loadGBDevicesInfo() {
     /**
      * The page and rows are sent from the JQGrid component with the Ajax query.
      * 
      */
    	int page=1;
    	int pageSize=20;
/*
    	try {
    		page = Integer.valueOf(req.getParameter("page")).intValue();
    	} catch(Exception e) {
    		page=1;
     	}
    	
    	try {
    		pageSize = Integer.valueOf(req.getParameter("rows")).intValue();
    	} catch(Exception e) {
    		pageSize=20;
    	}
*/
     /**
      * I am not using the star index and end index in this case, but in an
      * ideal situation, you will be passing the start and end index to your
      * pagination SQL query.
      * 
      */
//     int startIndex = page == 1 ? 0 : (pageSize * (page - 1));
//     int endIndex = page == 1 ? pageSize : pageSize * page;
     int total = -1;
     JQGridDTO<GBDevice> jqGridData = new JQGridDTO<GBDevice>();
     List<GBDevice> gbdevicesinfo = selectGBDeviceInfo();
     /**
      * The total in the ideal situation would be the count of the records of
      * your SQL query from the table you want to fetch data from.
      * 
      */
     total = gbdevicesinfo.size();
//     jqGridData.setPage(page);
     jqGridData.setTotal(String.valueOf(Math.ceil((double) total / pageSize)));
     jqGridData.setRecords(String.valueOf(total));
     jqGridData.setRows(gbdevicesinfo);
     return jqGridData;
    }
    
    /*
    public static Map<String,GBDevice> selectGBDeviceInfo()
    {
    	
		Connection con = JDBCConnector.getJDBCConnection("call");	
		StringBuffer buf = new StringBuffer();
		String query ="SELECT clientname,device_token_gdop,deviceid FROM gb_devices a,gb_devices_offline_pushkit b where a.device_id_gdop=b.deviceid "; 
        PreparedStatement ps=null;
        ResultSet rs=null;
        Map<String,GBDevice> gbdevicesinfo = null;
        //Map referenceData=null;
    
        try{
             ps = con.prepareStatement(query);
             rs=ps.executeQuery();
              
             gbdevicesinfo = new LinkedHashMap<String,GBDevice>();
             
             while(rs.next())
             {
            	 GBDevice c = new GBDevice();
            	 c.setDeviceId(rs.getString("deviceid"));
            	 c.setClient(rs.getString("clientname"));
            	 c.setDeviceToken(rs.getString("device_token_gdop"));
                 
            	 gbdevicesinfo.put(c.getDeviceId(),c);
            }
             
       		//referenceData.put("gbDevices", gbdevices);          
            return gbdevicesinfo;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            try {
            	ps.close();        	
            	rs.close();
            } catch(Exception e) {}

            }
        }
   */
    
         
    }
 
