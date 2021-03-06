/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package no.disksport.rest;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import no.disksport.model.Club;
import no.disksport.model.Municipalicy;
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

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
@RunWith(Arquillian.class)
public class PlayerResourceTest {

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
       return ShrinkWrap.create(WebArchive.class)
             .addClasses(Player.class, PlayerClass.class, PlayerResource.class,
                     PlayerResourceImpl.class, ResultsApp.class, Club.class, Municipalicy.class)
             .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    private WebTarget target;

    @ArquillianResource
    private URL base;

    @Before
    public void setUpClass() throws MalformedURLException {
        Client client = ClientBuilder.newClient();
        client.register(Player.class);
        target = client.target(URI.create(new URL(base, "rest/player").toExternalForm()));
    }

    @Test
    public void testCreatePlayer() {
        Player p = new Player();
        p.setFirstName("store");
        p.setLastName("petterson");
        p.setPdga("1234");
        p.setActive(true);
        p.setPlayerClass(PlayerClass.ADVANCED);

        Response result = target.request()
                .accept(MediaType.APPLICATION_XML)
                .buildPost(Entity.xml(p))
                .invoke();

        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());

        Player player = result.readEntity(Player.class);
        assertEquals(p.getFirstName(), player.getFirstName());
    }

    @Test
    public void testListPlayers() throws MalformedURLException {
        Player p = new Player();
        p.setFirstName("esben");
        p.setLastName("moglad");
        p.setPdga("1234");
        p.setActive(true);
        p.setPlayerClass(PlayerClass.ADVANCED);

        Response result = target
                .request()
                .accept(MediaType.APPLICATION_XML)
                .buildPost(Entity.xml(p))
                .invoke();

        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());

        WebTarget target2 = ClientBuilder
                .newClient()
                .register(Player.class)
                .target(URI.create(new URL(base, "rest/player").toExternalForm()));

        List<Player> players = target2
                .request()
                .accept(MediaType.APPLICATION_XML)
                .get(new GenericType<List<Player>>(){});

        assertEquals(2, players.size());
    }

    @Test
    public void findByFirstName() throws MalformedURLException {
        WebTarget target = ClientBuilder
                .newClient()
                .register(Player.class)
                .target(URI.create(new URL(base, "rest/player").toExternalForm()));

        target.resolveTemplate("{firstName}", "store");

        List<Player> players = target
                .request()
                .accept(MediaType.APPLICATION_XML)
                .get(new GenericType<List<Player>>(){});

        assertEquals("petterson", players.get(0).getLastName());
    }


}
