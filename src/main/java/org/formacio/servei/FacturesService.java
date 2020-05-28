package org.formacio.servei;

import java.util.Optional;

import org.formacio.domain.Factura;
import org.formacio.domain.LiniaFactura;
import org.formacio.repositori.FacturesRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FacturesService {

	/*
	 * Aquest metode ha de carregar la factura amb id idFactura i afegir una nova linia amb les dades
	 * passades (producte i totalProducte)
	 * 
	 * S'ha de retornar la factura modificada
	 * 
	 * Per implementar aquest metode necessitareu una referencia (dependencia) a FacturesRepositori
	 */
	@Autowired
	FacturesRepositori repoFacts;
	
	
	
	public Factura afegirProducte (long idFactura, String producte, int totalProducte) {
		
		Optional<Factura> fact = repoFacts.findById(idFactura);
		LiniaFactura linia = new LiniaFactura();
		
		if (fact.isPresent()) {
			linia.setProducte(producte);
			linia.setTotal(totalProducte);
			fact.get().getLinies().add(linia);
			repoFacts.save(fact.get());
			
			return fact.get();
		}
		else {
			return null;
		}
		
	}
}
