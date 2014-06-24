package be.ordina.utdemo.factoids.service;

import be.ordina.utdemo.factoids.model.Fact;

/** Fact service: Provide fact(s) to client
 * @author ppoissinger
 *
 */
public interface FactService {
	/** Provide a single fact.
	 * @return a fact
	 */
	Fact getAFact();
}
