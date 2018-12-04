package demo.common;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MapUtil {

	/**
	 * user:chaoyx
	 * Date:2016年4月13日下午3:12:07
	 *
	 * @param Map   --需要判断的Map类
	 * @param Class -- 是否包含的某一指定类型
	 * @return boolean 是否包含的结果
	 * @throws Exception
	 * @描述：判断Map中的Value对象类型是否包含某一指定类型
	 */
	public static boolean checkItemType(Map map, Class c) {
		boolean fix = false;

		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object value = map.get(key);
			if (value != null && !(c.isAssignableFrom(value.getClass()))) {
				fix = true;
				break;
			}
		}
		return fix;
	}

	/**
	 * user:chaoyx
	 * Date:2016年4月13日下午3:12:07
	 *
	 * @param Map --需要判断的Map类
	 * @return boolean 是否为空的返回结果
	 * @throws Exception
	 * @描述：判断Map是否为空
	 */
	public static boolean isEmpty(Map map) {
		if (map == null || map.size() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Function: isNotEmpty
	 * @Description: 非空判断
	 * @param:参数描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: ouyq
	 * @date: 2016-7-6 上午09:59:21
	 * Modification History:
	 * Date         Author          Version            Description
	 * ---------------------------------------------------------*
	 * 2016-7-6     ouyq           v1.0.0               修改原因
	 */
	public static boolean isNotEmpty(Map map) {
		return !isEmpty(map);
	}

	/**
	 * @param
	 * @return
	 * @version v1.0.0
	 * @author zhangwx7
	 * @date 2018-04-28 11:41:52
	 */
	public static boolean valIsNotBlankForStr(Map map, String key) {
		return StringUtils.isNotBlank(getString(map, key));
	}

	public static boolean valIsBlankForStr(Map map, String key) {
		return !valIsNotBlankForStr(map, key);
	}

	/**
	 * @param
	 * @return
	 * @Description Map判断value是否等于指定值
	 * @version v1.0.0
	 * @author zhangwx7
	 * @date 2018-05-03 07:19:21
	 */
	public static boolean valEquals(Map map, Object key, Object compareVal) {
		if (map == null || !map.containsKey(key)) {
			return false;
		}
		Object tmpValue = map.get(key);
		return tmpValue != null ? tmpValue.equals(compareVal) : null == compareVal;
	}

	/**
	 * user:chaoyx
	 * Date:2016年4月13日下午3:12:07
	 * 描述：根据Key值获取Value值，如果获取不到则获取默认值 ；
	 * 注意：只能获取对象为String类型数据，如果对象为非String类型，会获取的值会强转为String
	 *
	 * @param Map          -- 要获取的MAP对象
	 * @param key          -- 要获取的Key值名称
	 * @param defaultValue -- 默认Value值
	 * @return boolean 是否包含的结果
	 * @throws Exception
	 */
	public static String getString(Map map, String key, String defaultValue) {
		if (map == null || map.size() <= 0 || map.get(key) == null) {
			return defaultValue;
		} else {
			String tmp = map.get(key).toString();
			if (!StringUtils.isBlank(tmp)) {//由原来的isblank调整为isEmptyString
				return tmp;
			} else {
				return defaultValue;
			}
		}
	}

	public static int getInt(Map map, Object key) {
		return getInt(map, key, 0);
	}

	public static int getInt(Map map, Object key, int defaultValue) {
		return org.apache.commons.collections4.MapUtils.getIntValue(map, key, defaultValue);
	}

	/**
	 * user:chaoyx
	 * Date:2016年4月13日下午3:12:07
	 * 描述：根据Key值获取Value值，获取Map
	 * 注意：只能获取对象为String类型数据，如果对象为非String类型，会获取的值会强转为String
	 *
	 * @param Map          -- 要获取的MAP对象
	 * @param key          -- 要获取的Key值名称
	 * @param defaultValue -- 默认Value值
	 * @return boolean 是否包含的结果
	 * @throws Exception
	 */
	public static List getList(Map map, String key) {
		Object obj = map.get(key);
		if (obj != null && obj instanceof List) {
			return (List) obj;
		}
		return new ArrayList();
	}

	public static String getString(Map map, String key) {
		return getString(map, key, "");
	}

	public static String getStringForNotNull(Map map, String key) throws Exception {
		String str = getString(map, key);
		if (StringUtils.isBlank(str)) {
			throw new Exception("参数[" + key + "]为空!");
		}
		return str;
	}

	public static Map getMap(Map map, String key) {
		return org.apache.commons.collections4.MapUtils.getMap(map, key);
	}

	public static long getLong(Map map, String key, long defaultValue) {
		return org.apache.commons.collections4.MapUtils.getLongValue(map, key, defaultValue);
	}

	public static long getLong(Map map, String key) {
		return org.apache.commons.collections4.MapUtils.getLongValue(map, key, 0L);
	}

	public static Map toMap(String key, String value) {
		Map map = new HashMap();
		map.put(key, value);
		return map;
	}

	public static boolean isExistValueForKey(List<Map> mapList, Object key, Object value) throws Exception {
		boolean flag = false;
		if (CollectionUtils.isNotEmpty(mapList)) {
			for (Map map : mapList) {
				Object tmpValue = map.get(key);
				if ((tmpValue == null && value == null) || tmpValue.equals(value)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	public static Object getObjForPath(Map map, String path) throws Exception {
		// 表达式为空返回null
		if (StringUtils.isBlank(path) || isEmpty(map)) {
			return null;
		}

		Object resultObj = null;
		Map currentMap = map;

		String[] pathArray = path.split("\\.");
		for (int i = 0, length = pathArray.length; i < length; i++) {
			String key = pathArray[i];
			int index = -1;
			if (Pattern.matches(".+\\[\\d+\\]", key)) {
				int left = key.lastIndexOf("[");
				int right = key.lastIndexOf("]");
				String indexStr = key.substring(left + 1, right);
				index = NumberUtils.toInt(indexStr, -1);
				key = key.substring(0, left);
			}

			Object obj = currentMap.get(key);
			if (obj == null && StringUtils.isNumeric(key)) {
				obj = currentMap.get(Long.parseLong(key));
			}

			if (index >= 0) {
				if (obj instanceof List) {
					List tmpList = (List) obj;
					if (CollectionUtils.isNotEmpty(tmpList)) {
						obj = tmpList.get(index);
					} else {
						return null;
					}
				} else {
					return null;
				}
			}

			if (i == length - 1) {
				resultObj = obj;
			} else if (obj instanceof Map) {
				currentMap = (Map) obj;
			} else {
				return null;
			}
		}

		return resultObj;
	}

	public static String getStringForPath(Map map, String path) throws Exception {
		Object obj = getObjForPath(map, path);
		if (obj != null) {
			return obj.toString();
		}
		return null;
	}

	public static String getStringForPath(Map map, String path, String defaultValue) throws Exception {
		Object obj = getObjForPath(map, path);
		if (obj != null) {
			return obj.toString();
		}
		return defaultValue;
	}

	public static long getLongForPath(Map map, String path, long defaultValue) throws Exception {
		Object obj = getObjForPath(map, path);
		if (obj != null) {
			return Long.parseLong(obj.toString());
		}
		return defaultValue;
	}

	public static long getLongForPath(Map map, String path) throws Exception {
		return getLongForPath(map, path, 0L);
	}

	public static Map getMapForPath(Map map, String path) throws Exception {
		Object obj = getObjForPath(map, path);
		if (obj != null && obj instanceof Map) {
			return (Map) obj;
		}
		return new HashMap();
	}

	public static List getListForPath(Map map, String path) throws Exception {
		Object obj = getObjForPath(map, path);
		if (obj != null && obj instanceof List) {
			return (List) obj;
		}
		return new ArrayList();
	}

	public static List<String> getListStrForListMap(List<Map> listMap, Object key) throws Exception {
		List<String> returnList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(listMap)) {
			for (Map map : listMap) {
				Object value = map.get(key);
				returnList.add(value != null ? value.toString() : "");
			}
		}

		return returnList;
	}

	public static List getListForListMap(List<Map> listMap, Object key) throws Exception {
		List returnList = new ArrayList();
		if (CollectionUtils.isNotEmpty(listMap)) {
			for (Map map : listMap) {
				Object value = map.get(key);
				returnList.add(value);
			}
		}
		return returnList;
	}

	public static Map<String, List<Map>> classifyForList(List<Map<String, Object>> list, String key) throws Exception {
		Map<String, List<Map>> retMap = new HashMap<>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (Map map : list) {
				String value = getString(map, key);
				List<Map> tmpList = retMap.getOrDefault(value, new ArrayList<>());
				tmpList.add(map);
				retMap.put(value, tmpList);
			}
		}
		return retMap;
	}

	public static Map of(Object key, Object value) throws Exception {
		Map map = new HashMap();
		map.put(key, value);
		return map;
	}

	public static String isEmptyOfVal(Map source, String key) throws Exception {
		if (isNotEmpty(source)) {
			Object val = source.get(key);
			if (val == null || StringUtils.isBlank(val.toString())) {
				return "参数[" + key + "]为空!";
			}
			return "";
		}
		return "参数为空!";
	}
}
