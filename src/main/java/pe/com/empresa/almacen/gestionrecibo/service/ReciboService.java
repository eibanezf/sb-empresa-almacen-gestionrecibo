package pe.com.empresa.almacen.gestionrecibo.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import pe.com.empresa.almacen.gestionrecibo.entity.Recibo;
import pe.com.empresa.almacen.gestionrecibo.repository.ReciboRepository;
import pe.com.empresa.almacen.gestionrecibo.util.Constantes;
import pe.com.empresa.almacen.gestionrecibo.util.PropiedadesExternas;
import pe.com.empresa.almacen.gestionrecibo.util.ResponseValidarBean;
import pe.com.empresa.almacen.gestionrecibo.util.Utilitarios;

@Service
public class ReciboService {
	private static final Logger log = LoggerFactory.getLogger(ReciboService.class);

	@Autowired
	private PropiedadesExternas p;

	@Autowired
	ReciboRepository reciboRepository;

	@Transactional(readOnly = true)
	public ListarRecibosResponse listarRecibos(String msjTx, HeaderRequestType headerRequest) {
		HeaderResponseType headerResponse = new HeaderResponseType();
		ListarRecibosResponseType responseType = new ListarRecibosResponseType();
		ListarRecibosResponse response = new ListarRecibosResponse();

		log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
		log.info(Constantes.LOG_2P, msjTx, Constantes.ACT1_OBT_AREAS);
		log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
		
		List<Recibo> recibos = (List<Recibo>) reciboRepository.findAll();

		headerResponse.setCodigoRespuesta(p.idf0Cod);
		headerResponse.setMensajeRespuesta(p.idf0Msj);
		
		responseType.setHeaderResponse(headerResponse);
		responseType.setRecibos(recibos);

		response.setListarRecibosResponse(responseType);
		
		return response;
	}

	@Transactional(readOnly = true)
	public BuscarReciboResponse buscarRecibo(String msjTx, BuscarReciboRequest bodyRequest) {
		HeaderResponseType headerResponse = new HeaderResponseType();
		BuscarReciboResponseType responseType = new BuscarReciboResponseType();
		BuscarReciboResponse response = new BuscarReciboResponse();
		
		log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
		log.info(Constantes.LOG_2P, msjTx, Constantes.ACT1_OBT_AREA);
		log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
		
		ResponseValidarBean responseValidarBean = validarBean(msjTx, bodyRequest);
		
		if(!responseValidarBean.esValido()) {
			headerResponse.setCodigoRespuesta(Constantes.STR_UNO);
			headerResponse.setMensajeRespuesta(responseValidarBean.getMensaje());
			responseType.setHeaderResponse(headerResponse);
			response.setBuscarReciboResponse(responseType);
			
			return response;
		}
		
		Long idRecibo = Long.valueOf(bodyRequest.getBuscarReciboRequest().getIdRecibo());
		
		Recibo recibo = reciboRepository.findById(idRecibo).orElse(null);
		
		if(null == recibo) {
			headerResponse.setCodigoRespuesta(p.idf1Cod);
			headerResponse.setMensajeRespuesta(String.format(p.idf1Msj, idRecibo));
			responseType.setHeaderResponse(headerResponse);
			response.setBuscarReciboResponse(responseType);
			
			return response; 
		}
		
		headerResponse.setCodigoRespuesta(p.idf0Cod);
		headerResponse.setMensajeRespuesta(p.idf0Msj);
		
		responseType.setHeaderResponse(headerResponse);
		responseType.setRecibo(recibo);
		
		response.setBuscarReciboResponse(responseType);
		
		return response;
	}

	@Transactional
	public CrearReciboResponse crearRecibo(String msjTx, CrearReciboRequest bodyRequest) {
		HeaderResponseType headerResponse = new HeaderResponseType();
		CrearReciboResponseType responseType = new CrearReciboResponseType();
		CrearReciboResponse response = new CrearReciboResponse();
		
		log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
		log.info(Constantes.LOG_2P, msjTx, Constantes.ACT1_CREAR_AREA);
		log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
		
		ResponseValidarBean responseValidarBean = validarBean(msjTx, bodyRequest);
		
		if(!responseValidarBean.esValido()) {
			headerResponse.setCodigoRespuesta(Constantes.STR_UNO);
			headerResponse.setMensajeRespuesta(responseValidarBean.getMensaje());
			responseType.setHeaderResponse(headerResponse);
			response.setCrearReciboResponse(responseType);
			
			return response;
		}
		
		Recibo requestRecibo = new Recibo();
		requestRecibo.setFechaSalida(obtenerFechaSalida(bodyRequest.getCrearReciboRequest().getFechaSalida()));
		requestRecibo.setPersonaRecibe(bodyRequest.getCrearReciboRequest().getPersonaRecibe());
		requestRecibo.setPersonaEntrega(bodyRequest.getCrearReciboRequest().getPersonaEntrega());
		requestRecibo.setIdArea(bodyRequest.getCrearReciboRequest().getIdArea());

		Recibo responseRecibo = reciboRepository.save(requestRecibo);
		responseType.setRecibo(responseRecibo);

		headerResponse.setCodigoRespuesta(p.idf0Cod);
		headerResponse.setMensajeRespuesta(p.idf0Msj);

		responseType.setHeaderResponse(headerResponse);
		response.setCrearReciboResponse(responseType);
		
		return response;
	}
	
