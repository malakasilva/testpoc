/*
 * Copyright (c) 2015 SK C&C Co., Ltd. All rights reserved.
 *
 * This software is the confidential and proprietary information of SK C&C.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance wih the terms of the license agreement you entered into
 * with SK C&C.
 */
package com.springwalk.sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * @author sini
 * @date May 18, 2015
 */
@SpringBootApplication
@RestController
public class SampleApplication {
	
	@RequestMapping("/")
	public String home() {	
		return "Invalid URL.";
	}



	@RequestMapping("/mock")
	public ResponseEntity seed(@RequestBody PostBody requestBody) {
		PostgraceDBClient dbClient = new PostgraceDBClient(System.getenv("POSTGRACE_DB_URL"));
		int recordid = dbClient.getLastPageNumber();
		recordid++;
		String strResponse = "{\"errorCode\":\"0\",";
		strResponse += "\"result\":\"OK\",";
		strResponse += "\"recordId\":\" " + recordid + "\",";
		strResponse += "\"modId\":\"0\"";
		strResponse += "}";

		dbClient.setLastPageNumber(recordid);
		
		return new ResponseEntity<String>(strResponse, HttpStatus.OK);
	}
	

	@RequestMapping("/create")
	public String create() {
	    StringBuilder sb = new StringBuilder();
	    BufferedWriter writer = new BufferedWriter(new FileWriter("test.xml"));
	    
	    sb.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\r\n" + 
	    		"<ExportBusRelSales>");
	    
