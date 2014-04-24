/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package no.disksport.model;

/**
 * Defines the different player classes
 *
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public enum PlayerClass {
    OPEN_MEN,
    OPEN_WOMEN,
    MASTER_MEN,
    MASTER_WOMEN,
    GRAND_MASTER_MEN,
    GRAND_MASTER_WOMEN,
    SENIOR_GRAND_MASTER_MEN,
    LEGEND,
    SENIOR_LEGEND,
    JUNIOR_1_MEN,
    JUNIOR_1_WOMEN,
    JUNIOR_2_MEN,
    JUNIOR_2_WOMEN,
    INTERMEDIATE,
    ADVANCED,
    RECREATIONAL;

    public String getPDGAClassName() {
        switch (this) {
            case OPEN_MEN : return "MPO";
            case OPEN_WOMEN : return "FPO";
            case MASTER_MEN : return "MPM";
            case MASTER_WOMEN : return "FPM";
            case GRAND_MASTER_MEN : return "MPG";
            case GRAND_MASTER_WOMEN : return "FPG";
            case SENIOR_GRAND_MASTER_MEN : return "MPS";
            case LEGEND : return "MPL";
            case SENIOR_LEGEND : return "MPE";
            case JUNIOR_1_MEN : return "MJ1";
            case JUNIOR_1_WOMEN : return "PJ1";
            case JUNIOR_2_MEN : return "MJ2";
            case JUNIOR_2_WOMEN : return "PJ2";
            case ADVANCED: return "MA1";
            case INTERMEDIATE : return "MA2";
            case RECREATIONAL : return "MA3";
        }
        //should not happen, perhaps throw an exception?
        return "";
    }

}
