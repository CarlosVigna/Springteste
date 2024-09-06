package com.example.springteste.controllers;


import com.example.springteste.dtos.UsuariosRecordDto;
import com.example.springteste.models.UsuariosModel;
import com.example.springteste.repositories.UsuariosRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuariosController {

    @Autowired
    UsuariosRepository usuariosRepository;

    @PostMapping("/usuarios")
    public ResponseEntity<UsuariosModel> saveUsuario(@RequestBody @Valid UsuariosRecordDto usuariosRecordDto) {
        var usuariosModel = new UsuariosModel();
        BeanUtils.copyProperties(usuariosRecordDto, usuariosModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosRepository.save(usuariosModel));
    }


    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuariosModel>> getAllUsuario(){
        return ResponseEntity.status(HttpStatus.OK).body(usuariosRepository.findAll());
    }


    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Object> getOneUsuario(@PathVariable(value = "id") Long id){

        Optional<UsuariosModel> usuarioO = usuariosRepository.findById(id);
        if(usuarioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrardo");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioO.get());
    }


    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Object> updateUsuario (@PathVariable(value = "id") Long id, @RequestBody @Valid UsuariosRecordDto usuariosRecordDto){
        Optional<UsuariosModel> usuariosO = usuariosRepository.findById(id);
        if(usuariosO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrardo");
        }
        var usuariosModel = usuariosO.get();
        BeanUtils.copyProperties(usuariosRecordDto, usuariosModel);
        return ResponseEntity.status(HttpStatus.OK).body(usuariosRepository.save(usuariosModel));
         }


    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Object> deleteUsuario (@PathVariable (value = "id") Long id, @RequestBody @Valid UsuariosRecordDto usuariosRecordDto) {
        Optional<UsuariosModel> usuariosO = usuariosRepository.findById(id);
        if(usuariosO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        usuariosRepository.delete(usuariosO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
    }

}