	@Transactional
	public ActualizarReciboResponse actualizarRecibo(String msjTx, ActualizarReciboRequest bodyRequest) {
		HeaderResponseType headerResponse = new HeaderResponseType();
		ActualizarReciboResponseType responseType = new ActualizarReciboResponseType();
		ActualizarReciboResponse response = new ActualizarReciboResponse();
		
		log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
		log.info(Constantes.LOG_2P, msjTx, Constantes.ACT1_ACTUALIZAR_AREA);
		log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
		
		ResponseValidarBean responseValidarBean = validarBean(msjTx, bodyRequest);
		
		if(!responseValidarBean.esValido()) {
			headerResponse.setCodigoRespuesta(Constantes.STR_UNO);
			headerResponse.setMensajeRespuesta(responseValidarBean.getMensaje());
			responseType.setHeaderResponse(headerResponse);
			response.setActualizarReciboResponse(responseType);
			
			return response;
		}
		
		Recibo requestRecibo = new Recibo();
		requestRecibo.setIdRecibo(bodyRequest.getActualizarReciboRequest().getIdRecibo());
		requestRecibo.setFechaSalida(Utilitarios.stringADate(bodyRequest.getActualizarReciboRequest().getFechaSalida(), "yyyy-MM-dd HH:mm:ss"));
		requestRecibo.setPersonaRecibe(bodyRequest.getActualizarReciboRequest().getPersonaRecibe());
		requestRecibo.setPersonaEntrega(bodyRequest.getActualizarReciboRequest().getPersonaEntrega());
		requestRecibo.setIdArea(bodyRequest.getActualizarReciboRequest().getIdArea());

		Recibo responseRecibo = reciboRepository.save(requestRecibo);
		responseType.setRecibo(responseRecibo);

		headerResponse.setCodigoRespuesta(p.idf0Cod);
		headerResponse.setMensajeRespuesta(p.idf0Msj);

		responseType.setHeaderResponse(headerResponse);
		response.setActualizarReciboResponse(responseType);
		
		return response;
	}

	@Transactional
	public EliminarReciboResponse eliminarRecibo(String msjTx, EliminarReciboRequest bodyRequest) {
		HeaderResponseType headerResponse = new HeaderResponseType();
		EliminarReciboResponseType responseType = new EliminarReciboResponseType();
		EliminarReciboResponse response = new EliminarReciboResponse();

		try {
			log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
			log.info(Constantes.LOG_2P, msjTx, Constantes.ACT1_ELIMINAR_AREA);
			log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
			
			ResponseValidarBean responseValidarBean = validarBean(msjTx, bodyRequest);
			
			if(!responseValidarBean.esValido()) {
				headerResponse.setCodigoRespuesta(Constantes.STR_UNO);
				headerResponse.setMensajeRespuesta(responseValidarBean.getMensaje());
				responseType.setHeaderResponse(headerResponse);
				response.setEliminarReciboResponse(responseType);
				
				return response;
			}
			
			Long idRecibo = Long.valueOf(bodyRequest.getEliminarReciboRequest().getIdRecibo());
			
			Recibo recibo = reciboRepository.findById(idRecibo).orElse(null);
			
			if(null == recibo) {
				headerResponse.setCodigoRespuesta(p.idf1Cod);
				headerResponse.setMensajeRespuesta(String.format(p.idf1Msj, idRecibo));
				responseType.setHeaderResponse(headerResponse);
				response.setEliminarReciboResponse(responseType);
				
				return response; 
			}
			
			reciboRepository.deleteById(idRecibo);
			
			headerResponse.setCodigoRespuesta(p.idf0Cod);
			headerResponse.setMensajeRespuesta(p.idf0Msj);
			
			responseType.setHeaderResponse(headerResponse);
			response.setEliminarReciboResponse(responseType);
		} catch (Exception e) {
			log.error(Constantes.LOG_2P, msjTx, Utilitarios.getExceptionToMensaje(e));			
		}
		
		return response;
	}
	
