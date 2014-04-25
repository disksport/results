/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package no.disksport.rest;

import no.disksport.model.Player;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
@Path("/player")
public interface PlayerResource {

    @GET
    @Path("/")
    List<Player> getPlayers();

    @GET
    @Path("/{firstName}")
    List getPlayersByFirstName(@PathParam("firstName") String firstName);

    @DELETE
    @Path("/{id}")
    void removePlayer(@PathParam("id") Long playerId);

    @POST
    @Path("/")
    Player persistPlayer(Player player);

}
