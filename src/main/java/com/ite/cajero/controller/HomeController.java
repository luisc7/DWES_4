package com.ite.cajero.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ite.cajero.model.beans.Cuenta;
import com.ite.cajero.model.dao.IntDaoCuenta;
import com.ite.cajero.model.dao.IntDaoMovimiento;

@Controller
@RequestMapping("/")
public class HomeController {
	/**
	 * Se accede a todas las rutas desde este único controller
	 */
	
	@Autowired
	private IntDaoCuenta cdao;
	
	@Autowired
	private IntDaoMovimiento mdao;
	
	@GetMapping("/")
	public String inicio(HttpSession sesionCuenta) {
		/**
		 * Redirijo a la ruta de la lista de operaciones si la sesión está activa.
		 * 
		 * Si no, dirijo a la ruta de login
		 */
		
		Cuenta cuentaActiva = (Cuenta) sesionCuenta.getAttribute("cuentaActiva");
		if (cuentaActiva == null)
			return "redirect:/login";
		else
			return "redirect:/operar";
	}
	
	@GetMapping("/login")
	public String login(HttpSession sesionCuenta) {
		/**
		 * Compruebo si al ir a login ya está la sesión activa, si es así redirijo a la ruta de operar directamente
		 * 
		 * Si no está iniciada la sesión de una cuenta, muestro el jsp de login
		 */
		
		Cuenta cuentaActiva = (Cuenta) sesionCuenta.getAttribute("cuentaActiva");
		if (cuentaActiva == null)
			return "login";
		else
			return "redirect:/operar";
	}
	
