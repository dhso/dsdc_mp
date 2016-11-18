package resource;

import cn.dreampie.common.util.json.Jsoner;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.core.Resource;
import cn.dreampie.security.Subject;
import frame.kit.MapKit;

/**
 * Created by hadong on 15-1-16.
 */
@API("/api")
public class ApiResource extends Resource {

	/**
	 * 获取当前登录的用户ID
	 * 
	 * @return
	 */
	public Integer getCurrentUserId() {
		Integer uid = null;
		if (null != Subject.getPrincipal()) {
			uid = Integer.valueOf(Subject.getPrincipal().getModel().get("id").toString());
		}
		return uid;
	}

	/**
	 * 将请求携带的数据转成model对象
	 * 
	 * @param clazz
	 * @return
	 */
	public <T> T getModelParams(Class<T> clazz) {
		return Jsoner.toObject(Jsoner.toJSON(MapKit.params2Map(getParams())), clazz);
	}
}
