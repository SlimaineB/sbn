performance-tester
=========================

Simple showcase of a maven project using the gatling-maven-plugin.

To test it out, simply execute the following command:

    $mvn gatling:test -Dgatling.simulationClass=slim.RampSimulation
    $mvn gatling:test -Dgatling.simulationClass=slim.AtOnceSimulation

or simply:

    $mvn gatling:test

    
    cf : https://gatling.io/docs/2.3/general/simulation_setup/