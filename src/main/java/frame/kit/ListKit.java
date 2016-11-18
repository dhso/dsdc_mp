package frame.kit;

import java.util.List;

public class ListKit {
	@SuppressWarnings("rawtypes")
	public static List subList(List list, Integer from, Integer to) {
		Integer size = list.size();
		if (from > size - 1 || to > size - 1 || to - from >= size) {
			return list;
		}
		return list.subList(from, to);

	}
}
