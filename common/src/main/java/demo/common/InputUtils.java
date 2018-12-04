package demo.common;


import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Copyright: Copyright (c) 2018 Asiainfo
 *
 * @ClassName: InputUtils
 * @Description: 用来处理入参
 * @version: v1.0.0
 * @author: zhangwx7
 * @date: 2018-11-16 09:43:12
 * <p>
 * Modification History:
 * Date             Author          Version            Description
 *-----------------------------------------------------------------
 * 2018-11-16       zhangwx7        v1.0.0             新增类
 */
public class InputUtils {
	private InputUtils() {}

	/**
	 * Copyright: Copyright (c) 2018 Asiainfo
	 *
	 * @ClassName: NotNull
	 * @Description: 定义非null的方法
	 * @version: v1.0.0
	 * @author: zhangwx7
	 * @date: 2018-11-16 09:50:11
	 * <p>
	 * Modification History:
	 * Date             Author          Version            Description
	 *-----------------------------------------------------------------
	 * 2018-11-16       zhangwx7        v1.0.0             新增类
	 */
	public static class NotNull {
		public static String getString(Map map, String key) throws Exception {
			return getStringCond(map, key, true);
		}
		public static String getStringCond(Map map, String key, boolean isCheck) throws Exception {
			String str = MapUtils.getString(map, key);
			if (isCheck && StringUtils.isBlank(str)) {
				throw new Exception("参数["+key+"]为空!");
			}
			return str;
		}
	}

	/**
	 * Copyright: Copyright (c) 2018 Asiainfo
	 *
	 * @ClassName: Enum
	 * @Description: 定义枚举范围之内
	 * @version: v1.0.0
	 * @author: zhangwx7
	 * @date: 2018-11-16 09:50:58
	 * <p>
	 * Modification History:
	 * Date             Author          Version            Description
	 *-----------------------------------------------------------------
	 * 2018-11-16       zhangwx7        v1.0.0             新增类
	 */
	public static class InEnum {
		public static String getString(Map map, String key, String enumSet) throws Exception {
			return getStringCond(map, key, enumSet, true);
		}
		public static String getStringCond(Map map, String key, String enumSet, boolean isCheck) throws Exception {
			if (StringUtils.isBlank(enumSet)) {
				throw new Exception("枚举为空!");
			}
			String val = MapUtils.getString(map, key);
			if (enumSet.contains(val)) {
				throw new Exception("参数["+key+"]不在{"+enumSet+"}之内!");
			}
			return val;
		}
	}

}
