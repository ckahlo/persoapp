/**
 * 
 * COPYRIGHT (C) 2010, 2011, 2012, 2013, 2014 AGETO Innovation GmbH
 * 
 * Authors Christian Kahlo, Ralf Wondratschek
 * 
 * All Rights Reserved.
 * 
 * Contact: PersoApp, http://www.persoapp.de
 * 
 * @version 1.0, 30.07.2013 13:50:47
 * 
 *          This file is part of PersoApp.
 * 
 *          PersoApp is free software: you can redistribute it and/or modify it
 *          under the terms of the GNU Lesser General Public License as
 *          published by the Free Software Foundation, either version 3 of the
 *          License, or (at your option) any later version.
 * 
 *          PersoApp is distributed in the hope that it will be useful, but
 *          WITHOUT ANY WARRANTY; without even the implied warranty of
 *          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *          Lesser General Public License for more details.
 * 
 *          You should have received a copy of the GNU Lesser General Public
 *          License along with PersoApp. If not, see
 *          <http://www.gnu.org/licenses/>.
 * 
 *          Diese Datei ist Teil von PersoApp.
 * 
 *          PersoApp ist Freie Software: Sie können es unter den Bedingungen der
 *          GNU Lesser General Public License, wie von der Free Software
 *          Foundation, Version 3 der Lizenz oder (nach Ihrer Option) jeder
 *          späteren veröffentlichten Version, weiterverbreiten und/oder
 *          modifizieren.
 * 
 *          PersoApp wird in der Hoffnung, dass es nützlich sein wird, aber OHNE
 *          JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
 *          Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN
 *          ZWECK. Siehe die GNU Lesser General Public License für weitere
 *          Details.
 * 
 *          Sie sollten eine Kopie der GNU Lesser General Public License
 *          zusammen mit diesem Programm erhalten haben. Wenn nicht, siehe
 *          <http://www.gnu.org/licenses/>.
 * 
 */
package de.persoapp.core.tests.util;

import iso.std.iso_iec._24727.tech.schema.DIDAuthenticate;
import iso.std.iso_iec._24727.tech.schema.DIDAuthenticateResponse;
import iso.std.iso_iec._24727.tech.schema.DIDAuthenticationDataType;
import iso.std.iso_iec._24727.tech.schema.EAC1InputType;
import iso.std.iso_iec._24727.tech.schema.EAC2InputType;
import iso.std.iso_iec._24727.tech.schema.EACAdditionalInputType;
import iso.std.iso_iec._24727.tech.schema.SAL;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import de.persoapp.core.client.PropertyResolver;
import de.persoapp.core.ws.SALService;

/**
 * <p>
 * Configurable test spy for indirect input and indirect output.
 * </p>
 * 
 * ISO 24727 Service Access Layer web service
 * <p>
 * The Service-Access-Layer web service handles differential identity protocol
 * requests related to the Extended Access Control protocol. The implementation
 * of this service is a reduced function set providing only strictly required
 * implementations of didAuthenticate and EAC protocol messages.
 * </p>
 * 
 * @author Rico Klimsa, 2014.
 */
@WebService(serviceName = "SAL", portName = "SALPort", targetNamespace = "urn:iso:std:iso-iec:24727:tech:schema")
public class TestSALService implements SAL {
	
	public EAC1InputType tmpEac1 = null;
	public EAC2InputType tmpEac2 = null;
	
	public ArrayList<String> nullListEAC1 = null;
	public ArrayList<String> nullListEAC2 = null;
	public ArrayList<String> nullListEAC2b = null;
	
	public ConfigTestcase configFlag = null;
	
	public HashMap<DIDAuthenticate,DIDAuthenticateResponse> response = new HashMap<DIDAuthenticate,DIDAuthenticateResponse>();
	
	private SALService sal = new SALService();
	
	/**
	 * injected local web service context providing access to message contexts
	 */
	@Resource
	protected final WebServiceContext	wsCtx	= null;

