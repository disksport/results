package no.disksport.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * The player object represents an actual disc golf player
 *
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
@Entity
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "findByFirstName",
                query = "select p from Player p where p.firstName = :firstName"),
        @NamedQuery(name = "findByLastName",
                query = "select p from Player p where p.lastName = :lastName"),
        @NamedQuery(name = "findByPdga",
                query = "select p from Player p where p.pdga = :pdga"),
        @NamedQuery(name = "findActivePlayers",
                query = "select p from Player p where p.active = true")
})
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private boolean active = true;

    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
    private String lastName;

    @Size(min = 4, max = 7)
    @Digits(fraction = 0, integer = 7)
    private String pdga;

    @NotNull
    private PlayerClass playerClass;

    private Club club;

    public PlayerClass getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(PlayerClass playerClass) {
        this.playerClass = playerClass;
    }

    public String getPdga() {
        return pdga;
    }

    public void setPdga(String pdga) {
        this.pdga = pdga;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
