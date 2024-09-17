package com.example.springteste.controllers;


import com.example.springteste.dtos.ContasRecordDto;
import com.example.springteste.dtos.UsuariosRecordDto;
import com.example.springteste.models.ContasModel;
import com.example.springteste.models.UsuariosModel;
import com.example.springteste.repositories.ContasRepository;
import com.example.springteste.repositories.UsuariosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class ContasController {

@Autowired
private ContasRepository contasRepository;

@Autowired
private UsuariosRepository usuariosRepository;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@PostMapping("/contas")
    public ResponseEntity<ContasModel> saveConta (@RequestBody @Valid ContasRecordDto contasRecordDto){
        var contasModel = new ContasModel();
        BeanUtils.copyProperties(contasRecordDto, contasModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(contasRepository.save(contasModel));
}

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/contas")
    public ResponseEntity<List<ContasModel>> getAllContas(){
        return ResponseEntity.status(HttpStatus.OK).body(contasRepository.findAll());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/contas/{id}/usuarios")
    public ResponseEntity<List<UsuariosModel>> getUsuariosByConta(@PathVariable(value = "id") Long id) {
        ContasModel conta = contasRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        List<UsuariosModel> usuariosList = new ArrayList<>(conta.getUsuarios());
        return ResponseEntity.ok(usuariosList);
    }


}




