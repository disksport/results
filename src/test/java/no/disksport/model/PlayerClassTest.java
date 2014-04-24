/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package no.disksport.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class PlayerClassTest {

    @Test
    public void testPDGAClassName() {
        assertEquals("MPO",PlayerClass.OPEN_MEN.getPDGAClassName());
        assertEquals("MPE",PlayerClass.SENIOR_LEGEND.getPDGAClassName());
        assertEquals("MA1",PlayerClass.ADVANCED.getPDGAClassName());
    }

}
