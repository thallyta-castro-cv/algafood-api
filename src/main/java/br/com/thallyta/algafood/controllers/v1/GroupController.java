package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.core.security.CheckSecurity;
import br.com.thallyta.algafood.models.Group;
import br.com.thallyta.algafood.models.assembler.v1.request.GroupRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.v1.response.GroupResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.v1.requests.GroupRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.GroupResponseDTO;
import br.com.thallyta.algafood.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupResponseDTOAssembler assembler;

    @Autowired
    private GroupRequestDTODisassembler disassembler;

    @GetMapping
    @CheckSecurity.UserGroupsPermissions.CanGet
    public CollectionModel<GroupResponseDTO> getAll(){
        List<Group> groups = groupService.getAll();
        return assembler.toCollectionModel(groups);
    }

    @GetMapping("/{id}")
    @CheckSecurity.UserGroupsPermissions.CanGet
    public GroupResponseDTO getById(@PathVariable Long id){
        Group group =  groupService.findOrFail(id);
        return assembler.toModel(group);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @CheckSecurity.UserGroupsPermissions.CanEdit
    public GroupResponseDTO create(@RequestBody @Valid GroupRequestDTO groupRequestDTO) {
        Group group = disassembler.toDomainObject(groupRequestDTO);
        return assembler.toModel(groupService.save(group));
    }

    @PutMapping("/{id}")
    @CheckSecurity.UserGroupsPermissions.CanEdit
    public GroupResponseDTO update(@PathVariable Long id, @RequestBody @Valid GroupRequestDTO groupRequestDTO) {
        Group groupFound =  groupService.findOrFail(id);
        disassembler.copyToDomainObject(groupRequestDTO, groupFound);
        return assembler.toModel(groupService.save(groupFound));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.UserGroupsPermissions.CanEdit
    public void delete(@PathVariable Long id) {
        groupService.delete(id);
    }

}
