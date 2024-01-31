package org.iesvdm.pruebaud3.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.pruebaud3.modelo.Categoria;
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
public class CategoriaDAOImplement implements DAO<Categoria>{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void create(Categoria categoria) {

    }

    @Override
    public List<Categoria> getAll() {
        List<Categoria> i= jdbcTemplate.query("select * from categoria",(this::obtain));
        log.info("Devueltos {} registros.", i.size());
        return i;
    }

    @Override
    public Optional<Categoria> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Categoria categoria) {

    }

    @Override
    public void delete(long id) {

    }
    private Categoria obtain(ResultSet rs, int rows) throws SQLException {
        return new Categoria(
                rs.getInt("id_categoria"),
                rs.getString("nombre"),
                new Date(rs.getTimestamp("ultima_actualizacion").getTime())
        );
    }

}
