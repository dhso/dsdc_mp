package resource.qas.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.dreampie.common.util.properties.Proper;
import cn.dreampie.orm.Model;
import cn.dreampie.orm.annotation.Table;
import frame.kit.MapKit;
import frame.kit.StringKit;
import module.model.ConfModel;

/**
 * Created by hadong on 15-1-19.
 */
@Table(name = "qa_content", cached = true)
public class QA extends Model<QA> {
	public static final QA dao = new QA();

	public List<QA> getMultiKeys(List<String> keys) {
		Map<QA, Integer> sortMap = new HashMap<QA, Integer>();
		Map<QA, Integer> betterMap = new HashMap<QA, Integer>();
		if (keys.isEmpty()) {
			return null;
		}
		for (String key : keys) {
			List<QA> qasWords = find("select * from qa_content qc left join qa_type qt on qt.qat_id = qc.qac_type where qc.qac_question like '%" + StringKit.sqlSafe(key) + "%'");
			for (QA qasWord : qasWords) {
				Boolean isExist = false;
				for (QA qasWordSortMap : sortMap.keySet()) {
					if (qasWordSortMap.get("qac_id", Long.class) == qasWord.get("qac_id", Long.class)) {
						sortMap.put(qasWordSortMap, sortMap.get(qasWordSortMap).intValue() + 1);
						if (sortMap.get(qasWordSortMap) >= Integer.valueOf(ConfModel.dao.findCfgValueByKey("qa.hit"))) {
							betterMap.put(qasWordSortMap, sortMap.get(qasWordSortMap));
						}
						isExist = true;
					}
				}
				if (!isExist) {
					sortMap.put(qasWord, 1);
				}
			}
		}
		List<QA> result = new ArrayList<QA>();
		if (!betterMap.isEmpty()) {
			sortMap = betterMap;
		}
		for (Entry<?, Integer> entry : MapKit.sortInteger(sortMap)) {
			result.add((QA) entry.getKey());
		}
		return result;
	}

	public QA getKeyById(Long id) {
		return findFirst("select * from qa_content qc left join qa_type qt on qt.qat_id = qc.qac_type where qc.qac_id = ?", id);
	}
}
