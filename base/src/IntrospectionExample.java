import java.lang.reflect.Method;

public class IntrospectionExample {
    public static void main(String[] args) {
        // Classe cible pour l'introspection
        Class<?> clazz = String.class;

        System.out.println("Liste des méthodes de la classe " + clazz.getName() + " :");

        // Récupérer toutes les méthodes déclarées dans la classe
        Method[] methods = clazz.getDeclaredMethods();

        // Parcourir et afficher chaque méthode
        for (Method method : methods) {
            System.out.println("- " + method.getName() + " (Retour: " + method.getReturnType().getSimpleName() + ")");
        }
    }
}
