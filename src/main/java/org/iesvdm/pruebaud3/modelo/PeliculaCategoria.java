package org.iesvdm.pruebaud3.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class PeliculaCategoria {
    int id_pelicula;
    int id_categoria;
    Date ultima_actualizacion;
}
