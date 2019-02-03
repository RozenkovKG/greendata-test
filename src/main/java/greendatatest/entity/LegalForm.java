package greendatatest.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "legalform")
public class LegalForm {

    @Id
    @Column(name = "legalform_id", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "leagalform_name", nullable = false, length = 100, unique = true)
    private String name;

    public LegalForm() {
    }

    public LegalForm(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
