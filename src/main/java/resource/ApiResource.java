package resource;

import cn.dreampie.common.util.json.Jsoner;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.core.Resource;
import frame.kit.MapKit;

/**
 * Created by hadong on 15-1-16.
 */
@API("/api")
public class ApiResource extends Resource {

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
