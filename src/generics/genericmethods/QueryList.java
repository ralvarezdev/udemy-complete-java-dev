package generics.genericmethods;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("serial")
public class QueryList<T extends Student & QueryItem> extends ArrayList<T> {
	public static void main(String[] args) {
		QueryList<LPAStudent> lpaStudents = new QueryList<>();

		for (int i = 0; i < 20; i++)
			lpaStudents.add(new LPAStudent());

		System.out.println("Ordered students:");
		lpaStudents.sort(Comparator.naturalOrder());
		lpaStudents.printList();

		String percCompletion = "50";
		System.out.println("Students who are less than or equal to " + percCompletion + "% done their course");

		var matches = lpaStudents.getMatches("PERCENTCOMPLETE", percCompletion);
		matches.printList();

		System.out.println("Ordered by how much they have done their course:");
		matches.sort(new LPAStudentComparator());
		matches.printList();

	}

	public QueryList() {
	}

	public QueryList(List<T> items) {
		super(items);
	}

	public static <S extends QueryItem> List<S> getMatches(List<S> items, String field, String value) {

		List<S> matches = new ArrayList<>();
		for (var item : items)
			if (item.matchFieldValue(field, value))
				matches.add(item);

		return matches;
	}

	public QueryList<T> getMatches(String field, String value) {

		QueryList<T> matches = new QueryList<>();
		for (var item : this)
			if (item.matchFieldValue(field, value))
				matches.add(item);

		return matches;
	}

	public void printList() {
		this.forEach(student -> System.out.println(student));
		System.out.println();
	}
}
