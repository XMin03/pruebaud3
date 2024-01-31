package org.iesvdm.pruebaud3.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.pruebaud3.modelo.Idioma;
import org.iesvdm.pruebaud3.modelo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Slf4j
@Repository
public class IdiomaDAOImplement implements DAO<Idioma>{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void create(Idioma idioma) {

    }

    @Override
    public List<Idioma> getAll() {
        List<Idioma> i= jdbcTemplate.query("select * from idioma",(this::obtain));
        log.info("Devueltos {} registros.", i.size());
        return i;
    }

    @Override
    public Optional<Idioma> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Idioma idioma) {

    }

    @Override
    public void delete(long id) {

    }
    private Idioma obtain(ResultSet rs, int rows) throws SQLException {
        return new Idioma(
                rs.getInt("id_idioma"),
                rs.getString("nombre"),
                new Date(rs.getTimestamp("ultima_actualizacion").getTime())
            );
    }

}
