package com.example.demo.services;

import com.example.demo.entities.Rate;
import com.example.demo.entities.Valute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class XMLParseBean {

    private final Logger logger = LoggerFactory.getLogger(XMLParseBean.class);
    private ValuteService valuteService;
    private RateService rateService;

    @Autowired
    public void setRateService(RateService rateService) {
        this.rateService = rateService;
    }

    @Autowired
    public void setValuteService(ValuteService valuteService) {
        this.valuteService = valuteService;
    }

    public void parseValCurs() {
        try {
            NodeList nodeList = initDocument().getElementsByTagName("Valute");
            List<Valute> valutes = new ArrayList<>();
            List<Rate> rates = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    valutes.add(createValute(elem));
                    rates.add(createRate(elem));
                }
            }
            rates.add(new Rate(1, new BigDecimal(1), "RUBID", Date.valueOf(LocalDate.now().minus(1, ChronoUnit.DAYS))));
            valuteService.saveAll(valutes);
            rateService.saveAll(rates);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void init() {
        parseValCurs();
    }

    private Document initDocument() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("http://www.cbr.ru/scripts/XML_daily.asp");
        doc.getDocumentElement().normalize();
        return doc;
    }

    private Valute createValute(Element elem) {
        return new Valute(
                elem.getAttribute("ID"),
                Integer.parseInt(elem.getElementsByTagName("NumCode").item(0).getTextContent()),
                elem.getElementsByTagName("CharCode").item(0).getTextContent(),
                elem.getElementsByTagName("Name").item(0).getTextContent());
    }

    private Rate createRate(Element elem) throws ParseException {
        Element valCursElem = (Element) elem.getParentNode();
        return new Rate(
                Integer.parseInt(elem.getElementsByTagName("Nominal").item(0).getTextContent()),
                new BigDecimal(elem.getElementsByTagName("Value").item(0).getTextContent().replace(',', '.')),
                elem.getAttribute("ID"),
                new Date(new SimpleDateFormat("dd.MM.yyyy").parse(valCursElem.getAttribute("Date")).getTime()));
    }
}
