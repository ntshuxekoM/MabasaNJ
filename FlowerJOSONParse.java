package com.example.network1;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FlowerJOSONParse {
    public static List<Flower>  parseFeed(String content)
    {
    	try {
			JSONArray js = new JSONArray(content);
			List<Flower> listOfFlowers = new ArrayList<Flower>();
			
			for (int i = 0; i < js.length(); i++) {
				JSONObject jo = js.getJSONObject(i);
				Flower flower = new Flower();
				
				
				flower.setName(jo.getString("name"));
				flower.setCategory(jo.getString("category"));
				flower.setInstructions(jo.getString("instructions"));
				flower.setPrice(jo.getDouble("price"));
				flower.setPhoto(jo.getString("photo"));
				
				listOfFlowers.add(flower);
			}
			return listOfFlowers;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
}
