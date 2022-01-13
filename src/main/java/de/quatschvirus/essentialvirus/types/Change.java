package de.quatschvirus.essentialvirus.types;

public class Change {
    private final String title;
    private final String[] contents;

    public Change(String title, String[] contents) {
        this.title = title;
        this.contents = contents;
    }

    public String render() {
        StringBuilder out = new StringBuilder();
        out.append(title);
        for (String change : contents) {
            out.append("\n - ").append(change);
        }
        return out.toString();
    }
}
