package com.adoptme.api.estabelecimento.service;

import com.adoptme.api.estabelecimento.domain.Estabelecimento;
import com.adoptme.api.estabelecimento.domain.TipoEstabelecimento;
import com.adoptme.api.estabelecimento.dto.EstabelecimentoRequestDTO;
import com.adoptme.api.estabelecimento.dto.EstabelecimentoResponseDTO;
import com.adoptme.api.estabelecimento.mapper.EstabelecimentoMapper;
import com.adoptme.api.estabelecimento.repository.EstabelecimentoRepository;
import com.adoptme.api.usuario.domain.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstabelecimentoService {

    private final EstabelecimentoRepository estabelecimentoRepository;
    private final EstabelecimentoMapper estabelecimentoMapper;

    public EstabelecimentoService(EstabelecimentoRepository estabelecimentoRepository,
                                  EstabelecimentoMapper estabelecimentoMapper) {
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.estabelecimentoMapper = estabelecimentoMapper;
    }

    public EstabelecimentoResponseDTO criar(EstabelecimentoRequestDTO dto) {
        Estabelecimento estabelecimento = estabelecimentoMapper.toEntity(dto);

        if (dto.getUsuarioId() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(dto.getUsuarioId());
            estabelecimento.setUsuario(usuario);
        }

        return estabelecimentoMapper.toDto(estabelecimentoRepository.save(estabelecimento));
    }

    public List<EstabelecimentoResponseDTO> listarTodos() {
        return estabelecimentoMapper.toDtoList(estabelecimentoRepository.findAll());
    }

    public EstabelecimentoResponseDTO buscarPorId(Long id) {
        return estabelecimentoRepository.findById(id)
                .map(estabelecimentoMapper::toDto)
                .orElse(null);
    }

    public List<EstabelecimentoResponseDTO> buscarPorNome(String nome) {
        return estabelecimentoMapper.toDtoList(estabelecimentoRepository.findByNomeContainingIgnoreCase(nome));
    }

    public List<EstabelecimentoResponseDTO> buscarPorTipo(TipoEstabelecimento tipo) {
        return estabelecimentoMapper.toDtoList(estabelecimentoRepository.findByTipo(tipo));
    }

    public List<EstabelecimentoResponseDTO> buscar24Horas() {
        return estabelecimentoMapper.toDtoList(estabelecimentoRepository.findByEh24hrs(true));
    }

    public List<EstabelecimentoResponseDTO> buscarPorUsuario(Long usuarioId) {
        return estabelecimentoMapper.toDtoList(estabelecimentoRepository.findByUsuarioId(usuarioId));
    }

    public void deletar(Long id) {
        estabelecimentoRepository.deleteById(id);
    }
}