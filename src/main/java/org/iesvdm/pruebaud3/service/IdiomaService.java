package org.iesvdm.pruebaud3.service;

import org.iesvdm.pruebaud3.dao.DAO;
import org.iesvdm.pruebaud3.modelo.Idioma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class IdiomaService {
    @Autowired
    private DAO<Idioma> idiomaDAO;
    public List<Idioma> listAll(){
        return idiomaDAO.getAll();
    }
    public Optional<Idioma> find(int id){
        return idiomaDAO.find(id);
    }
    public void delete(int id){
        idiomaDAO.delete(id);
    }
    public void update(Idioma c){
        idiomaDAO.update(c);
    }
}
