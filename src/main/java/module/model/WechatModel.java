package module.model;

import com.alibaba.fastjson.JSON;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.UserApi;

import frame.plugin.tablebind.Table;
import module.entity.WxCustomer;

@SuppressWarnings("serial")
@Table(value = "wx_customer", pkName = "wc_openid")
public class WechatModel extends Model<WechatModel> {
	public static final WechatModel dao = new WechatModel();

	public WechatModel findCustomerByOpenid(String openid) {
		return WechatModel.dao.findById(openid);
	}

	public void subscribe(String openid, String timestamp) {
		WechatModel customer = WechatModel.dao.findCustomerByOpenid(openid);
		if (null != customer) {
			customer.set("wc_subscribe", 1).update();
			return;
		}
		ApiResult apiResult = UserApi.getUserInfo(openid);
		if (apiResult.isSucceed()) {
			WxCustomer wxCustomer = JSON.parseObject(apiResult.getJson(), WxCustomer.class);
			Db.update("insert into wx_customer values (?,?,?,?,?,?,?,?,?,?,?,?,?)", wxCustomer.getOpenid(), wxCustomer.getNickname(), wxCustomer.getSex(), wxCustomer.getCity(), wxCustomer.getCountry(), wxCustomer.getProvince(), wxCustomer.getLanguage(), wxCustomer.getHeadimgurl(), wxCustomer.getSubscribe_time(), wxCustomer.getUnionid(), wxCustomer.getRemark(), wxCustomer.getGroupid(), 1);
		} else {
			Db.update("insert into wx_customer(wc_openid,wc_subscribe_time,wc_subscribe) values (?,?,?)", openid, timestamp, 1);
		}

	}

	public void unSubscribe(String openid) {
		WechatModel customer = WechatModel.dao.findCustomerByOpenid(openid);
		if (null != customer) {
			customer.set("wc_subscribe", 0).update();
		}
	}
}
