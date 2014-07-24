package be.ordina.utdemo.factoids.model;

/**
 * Fact class: Encapsulate a fact.
 *
 * @author ppoissinger
 *
 */
public class Fact {
    /**
     * Actual Fact content
     */
    private final String content;

    /**
     * Build a new fact
     *
     * @param content
     *            fact content
     */
    public Fact(final String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Fact [content=" + content + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (content == null ? 0 : content.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Fact other = (Fact) obj;
        if (content == null) {
            if (other.content != null) {
                return false;
            }
        } else if (!content.equals(other.content)) {
            return false;
        }
        return true;
    }
}
