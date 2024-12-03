import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ExempleIntrospectionDynamique {
    private static Method getDetailsMethod;
    private static String details;

    public static void main(String[] args) {
        try {
            // Créer une instance de Personne
            Class<?> clazz = Class.forName("Personne");
            Object person = clazz.getDeclaredConstructor(String.class, int.class).newInstance("Alice", 30);

            // Appel de la méthode getDeclaredMethod
            getDetailsMethod = clazz.getDeclaredMethod("getDetails");
            details = (String) getDetailsMethod.invoke(person);

            // Affichage avant modification
            System.out.println("Détails avant modification : " + details);

            // Accéder et modifier un champ privé
            Field nameField = clazz.getDeclaredField("name");
            nameField.setAccessible(true); // Rendre le champ accessible
            nameField.set(person, "Bob");

            Field ageField = clazz.getDeclaredField("age");
            ageField.setAccessible(true);
            ageField.set(person, 25);

            // Appel de la méthode getDeclaredMethod
            getDetailsMethod = clazz.getDeclaredMethod("getDetails");
            details = (String) getDetailsMethod.invoke(person);

            // Affichage après modification
            System.out.println("Détails après modification : " + details);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
