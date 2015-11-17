/**
 */
package camlebell.com.net;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

public class JSONBeanUtil {

	public static <T> T getObjectFromJson(String json, Class<T> valueType) {
		T bean = null;

		try {
			Gson gson = new Gson();
			bean = gson.fromJson(json, valueType);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}

		return bean;
	}

	public static <T> T getObjectFromJson(String json, Type classOfT){
		Gson gson = new Gson();
		return gson.fromJson(json, classOfT);
	}
	
	public static String getJsonFromObject(Object valueType) {

		Gson gson = new Gson();
		return gson.toJson(valueType);
	}
	
}