	/**
	 * container initialization hook
	 */
	@PostConstruct
	public final void init() {
		System.out.println("INIT called: " + wsCtx);
	}

	/**
	 * local message bundle for user interaction
	 */
	private static final PropertyResolver.Bundle	textBundle	= PropertyResolver.getBundle("text_core");

	/**
	 * DIDAuthenticate transports higher level authentication protocol messages
	 * in conjunction with a scope, differential identity name, connection and
	 * slot handle.
	 * 
	 * @param parameters
	 *            - EAC protocol message as {@link DIDAuthenticationDataType}
	 *            embedded in {@link DIDAuthenticate}
	 * @return The returned EAC protocol response as
	 *         {@link DIDAuthenticationDataType} embedded in
	 *         {@link DIDAuthenticate}
	 */
	@Override
	// enhace WSS-runtime to be able to remove
	@WebMethod(operationName = "DIDAuthenticate", action = "urn:iso:std:iso-iec:24727:tech:schema:DIDAuthenticate")
	@WebResult(name = "DIDAuthenticateResponse", targetNamespace = "urn:iso:std:iso-iec:24727:tech:schema", partName = "parameters")
	public iso.std.iso_iec._24727.tech.schema.DIDAuthenticateResponse didAuthenticate(
			final iso.std.iso_iec._24727.tech.schema.DIDAuthenticate parameters) {
		
		try {
			//set the web service context in the real SALService
			Field field = sal.getClass().getDeclaredField("wsCtx");
			field.setAccessible(true);
			field.set(sal, this.wsCtx);
			field.setAccessible(false);
			
			if(parameters.getAuthenticationProtocolData() instanceof EAC1InputType && nullListEAC1!=null) {
				EAC1InputType eac1 = ((EAC1InputType)parameters.getAuthenticationProtocolData());
				this.makeNull(eac1, nullListEAC1);
				parameters.setAuthenticationProtocolData(eac1);
				nullListEAC1 = null;
			}
			
			if(parameters.getAuthenticationProtocolData() instanceof EAC2InputType && nullListEAC2!=null) {
				EAC2InputType eac2= ((EAC2InputType)parameters.getAuthenticationProtocolData());
				this.makeNull(eac2, nullListEAC2);
				parameters.setAuthenticationProtocolData(eac2);
				nullListEAC2 = null;
			}
			
			if(parameters.getAuthenticationProtocolData() instanceof EACAdditionalInputType && nullListEAC2b!=null) {
				EACAdditionalInputType eait = new EACAdditionalInputType();
				this.makeNull(eait, nullListEAC2b);
				parameters.setAuthenticationProtocolData(eait);
				nullListEAC2b = null;
			}
			
			if(tmpEac1 !=null && parameters.getAuthenticationProtocolData() instanceof EAC1InputType) {
				replaceArgument((EAC1InputType)parameters.getAuthenticationProtocolData(),tmpEac1);
				tmpEac1 =null;
			}
			if(tmpEac2 !=null && parameters.getAuthenticationProtocolData() instanceof EAC2InputType) {
				replaceArgument((EAC2InputType)parameters.getAuthenticationProtocolData(),tmpEac2);
				tmpEac2 =null;
			}			
			
		} catch (final SecurityException e) {
			e.printStackTrace();
		} catch (final IllegalArgumentException e) {
			e.printStackTrace();
		} catch (final IllegalAccessException e) {
			e.printStackTrace();
		} catch (final NoSuchFieldException e) {
			e.printStackTrace();
		} 
		if(configFlag!=null) {
			switch(configFlag) {
				case RENEW_DATA_FIRST_PHASE_OF_EAC:{
					if(parameters.getAuthenticationProtocolData() instanceof EAC1InputType) {
						configFlag = null;
						parameters.setAuthenticationProtocolData(new EAC1InputType());
						break;
					}
				}
				case RENEW_DATA_SECOND_PHASE_OF_EAC: {
					if(parameters.getAuthenticationProtocolData() instanceof EAC2InputType){
						configFlag = null;
						parameters.setAuthenticationProtocolData(new EAC2InputType());
						break;				
					}
				}
				case RENEW_DATA_ADDITIONAL_PHASE_OF_EAC: {
					if(parameters.getAuthenticationProtocolData() instanceof EACAdditionalInputType) {
						configFlag = null;
						parameters.setAuthenticationProtocolData(new EACAdditionalInputType());
						break;
					}
				}
				case UNKNOWN_AUTH_PROT_DATA: {
					configFlag = null;
					parameters.setAuthenticationProtocolData(new DIDAuthenticationDataType());
					break;
				}
				case DELETE_DATA: {
					configFlag = null;
					parameters.setAuthenticationProtocolData(null);
					break;
				}
				default: break;
			}
		}
		
		iso.std.iso_iec._24727.tech.schema.DIDAuthenticateResponse response = null;
		response = sal.didAuthenticate(parameters);

		if(response!=null){
			if(parameters!=null){
				this.response.put(parameters, response); //place parameters with return value				
			}
			else
			{
				this.response.put(new DIDAuthenticate(),response);//Just one response in error case.
			}
		}
		return response;
	}
	
	
	/**
	 * @param authenticationProtocolData
	 * @param tmpEac22
	 * @throws SecurityException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private void replaceArgument(EAC2InputType authenticationProtocolData,
			EAC2InputType tmpEac2) throws IllegalArgumentException, IllegalAccessException, SecurityException {
		Field[] fields = authenticationProtocolData.getClass().getDeclaredFields();
		Field[] fieldstmp = tmpEac2.getClass().getDeclaredFields();
		for(Field field: fieldstmp){
			field.setAccessible(true);
			if(field.get(tmpEac2)!=null){
				for(Field f: fields){
					if(f.getName().equals(field.getName())) {
						f.setAccessible(true);
						f.set(authenticationProtocolData, f.get(tmpEac2));
						f.setAccessible(false);
					}
				}
			}
			field.setAccessible(false);
		}		
	}


	/**
	 * @param authenticationProtocolData
	 * @param tmpEac12
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private void replaceArgument(EAC1InputType authenticationProtocolData,
			EAC1InputType tmpEac1) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = authenticationProtocolData.getClass().getDeclaredFields();
		Field[] fieldstmp = tmpEac1.getClass().getDeclaredFields();
		for(Field field: fieldstmp){
			field.setAccessible(true);
			if(field.get(tmpEac1)!=null){
				for(Field f: fields){
					if(f.getName().equals(field.getName())) {
						f.setAccessible(true);
						f.set(authenticationProtocolData, f.get(tmpEac1));
						f.setAccessible(false);
					}
				}
			}
			field.setAccessible(false);
		}
		
	}


	private void makeNull(EAC1InputType eac1, List<String> nullList) throws IllegalArgumentException, IllegalAccessException
	{
		Field[] fields = eac1.getClass().getDeclaredFields();
		
		for(Field f : fields) {
			f.setAccessible(true);
			
			for(String s : nullListEAC1) {
				if(s.equals(f.getName())){
					f.set(eac1, null);
				}
			}
			f.setAccessible(false);
		}
	}
	
	private void makeNull(EAC2InputType eac2, List<String> nullList) throws IllegalArgumentException, IllegalAccessException
	{
		Field[] fields = eac2.getClass().getDeclaredFields();
		
		for(Field f : fields) {
			f.setAccessible(true);
			
			for(String s : nullListEAC2) {
				if(s.equals(f.getName())){
					f.set(eac2, null);
				}
			}
			f.setAccessible(false);
		}
	}	
	
	private void makeNull(EACAdditionalInputType eait, List<String> nullList) throws IllegalArgumentException, IllegalAccessException
	{
		Field[] fields = eait.getClass().getDeclaredFields();
		
		for(Field f : fields) {
			f.setAccessible(true);
			
			for(String s : nullListEAC2b) {
				if(s.equals(f.getName())){
					f.set(eait, null);
				}
			}
			f.setAccessible(false);
		}
	}		
}
