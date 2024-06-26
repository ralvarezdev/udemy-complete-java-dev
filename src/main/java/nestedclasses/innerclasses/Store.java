package nestedclasses.innerclasses;

public class Store {
    public static void main(String[] args) {
        Meal regularMeal = new Meal();
        Meal USRegularMeal = new Meal(0.68);
        USRegularMeal.addTopping("cheese");
        USRegularMeal.addTopping("bacon");

        System.out.println(regularMeal);
        System.out.println(USRegularMeal);

    }
}
