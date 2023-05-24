package br.com.krossft.SpringAngular.dto;

import br.com.krossft.SpringAngular.models.Hero;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class HeroDto {


    @NotBlank(message = "NOME: não pode ser branco o nulo")
    @Length(min = 3, max = 45, message = "NOME: deverá ter mínimo {min} e máximo {max} caracteres")
    private String name;

//    @NotBlank(message = "PODER: não poder ser branco o nulo")
//    @Length(min = 5, max = 60, message = "PODER deverá ter mínimo {min} e máximo {max} caracteres")
//    private String power;

    @NotNull(message = "não pode ser branco, nulo")
    private Integer power;

    @NotBlank(message = "não pode ser branco, nulo")
    @Email(message = "email em formato incorreto")
    private String email;

    public HeroDto() {

    }


//    public Hero transformParaObjeto(){
//
//        return new Hero(name, power, email);
//    }
}