	public ResponseValidarBean validarBean(String msjTx, Object beanRequest) {
		ResponseValidarBean response = new ResponseValidarBean();
		String mensaje = Constantes.VACIO;
		String atributo = Constantes.VACIO;
		boolean valido = true;
		
		if (beanRequest instanceof BuscarReciboRequest) {
			BuscarReciboRequest request = ((BuscarReciboRequest) beanRequest);
			
			if(Constantes.VACIO.equals(request.getBuscarReciboRequest().getIdRecibo())) {
				atributo = "idRecibo";
				mensaje = Constantes.DATOS_BODY_INCOMPLETOS + atributo + Constantes.PUNTO + Constantes.NOT_NULL;
				valido = false;

				log.info(Constantes.LOG_2P, msjTx, mensaje);
			}
		}
		
		if (beanRequest instanceof CrearReciboRequest) {
			CrearReciboRequest request = ((CrearReciboRequest) beanRequest);
			
			if(Constantes.VACIO.equals(request.getCrearReciboRequest().getFechaSalida()) 
					|| obtenerFechaSalida(request.getCrearReciboRequest().getFechaSalida()) == null ) {
				atributo = "fechaSalida";
				mensaje = Constantes.DATOS_BODY_INCOMPLETOS + atributo + Constantes.PUNTO + Constantes.NOT_NULL;
				valido = false;

				log.info(Constantes.LOG_2P, msjTx, mensaje);
			}
			
			if(Constantes.VACIO.equals(request.getCrearReciboRequest().getPersonaRecibe())) {
				atributo = "personaRecibe";
				mensaje = Constantes.DATOS_BODY_INCOMPLETOS + atributo + Constantes.PUNTO + Constantes.NOT_NULL;
				valido = false;

				log.info(Constantes.LOG_2P, msjTx, mensaje);
			}
			
			if(Constantes.VACIO.equals(request.getCrearReciboRequest().getPersonaEntrega())) {
				atributo = "personaEntrega";
				mensaje = Constantes.DATOS_BODY_INCOMPLETOS + atributo + Constantes.PUNTO + Constantes.NOT_NULL;
				valido = false;

				log.info(Constantes.LOG_2P, msjTx, mensaje);
			}
			
			if(Constantes.VACIO.equals(request.getCrearReciboRequest().getIdArea())) {
				atributo = "idArea";
				mensaje = Constantes.DATOS_BODY_INCOMPLETOS + atributo + Constantes.PUNTO + Constantes.NOT_NULL;
				valido = false;

				log.info(Constantes.LOG_2P, msjTx, mensaje);
			}
		}
		
		if (beanRequest instanceof ActualizarReciboRequest) {
			ActualizarReciboRequest request = ((ActualizarReciboRequest) beanRequest);
			
			if(Constantes.VACIO.equals(request.getActualizarReciboRequest().getFechaSalida())) {
				atributo = "fechaSalida";
				mensaje = Constantes.DATOS_BODY_INCOMPLETOS + atributo + Constantes.PUNTO + Constantes.NOT_NULL;
				valido = false;

				log.info(Constantes.LOG_2P, msjTx, mensaje);
			}
			
			if(Constantes.VACIO.equals(request.getActualizarReciboRequest().getPersonaRecibe())) {
				atributo = "personaRecibe";
				mensaje = Constantes.DATOS_BODY_INCOMPLETOS + atributo + Constantes.PUNTO + Constantes.NOT_NULL;
				valido = false;

				log.info(Constantes.LOG_2P, msjTx, mensaje);
			}
			
			if(Constantes.VACIO.equals(request.getActualizarReciboRequest().getPersonaEntrega())) {
				atributo = "personaEntrega";
				mensaje = Constantes.DATOS_BODY_INCOMPLETOS + atributo + Constantes.PUNTO + Constantes.NOT_NULL;
				valido = false;

				log.info(Constantes.LOG_2P, msjTx, mensaje);
			}
			
			if(Constantes.VACIO.equals(request.getActualizarReciboRequest().getIdArea())) {
				atributo = "idArea";
				mensaje = Constantes.DATOS_BODY_INCOMPLETOS + atributo + Constantes.PUNTO + Constantes.NOT_NULL;
				valido = false;

				log.info(Constantes.LOG_2P, msjTx, mensaje);
			}
		}
		
		if (beanRequest instanceof EliminarReciboRequest) {
			EliminarReciboRequest request = ((EliminarReciboRequest) beanRequest);
			
			if(Constantes.VACIO.equals(request.getEliminarReciboRequest().getIdRecibo())) {
				atributo = "idRecibo";
				mensaje = Constantes.DATOS_BODY_INCOMPLETOS + atributo + Constantes.PUNTO + Constantes.NOT_NULL;
				valido = false;

				log.info(Constantes.LOG_2P, msjTx, mensaje);
			}
		}

		response.setMensaje(mensaje);
		response.setAtributo(atributo);
		response.setValido(valido);
		
		return response;
	}
	
	private Date obtenerFechaSalida(String fechaString) {
		Date response = null;
		
		if(null != fechaString && !Constantes.VACIO.equals(fechaString)) {
			response = Utilitarios.stringADate(fechaString, Constantes.FORMATOFECHADEFAULT);	
		}
		
		return response;
	}
}
