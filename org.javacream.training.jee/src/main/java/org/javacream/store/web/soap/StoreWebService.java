package org.javacream.store.web.soap;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.javacream.store.api.StoreService;
import org.javacream.util.qualifier.Business;

@ApplicationScoped
@WebService(targetNamespace="http://javacream.org", serviceName="StoreService")
public class StoreWebService {//implements WSDL

	@Inject @Business private StoreService storeService;

	@WebMethod(operationName="RetrieveStock")
	public @WebResult(name="Stock") List<Integer> getStock(@WebParam(name="Cat") String category, @WebParam(name="Item")List<String> items) {
		return items.stream().map((item) -> storeService.getStock(category, item)).collect(Collectors.toList());
	}
}
