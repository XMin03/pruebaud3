package org.iesvdm.pruebaud3.service;

import org.iesvdm.pruebaud3.dao.DAO;
import org.iesvdm.pruebaud3.modelo.Categoria;
import org.iesvdm.pruebaud3.modelo.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CategoriaService {
    @Autowired
    private DAO<Categoria> categoriaDAO;
    public List<Categoria> listAll(){
        return categoriaDAO.getAll();
    }
    public Optional<Categoria> find(int id){
        return categoriaDAO.find(id);
    }
    public void delete(int id){
        categoriaDAO.delete(id);
    }
    public void update(Categoria c){
        categoriaDAO.update(c);
    }
}
