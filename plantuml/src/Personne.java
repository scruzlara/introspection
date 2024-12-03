public class Personne {
    private String name;
    private int age;

    public Personne(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Méthode publique
    public String getDetails() {
        return "Nom: " + name + ", Âge: " + age;
    }
}
