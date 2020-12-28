package pe.com.empresa.almacen.gestionrecibo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import pe.com.empresa.almacen.gestionrecibo.canonical.ActualizarReciboRequest;
import pe.com.empresa.almacen.gestionrecibo.canonical.ActualizarReciboResponse;
import pe.com.empresa.almacen.gestionrecibo.canonical.BuscarReciboRequest;
import pe.com.empresa.almacen.gestionrecibo.canonical.BuscarReciboResponse;
import pe.com.empresa.almacen.gestionrecibo.canonical.CrearReciboRequest;
import pe.com.empresa.almacen.gestionrecibo.canonical.CrearReciboResponse;
import pe.com.empresa.almacen.gestionrecibo.canonical.EliminarReciboRequest;
import pe.com.empresa.almacen.gestionrecibo.canonical.EliminarReciboResponse;
import pe.com.empresa.almacen.gestionrecibo.canonical.ListarRecibosResponse;
import pe.com.empresa.almacen.gestionrecibo.canonical.type.ActualizarReciboResponseType;
import pe.com.empresa.almacen.gestionrecibo.canonical.type.BuscarReciboResponseType;
import pe.com.empresa.almacen.gestionrecibo.canonical.type.CrearReciboResponseType;
import pe.com.empresa.almacen.gestionrecibo.canonical.type.EliminarReciboResponseType;
import pe.com.empresa.almacen.gestionrecibo.canonical.type.HeaderRequestType;
import pe.com.empresa.almacen.gestionrecibo.canonical.type.HeaderResponseType;
import pe.com.empresa.almacen.gestionrecibo.canonical.type.ListarRecibosResponseType;
import pe.com.empresa.almacen.gestionrecibo.service.ReciboService;
import pe.com.empresa.almacen.gestionrecibo.util.CastingMapper;
import pe.com.empresa.almacen.gestionrecibo.util.Constantes;
import pe.com.empresa.almacen.gestionrecibo.util.PropiedadesExternas;
import pe.com.empresa.almacen.gestionrecibo.util.Utilitarios;

@RestController
@RequestMapping(Constantes.BASE_PATH)
public class ReciboController {
	private static final Logger log = LoggerFactory.getLogger(ReciboController.class);

	@Autowired
	private PropiedadesExternas p;

	@Autowired
	private ReciboService reciboService;

	@PostMapping(value = Constantes.METODO_LISTAR, consumes = Constantes.APPLICATION_JSON, produces = Constantes.APPLICATION_JSON)
	public ListarRecibosResponse listarRecibos(
			@RequestHeader(name = Constantes.IDTRANSACCION, required = true) String idTransaccion,
			@RequestHeader(name = Constantes.USUARIO, required = true) String usuario, 
			@RequestHeader(name = Constantes.APLICACION, required = true) String aplicacion) 
					throws JsonProcessingException {
		long tiempo = System.currentTimeMillis();
		String metodo = Constantes.METODO_LISTAR;

		HeaderRequestType headerRequest = new HeaderRequestType(idTransaccion, usuario, aplicacion);
		HeaderResponseType headerResponse = new HeaderResponseType();
		ListarRecibosResponseType responseType = new ListarRecibosResponseType();
		ListarRecibosResponse response = new ListarRecibosResponse();

		String idTx = headerRequest.getIdTransaccion();
		String msjTx = Utilitarios.getMsjTx(metodo, idTx);

		String printHeader = null;
		String printRequest = null;
		String printResponse = null;

		Utilitarios.imprimirLogInicioMetodo(msjTx, metodo);

		try {
			printHeader = Utilitarios.printPrettyJSONString(headerRequest);
			printRequest = Utilitarios.printPrettyJSONString(Constantes.VACIO);

			log.info(Constantes.LOG_2P, msjTx, Constantes.PARAMETROSENTRADA);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSHEADER, Constantes.SALTO_LINEA, printHeader);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSBODY, Constantes.SALTO_LINEA, printRequest);

