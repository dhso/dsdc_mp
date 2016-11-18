package frame.kit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.dreampie.route.core.Params;

public class MapKit {
	public static HashMap<String, Object> params2Map(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (String key : params.getNames()) {
			map.put(key, params.get(key));
		}
		return map;
	}

	public static ArrayList<Entry<?, String>> sortSring(Map<?, String> hashMap) {
		ArrayList<Entry<?, String>> arrayList = new ArrayList<Entry<?, String>>(hashMap.entrySet());
		Collections.sort(arrayList, new Comparator<Map.Entry<?, String>>() {
			public int compare(Map.Entry<?, String> map1, Map.Entry<?, String> map2) {
				return map1.getValue().compareTo(map2.getValue());
			}
		});
		return arrayList;
	}

	public static ArrayList<Entry<?, Integer>> sortInteger(Map<?, Integer> hashMap) {
		ArrayList<Entry<?, Integer>> arrayList = new ArrayList<Entry<?, Integer>>(hashMap.entrySet());
		Collections.sort(arrayList, new Comparator<Map.Entry<?, Integer>>() {
			public int compare(Map.Entry<?, Integer> map1, Map.Entry<?, Integer> map2) {
				return (map2.getValue() - map1.getValue());
			}
		});
		return arrayList;
	}

	public static ArrayList<Entry<?, Float>> sortFloat(Map<?, Float> hashMap) {
		ArrayList<Entry<?, Float>> arrayList = new ArrayList<Entry<?, Float>>(hashMap.entrySet());
		Collections.sort(arrayList, new Comparator<Map.Entry<?, Float>>() {
			public int compare(Map.Entry<?, Float> map1, Map.Entry<?, Float> map2) {
				return ((map2.getValue() - map1.getValue() == 0) ? 0 : (map2.getValue() - map1.getValue() > 0) ? 1 : -1);
			}
		});
		return arrayList;
	}

	public static ArrayList<Entry<?, Double>> sortDouble(Map<?, Double> hashMap) {
		ArrayList<Entry<?, Double>> arrayList = new ArrayList<Entry<?, Double>>(hashMap.entrySet());
		Collections.sort(arrayList, new Comparator<Map.Entry<?, Double>>() {
			public int compare(Map.Entry<?, Double> map1, Map.Entry<?, Double> map2) {
				return ((map2.getValue() - map1.getValue() == 0) ? 0 : (map2.getValue() - map1.getValue() > 0) ? 1 : -1);
			}
		});
		return arrayList;
	}
}
