package com.adoptme.api.estabelecimento.service;

import com.adoptme.api.estabelecimento.domain.Estabelecimento;
import com.adoptme.api.estabelecimento.domain.TipoEstabelecimento;
import com.adoptme.api.estabelecimento.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public Estabelecimento criar(Estabelecimento estabelecimento) {
        return estabelecimentoRepository.save(estabelecimento);
    }

    public List<Estabelecimento> listarTodos() {
        return estabelecimentoRepository.findAll();
    }

    public Optional<Estabelecimento> buscarPorId(Long id) {
        return estabelecimentoRepository.findById(id);
    }

    public List<Estabelecimento> buscarPorNome(String nome) {
        return estabelecimentoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Estabelecimento> buscarPorTipo(TipoEstabelecimento tipo) {
        return estabelecimentoRepository.findByTipo(tipo);
    }

    public List<Estabelecimento> buscar24Horas() {
        return estabelecimentoRepository.findByEh24hrs(true);
    }

    public List<Estabelecimento> buscarPorUsuario(Long usuarioId) {
        return estabelecimentoRepository.findByUsuarioId(usuarioId);
    }

    public void deletar(Long id) {
        estabelecimentoRepository.deleteById(id);
    }
}