			response = reciboService.listarRecibos(msjTx, headerRequest);
		} catch (Exception e) {
			headerResponse.setCodigoRespuesta(p.idt3Cod);
			headerResponse.setMensajeRespuesta(p.idt3Msj + Constantes.ESPACIO + e.getCause());

			responseType.setHeaderResponse(headerResponse);
			response.setListarRecibosResponse(responseType);

			log.error(Constantes.LOG_2P, msjTx, Utilitarios.getExceptionToMensaje(e));
		} finally {
			response.getListarRecibosResponse().getHeaderResponse().setIdTransaccion(idTx);
			printResponse = Utilitarios.printPrettyJSONString(response);
			tiempo = (System.currentTimeMillis() - tiempo);
			Utilitarios.imprimirLogFinMetodo(msjTx, metodo, printResponse, tiempo);
		}

		return response;
	}

	@PostMapping(value = Constantes.METODO_BUSCAR, consumes = Constantes.APPLICATION_JSON, produces = Constantes.APPLICATION_JSON)
	public BuscarReciboResponse buscarRecibo(
			@RequestHeader(name = Constantes.IDTRANSACCION, required = true) String idTransaccion,
			@RequestHeader(name = Constantes.USUARIO, required = true) String usuario, 
			@RequestHeader(name = Constantes.APLICACION, required = true) String aplicacion,
			@RequestBody BuscarReciboRequest bodyRequest) throws JsonProcessingException {
		long tiempo = System.currentTimeMillis();
		String metodo = Constantes.METODO_BUSCAR;

		HeaderRequestType headerRequest = new HeaderRequestType(idTransaccion, usuario, aplicacion);
		HeaderResponseType headerResponse = new HeaderResponseType();
		BuscarReciboResponseType responseType = new BuscarReciboResponseType();
		BuscarReciboResponse response = new BuscarReciboResponse();

		String idTx = headerRequest.getIdTransaccion();
		String msjTx = Utilitarios.getMsjTx(metodo, idTx);

		String printHeader = null;
		String printRequest = null;
		String printResponse = null;

		Utilitarios.imprimirLogInicioMetodo(msjTx, metodo);

		try {
			printHeader = Utilitarios.printPrettyJSONString(headerRequest);
			printRequest = Utilitarios.printPrettyJSONString(bodyRequest);

			log.info(Constantes.LOG_2P, msjTx, Constantes.PARAMETROSENTRADA);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSHEADER, Constantes.SALTO_LINEA, printHeader);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSBODY, Constantes.SALTO_LINEA, printRequest);

			HeaderResponseType validacionResponse = (HeaderResponseType) CastingMapper.validarParametrosEntrada(msjTx, headerRequest, bodyRequest.getBuscarReciboRequest());

			if (!Constantes.STR_CERO.equals(validacionResponse.getCodigoRespuesta())) {
				log.info(Constantes.LOG_2P, msjTx, Constantes.VALIDACIONPARAMETROSINCORRECTOS);
				headerResponse.setCodigoRespuesta(validacionResponse.getCodigoRespuesta());
				headerResponse.setMensajeRespuesta(validacionResponse.getMensajeRespuesta());
				responseType.setHeaderResponse(headerResponse);
				response.setBuscarReciboResponse(responseType);
			} else {
				log.info(Constantes.LOG_2P, msjTx, Constantes.VALIDACIONPARAMETROSCORRECTOS);
				response = reciboService.buscarRecibo(msjTx, bodyRequest);
			}
		} catch (Exception e) {
			headerResponse.setCodigoRespuesta(p.idt3Cod);
			headerResponse.setMensajeRespuesta(String.format(p.idt3Msj, Utilitarios.getExceptionToMensaje(e)));

			responseType.setHeaderResponse(headerResponse);
			response.setBuscarReciboResponse(responseType);

			log.error(Constantes.LOG_2P, msjTx, Utilitarios.getExceptionToMensaje(e));
		} finally {
			response.getBuscarReciboResponse().getHeaderResponse().setIdTransaccion(idTx);
			printResponse = Utilitarios.printPrettyJSONString(response);
			tiempo = (System.currentTimeMillis() - tiempo);
			Utilitarios.imprimirLogFinMetodo(msjTx, metodo, printResponse, tiempo);
		}

		return response;
	}

	@PostMapping(value = Constantes.METODO_CREAR, consumes = Constantes.APPLICATION_JSON, produces = Constantes.APPLICATION_JSON)
	public CrearReciboResponse crearUsuario(@RequestHeader(name = Constantes.IDTRANSACCION, required = true) String idTransaccion,
			@RequestHeader(name = Constantes.USUARIO, required = true) String usuario, 
			@RequestHeader(name = Constantes.APLICACION, required = true) String aplicacion,
			@RequestBody CrearReciboRequest bodyRequest) throws JsonProcessingException {
		long tiempo = System.currentTimeMillis();
		String metodo = Constantes.METODO_CREAR;

		HeaderRequestType headerRequest = new HeaderRequestType(idTransaccion, usuario, aplicacion);
		HeaderResponseType headerResponse = new HeaderResponseType();
		CrearReciboResponseType responseType = new CrearReciboResponseType();
		CrearReciboResponse response = new CrearReciboResponse();

		String idTx = headerRequest.getIdTransaccion();
		String msjTx = Utilitarios.getMsjTx(metodo, idTx);

		String printHeader = null;
		String printRequest = null;
		String printResponse = null;

		Utilitarios.imprimirLogInicioMetodo(msjTx, metodo);
		
		try {
			printHeader = Utilitarios.printPrettyJSONString(headerRequest);
			printRequest = Utilitarios.printPrettyJSONString(bodyRequest);

			log.info(Constantes.LOG_2P, msjTx, Constantes.PARAMETROSENTRADA);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSHEADER, Constantes.SALTO_LINEA, printHeader);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSBODY, Constantes.SALTO_LINEA, printRequest);

			HeaderResponseType validacionResponse = (HeaderResponseType) CastingMapper.validarParametrosEntrada(msjTx, headerRequest, bodyRequest.getCrearReciboRequest());

			if (!Constantes.STR_CERO.equals(validacionResponse.getCodigoRespuesta())) {
				log.info(Constantes.LOG_2P, msjTx, Constantes.VALIDACIONPARAMETROSINCORRECTOS);
				headerResponse.setCodigoRespuesta(validacionResponse.getCodigoRespuesta());
				headerResponse.setMensajeRespuesta(validacionResponse.getMensajeRespuesta());
				responseType.setHeaderResponse(headerResponse);
				response.setCrearReciboResponse(responseType);
			} else {
				log.info(Constantes.LOG_2P, msjTx, Constantes.VALIDACIONPARAMETROSCORRECTOS);
				response = reciboService.crearRecibo(msjTx, bodyRequest);
			}
		} catch (Exception e) {
			headerResponse.setCodigoRespuesta(p.idt3Cod);
			headerResponse.setMensajeRespuesta(String.format(p.idt3Msj, Utilitarios.getExceptionToMensaje(e)));

			responseType.setHeaderResponse(headerResponse);
			response.setCrearReciboResponse(responseType);

			log.error(Constantes.LOG_2P, msjTx, Utilitarios.getExceptionToMensaje(e));
		} finally {
			response.getCrearReciboResponse().getHeaderResponse().setIdTransaccion(idTx);
			printResponse = Utilitarios.printPrettyJSONString(response);
			tiempo = (System.currentTimeMillis() - tiempo);
			Utilitarios.imprimirLogFinMetodo(msjTx, metodo, printResponse, tiempo);
		}

		return response;
	}

	@PostMapping(value = Constantes.METODO_ACTUALIZAR, consumes = Constantes.APPLICATION_JSON, produces = Constantes.APPLICATION_JSON)
	public ActualizarReciboResponse actualizarRecibo(@RequestHeader(name = Constantes.IDTRANSACCION, required = true) String idTransaccion,
			@RequestHeader(name = Constantes.USUARIO, required = true) String usuario, 
			@RequestHeader(name = Constantes.APLICACION, required = true) String aplicacion,
			@RequestBody ActualizarReciboRequest bodyRequest) throws JsonProcessingException {
		long tiempo = System.currentTimeMillis();
		String metodo = Constantes.METODO_ACTUALIZAR;
		
		HeaderRequestType headerRequest = new HeaderRequestType(idTransaccion, usuario, aplicacion);
		HeaderResponseType headerResponse = new HeaderResponseType();
		ActualizarReciboResponseType responseType = new ActualizarReciboResponseType();
		ActualizarReciboResponse response = new ActualizarReciboResponse();

		String idTx = headerRequest.getIdTransaccion();
		String msjTx = Utilitarios.getMsjTx(metodo, idTx);

		String printHeader = null;
		String printRequest = null;
		String printResponse = null;

		Utilitarios.imprimirLogInicioMetodo(msjTx, metodo);
		
		try {
			printHeader = Utilitarios.printPrettyJSONString(headerRequest);
			printRequest = Utilitarios.printPrettyJSONString(bodyRequest);

			log.info(Constantes.LOG_2P, msjTx, Constantes.PARAMETROSENTRADA);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSHEADER, Constantes.SALTO_LINEA, printHeader);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSBODY, Constantes.SALTO_LINEA, printRequest);

			HeaderResponseType validacionResponse = (HeaderResponseType) CastingMapper.validarParametrosEntrada(msjTx, headerRequest, bodyRequest.getActualizarReciboRequest());

			if (!Constantes.STR_CERO.equals(validacionResponse.getCodigoRespuesta())) {
				log.info(Constantes.LOG_2P, msjTx, Constantes.VALIDACIONPARAMETROSINCORRECTOS);
				headerResponse.setCodigoRespuesta(validacionResponse.getCodigoRespuesta());
				headerResponse.setMensajeRespuesta(validacionResponse.getMensajeRespuesta());
				responseType.setHeaderResponse(headerResponse);
				response.setActualizarReciboResponse(responseType);
			} else {
				log.info(Constantes.LOG_2P, msjTx, Constantes.VALIDACIONPARAMETROSCORRECTOS);
				response = reciboService.actualizarRecibo(msjTx, bodyRequest);
			}
		} catch (Exception e) {
			headerResponse.setCodigoRespuesta(p.idt3Cod);
			headerResponse.setMensajeRespuesta(String.format(p.idt3Msj, Utilitarios.getExceptionToMensaje(e)));

			responseType.setHeaderResponse(headerResponse);
			response.setActualizarReciboResponse(responseType);

			log.error(Constantes.LOG_2P, msjTx, Utilitarios.getExceptionToMensaje(e));
		} finally {
			response.getActualizarReciboResponse().getHeaderResponse().setIdTransaccion(idTx);
			printResponse = Utilitarios.printPrettyJSONString(response);
			tiempo = (System.currentTimeMillis() - tiempo);
			Utilitarios.imprimirLogFinMetodo(msjTx, metodo, printResponse, tiempo);
		}

		return response;
	}

	@PostMapping(value = Constantes.METODO_ELIMINAR, consumes = Constantes.APPLICATION_JSON, produces = Constantes.APPLICATION_JSON)
	public EliminarReciboResponse eliminarPorId(@RequestHeader(name = Constantes.IDTRANSACCION, required = true) String idTransaccion,
			@RequestHeader(name = Constantes.USUARIO, required = true) String usuario, 
			@RequestHeader(name = Constantes.APLICACION, required = true) String aplicacion,
			@RequestBody EliminarReciboRequest bodyRequest) throws JsonProcessingException {
		long tiempo = System.currentTimeMillis();
		String metodo = Constantes.METODO_ELIMINAR;
		
		HeaderRequestType headerRequest = new HeaderRequestType(idTransaccion, usuario, aplicacion);
		HeaderResponseType headerResponse = new HeaderResponseType();
		EliminarReciboResponseType responseType = new EliminarReciboResponseType();
		EliminarReciboResponse response = new EliminarReciboResponse();

		String idTx = headerRequest.getIdTransaccion();
		String msjTx = Utilitarios.getMsjTx(metodo, idTx);

		String printHeader = null;
		String printRequest = null;
		String printResponse = null;

		Utilitarios.imprimirLogInicioMetodo(msjTx, metodo);
		
		try {
			printHeader = Utilitarios.printPrettyJSONString(headerRequest);
			printRequest = Utilitarios.printPrettyJSONString(bodyRequest);

			log.info(Constantes.LOG_2P, msjTx, Constantes.PARAMETROSENTRADA);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSHEADER, Constantes.SALTO_LINEA, printHeader);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSBODY, Constantes.SALTO_LINEA, printRequest);

			HeaderResponseType validacionResponse = (HeaderResponseType) CastingMapper.validarParametrosEntrada(msjTx, headerRequest, bodyRequest.getEliminarReciboRequest());

			if (!Constantes.STR_CERO.equals(validacionResponse.getCodigoRespuesta())) {
				log.info(Constantes.LOG_2P, msjTx, Constantes.VALIDACIONPARAMETROSINCORRECTOS);
				headerResponse.setCodigoRespuesta(validacionResponse.getCodigoRespuesta());
				headerResponse.setMensajeRespuesta(validacionResponse.getMensajeRespuesta());
				responseType.setHeaderResponse(headerResponse);
				response.setEliminarReciboResponse(responseType);
			} else {
				log.info(Constantes.LOG_2P, msjTx, Constantes.VALIDACIONPARAMETROSCORRECTOS);
				response = reciboService.eliminarRecibo(msjTx, bodyRequest);
			}
		} catch (Exception e) {
			headerResponse.setCodigoRespuesta(p.idt3Cod);
			headerResponse.setMensajeRespuesta(String.format(p.idt3Msj, Utilitarios.getExceptionToMensaje(e)));

			responseType.setHeaderResponse(headerResponse);
			response.setEliminarReciboResponse(responseType);

			log.error(Constantes.LOG_2P, msjTx, Utilitarios.getExceptionToMensaje(e));
		} finally {
			response.getEliminarReciboResponse().getHeaderResponse().setIdTransaccion(idTx);
			printResponse = Utilitarios.printPrettyJSONString(response);
			tiempo = (System.currentTimeMillis() - tiempo);
			Utilitarios.imprimirLogFinMetodo(msjTx, metodo, printResponse, tiempo);
		}

		return response;
	}
}
