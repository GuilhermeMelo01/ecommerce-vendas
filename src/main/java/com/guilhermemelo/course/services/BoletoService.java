package com.guilhermemelo.course.services;

import com.guilhermemelo.course.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instantePedido){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instantePedido);
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        pagto.setDataVencimento(calendar.getTime());
    }
}
