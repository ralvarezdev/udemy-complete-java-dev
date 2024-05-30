package lambdas;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;

public class MethodReferencesChallenge {
	public static void main(String[] args) {
		String[] names = { "David", "John", "Jesus", "Javier", "Ramon", "Ivana", "Grecia", "Andres" };

		LinkedList<UnaryOperator<String>> funcs = new LinkedList<>();

		// Method Reference to Set First Name to Upper Case
		funcs.add(String::toUpperCase);

		// Lambda Expression to Add First Character of the Middle Name
		funcs.add(s -> s + " " + lambdas.LambdaExpressionChallenge.getRandomChar('A', 'Z') + ".");

		// Method Reference to Reverse the First Name
		funcs.add(s -> s + " " + lambdas.LambdaExpressionChallenge.getReversedString(s.substring(0, s.indexOf(' '))));

		// Modify Names
		modifyNames(funcs, names);

		System.out.println("Modified Names List:");
		lambdas.LambdaExpressionChallenge.printList(Arrays.asList(names));
	}

	private static void modifyNames(List<UnaryOperator<String>> funcs, String[] names) {
		funcs.forEach(func -> {
			Arrays.asList(names).replaceAll(func);
		});
	}
}
