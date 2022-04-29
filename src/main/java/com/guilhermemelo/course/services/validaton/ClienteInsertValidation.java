package com.guilhermemelo.course.services.validaton;

import com.guilhermemelo.course.domain.Cliente;
import com.guilhermemelo.course.dto.ClienteNewDto;
import com.guilhermemelo.course.enums.TipoCliente;
import com.guilhermemelo.course.repositories.ClienteRepository;
import com.guilhermemelo.course.resources.exception.FieldMessage;
import com.guilhermemelo.course.services.validaton.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidation implements ConstraintValidator<ClienteInsert, ClienteNewDto> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCpnj", "CPF invalido!"));
        }

        if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ invalido!"));
        }

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if (aux != null){
            list.add(new FieldMessage("email","Email ja existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}