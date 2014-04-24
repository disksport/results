/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package no.disksport.rest;

import no.disksport.model.Player;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
@Stateless
public class PlayerResourceImpl implements PlayerResource {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Player> getPlayers() {
        return entityManager.createQuery("select p from Player p order by p.id", Player.class).getResultList();
    }

    @Override
    public void removePlayer(Long playerId) {
        final Player player = entityManager.find(Player.class, playerId);
        if(player != null)
            entityManager.remove(player);
        else
            throw new NoResultException("No player with id: "+playerId+" found.");
    }

    @Override
    public Player persistPlayer(Player player) {
        entityManager.persist(player);
        return player;
    }
}
