import java.util.*;

public class MapSort {

	/**
	 * This method is used to sort hash table by values 
	 * @param table input hash table that need to be sorted 
	 * @return array list which sorted, represent hash table of input
	 */
	@SuppressWarnings("rawtypes")
	public static Map<Site, Integer> sortByValue(Hashtable<Site, Integer> table, int number) {
		@SuppressWarnings("unchecked")
		/* transfer to list and sort it */
		List<Map.Entry<Site, Integer>> list = new ArrayList(table.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Site, Integer>>(){
			public int compare(Map.Entry<Site, Integer> o1, Map.Entry<Site, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});

		/* get the top (number) sorted Map */
		Map<Site, Integer> res = new LinkedHashMap<Site, Integer>();
		int i = 0;
		for (Iterator iter = list.iterator(); iter.hasNext() && i < number;) {
			Map.Entry entry = (Map.Entry) iter.next();
			res.put((Site)entry.getKey(), (Integer)entry.getValue());
			i++;
		}
		return res;
	}
}
