package org.iesvdm.pruebaud3.service;

import org.iesvdm.pruebaud3.dao.DAO;
import org.iesvdm.pruebaud3.modelo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PeliculaService {
    @Autowired
    private DAO<Pelicula> peliculaDao;
    public void create(Pelicula c){
        peliculaDao.create(c);
    }
    public List<Pelicula> listAll(){
        return peliculaDao.getAll();
    }
    public Optional<Pelicula> find(int id){
        return peliculaDao.find(id);
    }
    public void delete(int id){
        peliculaDao.delete(id);
    }
    public void update(Pelicula c){
        peliculaDao.update(c);
    }
}
