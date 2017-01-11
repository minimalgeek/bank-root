package hu.farago.data.edgar;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import hu.farago.repo.model.entity.mongo.embedded.edgar.EdgarXML;

@Component
public class XMLToEdgarConverter {

	private JAXBContext jaxbContext;
	private Unmarshaller jaxbUnmarshaller;
	
	public EdgarXML convertXMLToEdgar(File xmlFile) throws JAXBException {
		
		if (jaxbContext == null) {
			jaxbContext = JAXBContext.newInstance(EdgarXML.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		}
		
		EdgarXML edgar = (EdgarXML) jaxbUnmarshaller.unmarshal(xmlFile);
		
		return edgar;
	}
	
	public EdgarXML convertXMLToEdgar(String xmlContent) throws JAXBException {
		
		if (jaxbContext == null) {
			jaxbContext = JAXBContext.newInstance(EdgarXML.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		}
		
		EdgarXML edgar = (EdgarXML) jaxbUnmarshaller.unmarshal(IOUtils.toInputStream(xmlContent));
		
		return edgar;
	}
	
}
