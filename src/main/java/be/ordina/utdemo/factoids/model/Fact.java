package be.ordina.utdemo.factoids.model;

import lombok.Data;

/**
 * Fact class: Encapsulate a fact.
 * 
 * @author ppoissinger
 * 
 */
@Data
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
}
