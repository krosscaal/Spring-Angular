package br.com.krossft.SpringAngular.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "hero_power")
public class Power implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Getter
    @Setter
    private Integer id;

    @Setter
    @Getter
    @Column
    @NotBlank(message = "não poder ser vazio o nulo")
    private String power;

    public Power() {
    }
}
