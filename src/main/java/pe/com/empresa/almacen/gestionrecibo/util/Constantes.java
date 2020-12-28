package pe.com.empresa.almacen.gestionrecibo.util;

public class Constantes {
	
	public static final String CHARSET = ";charset=UTF-8";
	
	public static final String DATOS_HEADER_INCOMPLETOS = "Datos de Cabecera incompletos, VERIFICAR:";
	public static final String DATOS_BODY_INCOMPLETOS = "Datos de Body incompletos, VERIFICAR:";
	
	public static final String SIZE = "El campo debe de tener como maximo {max} caracteres";
	public static final String NOT_NULL = "El campo debe de tener un valor valido";

	public static final String VACIO = "";
	public static final String FORMATOFECHACABECERA = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String FORMATOFECHADEFAULT = "yyyy-MM-dd HH:mm:ss";

	public static final String IDTRANSACCION = "idTransaccion";
	public static final String USUARIO = "usuario";
	public static final String APLICACION = "aplicacion";

	public static final String PUNTO = ".";
	public static final String GUION = "-";
	public static final String PUNTO_COMA = ";";
	public static final String BARRA = "\\|";
	public static final String SLASH = "/";
	public static final String IGUAL = "=";
	public static final String ESPACIO = " ";
	public static final String CORCHETE_IZQ = "[";
	public static final String CORCHETE_DER = "]";
	public static final String APPLICATION_JSON = "application/json";
	public static final String ID_TXT = " idTx=";

	public static final String SEPARADOR = "-----------------------------------------------------------------------------";
	public static final String SALTO_LINEA = "\n";
	public static final String PARAMETROSENTRADA = " Parametros de entrada: ";
	public static final String PARAMETROSSALIDA = " Parametros de salida: ";
	public static final String PARAMETROSHEADER = " Header Request:";
	public static final String PARAMETROSBODY = "Body Request: ";
	public static final String PARAMETROSOBLIGATORIOS = "----0. Validar parametros obligatorios ------";
	public static final String VALIDACIONPARAMETROSCORRECTOS = " Validacion correcta de parametros de entrada";
	public static final String VALIDACIONPARAMETROSINCORRECTOS = " Parametros de entrada no cumple validacion";
	public static final String VALIDACIONINCORRECTA = "Validacion incorrecta";
	public static final String DEVOLVERRESPONSE = " Response a devolver: ";
	public static final String ERROR_EXCEPTION = "Error Exception: ";
	public static final String TIEMPO = " Tiempo total de proceso (ms): ";

	public static final String INICIO_METODO = "[INICIO del metodo ";
	public static final String FIN_METODO = "[FIN del metodo ";

	public static final String INICIO_ACT = "[INICIO] [";
	public static final String FIN_ACT = "[FIN] [";

	public static final String ACT1_OBT_AREAS = "Actividad 1. Obtener recibos]";
	public static final String ACT1_OBT_AREA = "Actividad 1. Obtener recibo]";
	public static final String ACT1_CREAR_AREA = "Actividad 1. Crear recibo]";
	public static final String ACT1_ACTUALIZAR_AREA = "Actividad 1. Actualizar recibo]";
	public static final String ACT1_ELIMINAR_AREA = "Actividad 1. Eliminar recibo]";

	public static final String LOG_1P = "{}";
	public static final String LOG_2P = "{} {}";
	public static final String LOG_3P = "{} {} {}";
	public static final String LOG_4P = "{} {} {} {}";
	public static final String LOG_5P = "{} {} {} {} {}";
	public static final String LOG_6P = "{} {} {} {} {} {}";


	public static final String BASE_PATH = "/api/empresa/almacen/recibo";
	public static final String METODO_LISTAR = "/listarRecibos";
	public static final String METODO_BUSCAR = "/buscarRecibo";
	public static final String METODO_CREAR = "/crearRecibo";
	public static final String METODO_ACTUALIZAR = "/actualizarRecibo";
	public static final String METODO_ELIMINAR = "/eliminarRecibo";
	
	public static final int INT_CERO = 0;
	public static final int INT_UNO = 1;
	
	public static final String STR_CERO = "0";
	public static final String STR_UNO = "1";
}
