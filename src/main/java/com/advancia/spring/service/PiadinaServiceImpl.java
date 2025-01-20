package com.advancia.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.advancia.spring.soapclient.Piadina;
import com.advancia.spring.soapclient.PiadinaApiSoap;
import com.advancia.spring.soapclient.PiadinaApiSoapService;

@Service
public class PiadinaServiceImpl implements PiadinaService {
	
	@Override
	public List<Piadina> getPiadinas() {
		PiadinaApiSoapService service = new PiadinaApiSoapService();
		PiadinaApiSoap port = service.getPiadinaApiSoapPort();
		
		System.out.println("SOAP API for get piadinas list called.");
		try {
            List<Piadina> piadinas = port.getPiadinas();
            if(piadinas == null) {
                System.out.println("No piadinas received.");
            } else {
                System.out.println("Number of piadina received: " + piadinas.size());
            }
            return piadinas;
        } catch(Exception e) {
            System.out.println("Error while calling the SOAP API: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}