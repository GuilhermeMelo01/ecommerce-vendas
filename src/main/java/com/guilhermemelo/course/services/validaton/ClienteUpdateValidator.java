package com.guilhermemelo.course.services.validaton;

import com.guilhermemelo.course.domain.Cliente;
import com.guilhermemelo.course.dto.ClienteDto;
import com.guilhermemelo.course.dto.ClienteNewDto;
import com.guilhermemelo.course.enums.TipoCliente;
import com.guilhermemelo.course.repositories.ClienteRepository;
import com.guilhermemelo.course.resources.exception.FieldMessage;
import com.guilhermemelo.course.services.validaton.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDto> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDto objDto, ConstraintValidatorContext context) {

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)){
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