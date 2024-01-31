package org.iesvdm.pruebaud3.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.pruebaud3.modelo.Categoria;
import org.iesvdm.pruebaud3.modelo.Idioma;
import org.iesvdm.pruebaud3.modelo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Slf4j
@Repository
public class PeliculaDAOImplement implements DAO<Pelicula> {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void create(Pelicula pelicula) {
        String sql= """
                insert into pelicula (
                titulo,
                descripcion,
                fecha_lanzamiento,
                id_idioma,
                duracion_alquiler,
                rental_rate,
                duracion,
                replacement_cost) values(?,?,?,?,?,?,?,?)
                """;
        KeyHolder k = new GeneratedKeyHolder();
        int row=jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql,new String[]{"id"});
            int idx = 1;
            ps.setString(idx++,pelicula.getTitulo());
            ps.setString(idx++,pelicula.getDescripcion());
            ps.setDate(idx++,pelicula.getFecha_lanzamiento());
            ps.setInt(idx++,pelicula.getIdioma().getId_idioma());
            ps.setInt(idx++,pelicula.getDucarion_alquiler());
            ps.setDouble(idx++,pelicula.getRental_rate());
            ps.setInt(idx++,pelicula.getDuracion());
            ps.setBigDecimal(idx,pelicula.getReplacement_cost());
            return ps;
        },k);
        pelicula.setId_pelicula(k.getKey().intValue());
        log.info("Insertados {} registros.", row);


        row=jdbcTemplate.update("insert into pelicula_categoria ( id_pelicula, id_categoria) values(?,?)",pelicula.getId_pelicula(),pelicula.getCategoria().getId_categoria());
        log.info("Insertados {} registros.", row);

    }

    @Override
    public List<Pelicula> getAll() {
        List<Pelicula> p= jdbcTemplate.query("select * from pelicula",(this::obtain));
        log.info("Devueltos {} registros.", p.size());
        return p;
    }

    @Override
    public Optional<Pelicula> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Pelicula pelicula) {

    }

    @Override
    public void delete(long id) {

    }
    private Pelicula obtain(ResultSet rs, int rows) throws SQLException {
        return new Pelicula(
                rs.getInt("id_pelicula"),
                rs.getString("titulo"),
                rs.getString("descripcion"),
                rs.getDate("fecha_lanzamiento"),
                jdbcTemplate.queryForObject("Select * from idioma where id_idioma=?" ,
                        (rs1, rowNum1) -> new Idioma(rs1.getInt("id_idioma"),
                                rs1.getString("nombre"),
                                new Date(rs1.getTimestamp("ultima_actualizacion").getTime()))
                        , rs.getInt("id_idioma")),
                rs.getInt("duracion_alquiler"),
                rs.getDouble("rental_rate"),
                rs.getInt("duracion"),
                rs.getBigDecimal("replacement_cost"),
                new Date(rs.getTimestamp("ultima_actualizacion").getTime()),
                jdbcTemplate.queryForObject("Select categoria.* from categoria join pelicula_categoria on categoria.id_categoria = pelicula_categoria.id_categoria where pelicula_categoria.id_pelicula=?" ,
                        (rs1, rowNum1) -> new Categoria(rs1.getInt("id_categoria"),
                                rs1.getString("nombre"),
                                new Date(rs1.getTimestamp("ultima_actualizacion").getTime())
                        ),rs.getInt("id_pelicula"))
                );
    }
}

