package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.models.Group;
import br.com.thallyta.algafood.models.assembler.request.GroupRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.response.GroupResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.requests.GroupRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.GroupResponseDTO;
import br.com.thallyta.algafood.repositories.GroupRepository;
import br.com.thallyta.algafood.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupResponseDTOAssembler assembler;

    @Autowired
    private GroupRequestDTODisassembler disassembler;

    @GetMapping
    public List<GroupResponseDTO> getAll(){
        List<Group> groups = groupService.getAll();
        return assembler.toCollectionModel(groups);
    }

    @GetMapping("/{id}")
    public GroupResponseDTO getById(@PathVariable Long id){
        Group group =  groupService.findOrFail(id);
        return assembler.toGroupResponse(group);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public GroupResponseDTO create(@RequestBody @Valid GroupRequestDTO groupRequestDTO) {
        Group group = disassembler.toDomainObject(groupRequestDTO);
        return assembler.toGroupResponse(groupService.save(group));
    }

    @PutMapping("/{id}")
    public GroupResponseDTO update(@PathVariable Long id, @RequestBody @Valid GroupRequestDTO groupRequestDTO) {
        Group groupFound =  groupService.findOrFail(id);
        disassembler.copyToDomainObject(groupRequestDTO, groupFound);
        return assembler.toGroupResponse(groupService.save(groupFound));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        groupService.delete(id);
    }

}