	    for (int i=0;i<10;i++) {
	    
	    sb.append("<smmBusRelTable>\r\n" + 
	    		"    <BROrdAcc>217700</BROrdAcc>\r\n" + 
	    		"    <BRName1>Comimet SA</BRName1>\r\n" + 
	    		"    <BRName2>A l'att. de M. André De Barsy</BRName2>\r\n" + 
	    		"    <BRName3 />\r\n" + 
	    		"    <BRAddress1>Rue Ducale 37</BRAddress1>\r\n" + 
	    		"    <BRAddress2>1000 Bruxelles</BRAddress2>\r\n" + 
	    		"    <BRAddress3 />\r\n" + 
	    		"    <BRAddress4 />\r\n" + 
	    		"    <BRAddress5 />\r\n" + 
	    		"    <BRPO-Box>0</BRPO-Box>\r\n" + 
	    		"    <BRTransfer1>Comimet SA</BRTransfer1>\r\n" + 
	    		"    <BRTransfer2>Rue Ducale 37</BRTransfer2>\r\n" + 
	    		"    <BRTransfer3>1000 Bruxelles</BRTransfer3>\r\n" + 
	    		"    <BRPhone>+32 2 5136515</BRPhone>\r\n" + 
	    		"    <BRZipcode>1000</BRZipcode>\r\n" + 
	    		"    <BRState />\r\n" + 
	    		"    <BRCountry>be</BRCountry>\r\n" + 
	    		"    <BRCountryName>BELGIUM</BRCountryName>\r\n" + 
	    		"    <BRSegment>Company</BRSegment>\r\n" + 
	    		"    <BRHouseNumber>37</BRHouseNumber>\r\n" + 
	    		"    <BRHouseNumberExtra />\r\n" + 
	    		"    <BRSalesId />\r\n" + 
	    		"    <BRQuotId>62063000087</BRQuotId>\r\n" + 
	    		"    <BRStreetName>Rue Ducale</BRStreetName>\r\n" + 
	    		"    <BRCity>Bruxelles</BRCity>\r\n" + 
	    		"    <ContactPerson>\r\n" + 
	    		"      <CTContactPersonId>CP04545646</CTContactPersonId>\r\n" + 
	    		"      <CTFirstname>André</CTFirstname>\r\n" + 
	    		"      <CTLastname>De Barsy</CTLastname>\r\n" + 
	    		"      <CTFunction />\r\n" + 
	    		"      <CTPrefixFunction />\r\n" + 
	    		"      <CTPrefixName>A l'att. de</CTPrefixName>\r\n" + 
	    		"      <CTDepartment />\r\n" + 
	    		"      <CTHeading>Monsieur De Barsy</CTHeading>\r\n" + 
	    		"      <CTTitle />\r\n" + 
	    		"      <CTTitleDescr>M.</CTTitleDescr>\r\n" + 
	    		"      <CTDocuGreet1 />\r\n" + 
	    		"      <CTDocuGreet2 />\r\n" + 
	    		"      <CTWebUserId>10559835752</CTWebUserId>\r\n" + 
	    		"      <CTEmail>adb-comimet@skynet.be</CTEmail>\r\n" + 
	    		"      <CTSalesId />\r\n" + 
	    		"      <CTQuotId>62063000087</CTQuotId>\r\n" + 
	    		"      <CTCommEmail>adb-comimet@skynet.be</CTCommEmail>\r\n" + 
	    		"      <smmQuotationTable>\r\n" + 
	    		"        <QTQuotationId>62063000087</QTQuotationId>\r\n" + 
	    		"        <QTActionCode>WAPLSUBK2041AL</QTActionCode>\r\n" + 
	    		"        <QTPhase>new</QTPhase>\r\n" + 
	    		"        <QTCustGroup>gen</QTCustGroup>\r\n" + 
	    		"        <QTItemIdCrossSelling />\r\n" + 
	    		"        <QTCrossItemItemTxt />\r\n" + 
	    		"        <QTCurr>eur</QTCurr>\r\n" + 
	    		"        <QTLang>fr-be</QTLang>\r\n" + 
	    		"        <QTEmail />\r\n" + 
	    		"        <QTDlvTerm />\r\n" + 
	    		"        <QTCustRef />\r\n" + 
	    		"        <QTType>1 step mailing</QTType>\r\n" + 
	    		"        <QTOrderPool />\r\n" + 
	    		"        <QTDlvTermTxt />\r\n" + 
	    		"        <QTPayment>15id</QTPayment>\r\n" + 
	    		"        <QTPaymTermTxt>Veuillez payer dans les 15 jours suivant la date de facturation.</QTPaymTermTxt>\r\n" + 
	    		"        <QTTaxGroup>dom</QTTaxGroup>\r\n" + 
	    		"        <QTVatNum>BE 0403.230.780</QTVatNum>\r\n" + 
	    		"        <QTEnterpriseNbr>TVA BE 0403.230.780</QTEnterpriseNbr>\r\n" + 
	    		"        <QTFiscalCode />\r\n" + 
	    		"        <QTTaxAmount>5,64</QTTaxAmount>\r\n" + 
	    		"        <QTNetAmount>94,00</QTNetAmount>\r\n" + 
	    		"        <QTInvDate>05-10-2020</QTInvDate>\r\n" + 
	    		"        <QTDueDate>20-10-2020</QTDueDate>\r\n" + 
	    		"        <QTPaymMode>trac</QTPaymMode>\r\n" + 
	    		"        <TaxData>\r\n" + 
	    		"          <TaxValue>6 %</TaxValue>\r\n" + 
	    		"          <TaxBase>94,00</TaxBase>\r\n" + 
	    		"          <TaxAmount>5,64</TaxAmount>\r\n" + 
	    		"        </TaxData>\r\n" + 
	    		"        <smmQuotationLine>\r\n" + 
	    		"          <QLQuotationId>62063000087</QLQuotationId>\r\n" + 
	    		"          <QLItemID>waplsubk</QLItemID>\r\n" + 
	    		"          <QLItemTxt>Élaborer une planification successorale étape par étape</QLItemTxt>\r\n" + 
	    		"          <QLVersion>E01P1</QLVersion>\r\n" + 
	    		"          <QLQty>1,00</QLQty>\r\n" + 
	    		"          <QLSalesUnit>pcs</QLSalesUnit>\r\n" + 
	    		"          <QLUnitTxt>pcs</QLUnitTxt>\r\n" + 
	    		"          <QLSalesPrice>124,00</QLSalesPrice>\r\n" + 
	    		"          <QLStartDate>06-10-2020</QLStartDate>\r\n" + 
	    		"          <QLEndDate>06-10-2020</QLEndDate>\r\n" + 
	    		"          <QLActionCode>waplsubk2041al</QLActionCode>\r\n" + 
	    		"          <QLActionCodeLanguageTxt />\r\n" + 
	    		"          <QLPriceUnit>1,00</QLPriceUnit>\r\n" + 
	    		"          <QLLineDisc>30,00</QLLineDisc>\r\n" + 
	    		"          <QLLinePct>0</QLLinePct>\r\n" + 
	    		"          <QLLineAmt>94,00</QLLineAmt>\r\n" + 
	    		"          <QLTaxAmt>5,64</QLTaxAmt>\r\n" + 
	    		"          <QLMarkup>0</QLMarkup>\r\n" + 
	    		"          <QLTaxGroup>dom</QLTaxGroup>\r\n" + 
	    		"          <QLTaxItemGroup>6g</QLTaxItemGroup>\r\n" + 
	    		"          <QLTaxWriteCode>6 %</QLTaxWriteCode>\r\n" + 
	    		"          <QLInvoiceTxt>Livre : Élaborer une planification successorale étape par étape</QLInvoiceTxt>\r\n" + 
	    		"          <QLtitle7Txt>Élaborer une planification successorale étape par étape</QLtitle7Txt>\r\n" + 
	    		"          <PriceDiscTable>\r\n" + 
	    		"            <DiscountText>Votre remise de promotion</DiscountText>\r\n" + 
	    		"            <DiscountAmount>30,00</DiscountAmount>\r\n" + 
	    		"          </PriceDiscTable>\r\n" + 
	    		"        </smmQuotationLine>\r\n" + 
	    		"      </smmQuotationTable>\r\n" + 
	    		"    </ContactPerson>\r\n" + 
	    		"  </smmBusRelTable>");
	    }
	    sb.append("</ExportBusRelSales>");

	    
	    writer.write(sb.toString());	    
	    writer.close();
	}
	
	@RequestMapping("/get")
	public String getFile() {
		File file = new File("test.xml"); 
		  
		  BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		  String st;
		  StringBuilder sb = new StringBuilder();
		  while ((st = br.readLine()) != null) {
			  sb.append(st); 
		  } 
	
		  return sb.toString();
	}
	
	public static void main(String[] args){		
		SpringApplication.run(SampleApplication.class, args);
	}

}