	@PostMapping("/login")
	public String loginPost(
			HttpSession sesionCuenta,
			Model model,
			@RequestParam("cuenta") int idCuenta) {
		
		/**
		 * Para el envío del formulario de login, comprueba si la cuenta existe:
		 * 
		 * - Si la cuenta existe, almacena en sesión la misma y lleva  ala ruta de operar
		 * - Si al cuenta no existe, refresca el login y muestra mensaje de que la cuenta no existe
		 */
		
		Cuenta cuentaActiva = cdao.findById(idCuenta);
		
		if (cuentaActiva == null) {
			model.addAttribute("mensajeLogin", "La cuenta no existe");
			return "login";
			
		} else {
			sesionCuenta.setAttribute("cuentaActiva", cuentaActiva);			
			return "redirect:/operar";			
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession sesionCuenta) {
		/**
		 * Vacía el atributo de sesión de la cuantaActiva (asigna null)
		 */
		
		sesionCuenta.setAttribute("cuentaActiva", null);
			return "redirect:/";
	}
	
	@GetMapping("/operar")
	public String operar(HttpSession sesionCuenta) {
		/**
		 * Comprueba si la sesión está activa:
		 * - Si está activa, muestra el jsp de operar
		 * - Si no está activa, redirige al login
		 */
		
		Cuenta cuentaActiva = (Cuenta) sesionCuenta.getAttribute("cuentaActiva");
		if (cuentaActiva == null)
			return "redirect:/login";
		else
			return "operar";	
	}
	
	@GetMapping("/ingresar")
	public String ingresar(HttpSession sesionCuenta) {
		/**
		 * Como en las demás rutas que requieren sesión iniciada, comprueba si la sesión está activa:
		 * - Si está activa, muestra el jsp de ingresar
		 * - Si no está activa, redirige al login
		 */
		
		Cuenta cuentaActiva = (Cuenta) sesionCuenta.getAttribute("cuentaActiva");
		if (cuentaActiva == null)
			return "redirect:/login";
		else
			return "ingresar";
	}
	
	@PostMapping("/ingresar")
	public String ingresarPost(
			HttpSession sesionCuenta,
			RedirectAttributes attr,
			Model model,
			@RequestParam("cantidad") double cantidad) {
		/**
		 * El envío del formulario de ingreso comprueba si la cantidad 
		 * a ingresar es menor o igual a cero:
		 * 
		 * - Si lo es, mete en Model un atributo con el mensaje de aviso y refresca el jsp
		 * - Si es > 0, obtiene la cuenta activa de sesión, anota un movimiento de ingreso en mdao,
		 * modifica (incrementa) el saldo de la cuenta, y genera un mensaje de la operación. 
		 * Luego redirige al jsp de Operar con el mensaje de la operación.
		 */
		
		if (cantidad <= 0) {
			String mensajeOperar = "Introduce una cantidad mayor de cero";			
			model.addAttribute("mensajeOperar", mensajeOperar);			
			return "extraer";
			
		} else {
		
			Cuenta cuenta = (Cuenta) sesionCuenta.getAttribute("cuentaActiva");			
			mdao.ingreso(cuenta, cantidad);		
			cdao.addSaldo(cuenta, cantidad);			
			String mensajeOperar = "Has ingresado " + Double.toString(cantidad) + " Euros";			
			attr.addFlashAttribute("mensajeOperar", mensajeOperar);
			
			return "redirect:/operar";
		}
	}
	
	@GetMapping("/extraer")
	public String extraer(HttpSession sesionCuenta) {
		/**
		 * Como en las demás rutas que requieren sesión iniciada, comprueba si la sesión está activa:
		 * - Si está activa, muestra el jsp de extraer
		 * - Si no está activa, redirige al login
		 */
		
		Cuenta cuentaActiva = (Cuenta) sesionCuenta.getAttribute("cuentaActiva");
		if (cuentaActiva == null)
			return "redirect:/login";
		else
			return "extraer";
	}
	
	@PostMapping("/extraer")
	public String extraerPost(
			HttpSession sesionCuenta,
			RedirectAttributes attr,
			Model model,
			@RequestParam("cantidad") double cantidad) {
		/**
		 * El envío del formulario de retirada de efectivo comprueba dos aspectos:
		 * 
		 * 1) Si el saldo de la cuenta es menor que la cantidad de la retirada:
		 * - Si lo es, genera un mensaje de aviso y redirige al jsp de Operar.
		 * 
		 * 2) Si la cantidad a retirar es menor o igual a cero:
		 * 
		 * - Si lo es, mete en Model un atributo con el mensaje de aviso y refresca el jsp
		 * - Si es > 0, obtiene la cuenta activa de sesión, anota un movimiento de ingreso en mdao,
		 * modifica (incrementa) el saldo de la cuenta, y genera un mensaje de la operación. 
		 * Luego redirige al jsp de Operar con el mensaje de la operación.
		 * 
		 * He decidido llevar a Operar en caso de saldo insuficiente, porque 
		 * pienso que si no se puede sacar la cantidad indicada probablemente
		 * no se desee hacer la operación.
		 * 
		 * Sin embargo, he considerado que si la cantidad indicada es menor o igual a cero 
		 * puede deberse a un error del usuario, y por eso permanezco en el mismo jsp.
		 */
		
		Cuenta cuenta = (Cuenta) sesionCuenta.getAttribute("cuentaActiva");
		
		double saldoDisponible = cuenta.getSaldo();
		
		if (saldoDisponible < cantidad) {			
			String mensajeOperar = "Saldo insuficiente";			
			attr.addFlashAttribute("mensajeOperar", mensajeOperar);			
			return "redirect:/operar";
			
		} else if (cantidad <= 0) {
			String mensajeOperar = "Introduce una cantidad mayor de cero";			
			model.addAttribute("mensajeOperar", mensajeOperar);			
			return "extraer";
		}		
		
		else {
			mdao.extraccion(cuenta, cantidad);		
			cdao.decSaldo(cuenta, cantidad);			
			String mensajeOperar = "Has sacado " + Double.toString(cantidad) + " Euros";			
			attr.addFlashAttribute("mensajeOperar", mensajeOperar);			
			return "redirect:/operar";
		}
	}
	
	@GetMapping("/movimientos")
	public String movimientos(
			HttpSession sesionCuenta,
			Model model) {
		/**
		 * Como en las demás rutas que requieren sesión iniciada, comprueba si la sesión 
		 * está activa:
		 * - Si no está activa, redirige al login
		 * - Si está activa, obtiene la lista de los movimientos de la cuenta activa 
		 * llamando al método correspondiente de mdao, al que le decimos la cuenta, y 
		 * mete la lista en un atributo de Model para mostrarlos en el jsp de movimientos
		 * 
		 * Además, obtiene el saldo de la cuenta y lo mete también en Model para mostrarlo
		 * en el jsp
		 */
		
		Cuenta cuentaActiva = (Cuenta) sesionCuenta.getAttribute("cuentaActiva");
		if (cuentaActiva == null)
			return "redirect:/login";
		else
			model.addAttribute("listaMovimientosCuenta", mdao.movimientosCuenta(cuentaActiva));
			model.addAttribute("saldo", cdao.consultarSaldo(cuentaActiva));
			return "movimientos";		
	}
	
	@GetMapping("/transferencia")
	public String transferencia(HttpSession sesionCuenta) {
		/**
		 * Como en las demás rutas que requieren sesión iniciada, comprueba si la sesión 
		 * está activa:
		 * - Si no está activa, redirige al login
		 * - Si está activa, saca el jsp de transferencia
		 */
		Cuenta cuentaActiva = (Cuenta) sesionCuenta.getAttribute("cuentaActiva");
		if (cuentaActiva == null)
			return "redirect:/login";
		else
			return "transferencia";			
	}
	
	@PostMapping("/transferencia")
	public String transferenciaPost(
			HttpSession sesionCuenta,
			RedirectAttributes attr,
			Model model,
			@RequestParam("cantidad") double cantidad,
			@RequestParam("destino") int destino) {
		/**
		 * Para la transferencia, se comprueban:
		 * - cantidad, si es menor o igual a cero refresca jsp y muestra mensaje avisando
		 * 
		 * Luego, se obtienen el saldo de la cuenta de origen, y se obtiene la
		 * cuenta de destino, comprobando:
		 * - Si la cuenta de destino no existe, refresca jsp con mensaje de ello
		 * - Si la cuenta de destino y de origen son la misma, refresca jsp con mensaje
		 * - Si el saldo disponible es menor a la cantidad, redirige a operar y muestra 
		 * mensaje de saldo insuficiente.
		 * 
		 * Si no se incumple ninguna de las comprobaciones anteriores:
		 * - Genera movimiento de emisión de transferencia de cuenta origen
		 * - Sustrae el importe de la transferencia del saldo de cuenta origen
		 * - Genera movimiento de recepción de transferencia de cuenta destino
		 * - Añade el importe de la transferencia al saldo de cuenta origen
		 * 
		 * Los cuatro pasos anteriores con métodos de los respectivos daos de 
		 * la capa de servicio hechos al efecto.
		 * 
		 * Además, genera mensaje y redirige a operar.
		 */
		
		if (cantidad <= 0) {
			String mensajeOperar = "Introduce una cantidad mayor de cero";			
			model.addAttribute("mensajeOperar", mensajeOperar);			
			return "transferencia";			
		}				
		
		Cuenta cuenta = (Cuenta) sesionCuenta.getAttribute("cuentaActiva");		
		double saldoDisponible = cuenta.getSaldo();		
		Cuenta cuentaDestino = cdao.findById(destino);
		
		if (cuentaDestino == null) {
			model.addAttribute("mensajeOperar", "La cuenta de destino no existe");
			return "transferencia";
		} else if (cuenta.equals(cuentaDestino)) {
			model.addAttribute("mensajeOperar", "La cuenta no puede ser la cuenta de origen");
			return "transferencia";
		} else if (saldoDisponible < cantidad) {			
			String mensajeOperar = "Saldo insuficiente";			
			attr.addFlashAttribute("mensajeOperar", mensajeOperar);			
			return "redirect:/operar";
			
		} else {				
			
			mdao.transferenciaEmitida(cuenta, cantidad);		
			cdao.decSaldo(cuenta, cantidad);
			mdao.transferenciaRecibida(cuentaDestino, cantidad);		
			cdao.addSaldo(cuentaDestino, cantidad);
			String mensajeOperar = "Has transferido " + Double.toString(cantidad) + " Euros" + " a la cuenta " + Integer.toString(cuentaDestino.getIdCuenta());			
			attr.addFlashAttribute("mensajeOperar", mensajeOperar);			
			return "redirect:/operar";
		}
	}
}
