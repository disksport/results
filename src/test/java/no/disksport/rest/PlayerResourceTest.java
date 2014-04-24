/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package no.disksport.rest;

import no.disksport.model.Player;
import no.disksport.model.PlayerClass;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
@RunWith(Arquillian.class)
public class PlayerResourceTest {

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
       return ShrinkWrap.create(WebArchive.class)
             .addClasses(Player.class, PlayerClass.class, PlayerResource.class,
                     PlayerResourceImpl.class, ResultsApp.class);
    }

    private WebTarget target;

    @ArquillianResource
    private URL base;

    @Before
    public void setUpClass() throws MalformedURLException {
        Client client = ClientBuilder.newClient();
        target = client.target(URI.create(new URL(base, "rest/player").toExternalForm()));
    }

    /**
     * Test of getList method, of class MyResource.
     */
    @Test
    public void testCreatePlayer() {
        Player p = new Player();
        p.setFirstName("foo");
        p.setLastName("bar");
        p.setPdga("1234");
        p.setActive(true);
        p.setPlayerClass(PlayerClass.ADVANCED);

        //String result = target.request().post(p);
        //assertEquals("apple", result);
    }
}
