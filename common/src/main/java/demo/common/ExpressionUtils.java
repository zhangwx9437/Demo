package demo.common;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExpressionUtils {

	private static Log log = LogFactory.getLog(ExpressionUtils.class);

	private static Map expressMap = new ConcurrentHashMap();
	private static JexlEngine engine = new JexlEngine();

	public static Object evaluate(Map map, String expr) throws Exception {
		// 表达式为空返回null
		if (null == expr || "".equals(expr.trim())) {
			if (log.isInfoEnabled()) {
				log.info("Incoming expression is empty！");
			}
			return null;
		}

		MapContext context = new MapContext(map);

		// 获取表达式对象
		Expression expression = (Expression) expressMap.get(expr);
		if (expression == null) {
			expression = engine.createExpression(expr);
			expressMap.put(expr, expression);
		}

		//计算表达式结果
		Object obj = expression.evaluate(context);

		return obj;
	}
}
