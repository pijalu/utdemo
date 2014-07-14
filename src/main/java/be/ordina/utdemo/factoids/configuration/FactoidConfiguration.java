package be.ordina.utdemo.factoids.configuration;

import be.ordina.utdemo.factoids.service.FactService;

public interface FactoidConfiguration {

    FactService getService();

}

