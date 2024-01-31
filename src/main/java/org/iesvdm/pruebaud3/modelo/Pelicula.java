package org.iesvdm.pruebaud3.modelo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula {
    int id_pelicula;

    @NotNull(message = "{titulo} {no.nulo}.")
    @NotBlank(message = "{titulo} {no.vacio}.")
    @Size(min = 3,message = "{minimo} {min} {caracter.plural}.")
    String titulo;

    @Size(max = 300,message = "{maximo} {max} {caracter.plural}.")
    String descripcion;

    @NotNull(message = "{fecha_lanzamiento} {no.nulo}.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date fecha_lanzamiento;

    @NotNull(message = "{idioma} {no.nulo}.")
    Idioma idioma;


    int ducarion_alquiler;

    @PositiveOrZero(message = "{no.negative}")
    double rental_rate;

    @Min(value=1, message = "{minimo} {value}.")
    int duracion;

    @NotNull(message = "{replacement_cost} {no.nulo}.")
    @DecimalMin(value="19.99", inclusive = true,message = "{minimo} {value}.")
    BigDecimal replacement_cost;
    Date ultima_actualizacion;
    @NotNull(message = "{categoria} {no.nulo}.")
    Categoria categoria;
}
