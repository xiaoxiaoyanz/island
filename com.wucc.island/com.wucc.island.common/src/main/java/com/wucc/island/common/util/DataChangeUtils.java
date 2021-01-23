package com.wucc.island.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 数据以及类型转换
 * @author shenpf
 * @since 2020-04-10
 *
 */
public class DataChangeUtils {

	/**
	 * 逗号隔开的ids转换为List
	 * @param ids
	 * @return
	 */
	public static List<String> idsToList(String ids) {	
		List<String> idsList= new ArrayList<String>();
		String[] split = ids.split(",");
		for(String id:split){
			idsList.add(id);
		}
		return idsList;
	}

	/**
	 * map key值转换由下划线转换成驼峰
	 * @param ids
	 * @return
	 */
	public static Map<String, Object> mapKeyLineToHump(Map<String, Object> map) {	
		Map<String, Object> mapBean= new HashMap<String, Object>();
		map.forEach((key, value) -> {
			mapBean.put(EasyUtils.lineToHump(key), value);
		});
		return mapBean;
	}
	
	/**
	 * map key值转换由下划线转换成驼峰
	 * @param ids
	 * @return
	 */
	public static List<Map<String, Object>> listMapKeyLineToHump(List<Map<String, Object>> list) {	
		List<Map<String, Object>> detailList= new ArrayList<Map<String, Object>>();
		Map<String, Object> mapDetail= new HashMap<String, Object>();
		if(list != null && list.size()>0){
			for(Map<String, Object> map:list){
				map.forEach((key, value) -> {
					mapDetail.put(EasyUtils.lineToHump(key), value);
				});
				detailList.add(mapDetail);
			}
		}
		return detailList;
	}
	
	
	public static void parseBean(String jsonStr, List<String> sqlList) {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		if (null != jsonObject) {
			Iterator<String> keys = jsonObject.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				String filedSet = null;
				if (key.endsWith("Bean")) {
					filedSet = jsonObject.get(key).toString();
					parseBean(filedSet, sqlList);
				}

				JSONObject object = JSONObject.fromObject(filedSet);
				String tableName = key.replace("Bean", "");
				StringBuilder stringBuilder = new StringBuilder();
				installSql(tableName, stringBuilder, object);

			}
		}
	}

	public static void parseList(String jsonStr, List<String> sqlList) {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		if (null != jsonObject) {
			Iterator<String> keys = jsonObject.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				String filedSet = null;
				if (key.endsWith("List")) {
					filedSet = jsonObject.get(key).toString();
					parseList(filedSet, sqlList);
				}

				JSONArray jsonArray = JSONArray.fromObject(filedSet);
				String tableName = key.replace("List", "");
				StringBuilder stringBuilder = new StringBuilder();

				jsonArray.forEach(x -> {
					installSql(tableName, stringBuilder, x);
				});
			}
		}
	}

	private static void installSql(String tableName,
			StringBuilder stringBuilder, Object x) {
		JSONObject object = JSONObject.fromObject(x);
		stringBuilder.append("insert into " + tableName);
		List<String> filedList = new ArrayList<>();
		List<Object> valueList = new ArrayList<>();
		Iterator<String> iterator = object.keys();
		while (iterator.hasNext()) {
			String filed = iterator.next();
			filedList.add(filed);
			Object value = object.get(filed);
			valueList.add(value);
		}

		if (!filedList.isEmpty()) {
			stringBuilder.append("(");
			filedList.forEach(y -> {
				stringBuilder.append(y + ",");
			});
			stringBuilder.deleteCharAt(stringBuilder.length());
			stringBuilder.append(")");

			stringBuilder.append("values");
			stringBuilder.append("(");

			valueList.forEach(y -> {
				stringBuilder.append(y + ",");
			});
			stringBuilder.deleteCharAt(stringBuilder.length());
			stringBuilder.append(")");
		}
	}

	private static void excuteSql(List<String> sqlList) {

	}

}
