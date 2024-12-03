import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class UMLDiagramGeneratorPlantUML {
    public static void main(String[] args) {
        // Nom de la classe à analyser
        String className = "Personne";

        try {
            // Charger la classe
            Class<?> clazz = Class.forName(className);

            // Construire le diagramme UML au format PlantUML
            StringBuilder umlDiagram = new StringBuilder();
            umlDiagram.append("@startuml\n\n");
            umlDiagram.append("class ").append(clazz.getSimpleName()).append(" {\n");

            // Ajouter les champs (attributs)
            for (Field field : clazz.getDeclaredFields()) {
                umlDiagram.append("  ")
                        .append(visibilityToPlantUML(field.getModifiers())).append(" ")
                        .append(field.getName()).append(" : ")
                        .append(field.getType().getSimpleName()).append("\n");
            }

            // Ajouter les méthodes
            for (Method method : clazz.getDeclaredMethods()) {
                umlDiagram.append("  ")
                        .append(visibilityToPlantUML(method.getModifiers())).append(" ")
                        .append(method.getName()).append("(");

                // Ajouter les paramètres de la méthode
                Class<?>[] params = method.getParameterTypes();
                for (int i = 0; i < params.length; i++) {
                    umlDiagram.append(params[i].getSimpleName());
                    if (i < params.length - 1) umlDiagram.append(", ");
                }
                umlDiagram.append(") : ").append(method.getReturnType().getSimpleName()).append("\n");
            }

            umlDiagram.append("}\n\n");
            umlDiagram.append("@enduml");

            // Écrire le diagramme dans un fichier
            String outputFileName = "diagram.puml";
            try (FileWriter writer = new FileWriter(outputFileName)) {
                writer.write(umlDiagram.toString());
                System.out.println("Diagramme PlantUML généré dans le fichier : " + outputFileName);
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Classe non trouvée : " + className);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
        }
    }

    // Méthode pour traduire les modificateurs en notation PlantUML
    private static String visibilityToPlantUML(int modifiers) {
        if (Modifier.isPublic(modifiers)) return "+";
        if (Modifier.isProtected(modifiers)) return "#";
        if (Modifier.isPrivate(modifiers)) return "-";
        return "~"; // Default/package-private
    }
}
