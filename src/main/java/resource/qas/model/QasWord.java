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

/**
 * Created by hadong on 15-1-19.
 */
@Table(name = "qas_words", cached = true)
public class QasWord extends Model<QasWord> {
	public static final QasWord dao = new QasWord();

	public List<QasWord> getMultiKeys(List<String> keys) {
		Map<QasWord, Integer> sortMap = new HashMap<QasWord, Integer>();
		Map<QasWord, Integer> betterMap = new HashMap<QasWord, Integer>();
		if (keys.isEmpty()) {
			return null;
		}
		for (String key : keys) {
			List<QasWord> qasWords = find("select * from qas_words where wod_question like '%" + key + "%'");
			for (QasWord qasWord : qasWords) {
				Boolean isExist = false;
				for (QasWord qasWordSortMap : sortMap.keySet()) {
					if (qasWordSortMap.get("wod_id", Long.class) == qasWord.get("wod_id", Long.class)) {
						sortMap.put(qasWordSortMap, sortMap.get(qasWordSortMap).intValue() + 1);
						if (sortMap.get(qasWordSortMap) >= Proper.getInt("qas.hit")) {
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
		List<QasWord> result = new ArrayList<QasWord>();
		if (!betterMap.isEmpty()) {
			sortMap = betterMap;
		}
		for (Entry<?, Integer> entry : MapKit.sortInteger(sortMap)) {
			result.add((QasWord) entry.getKey());
		}
		return result;
	}

	public QasWord getKeyById(Long id) {
		return findFirst("select * from qas_words where wod_id = ?", id);
	}
}
