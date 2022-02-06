package com.ite.cajero.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ite.cajero.model.beans.Cuenta;
import com.ite.cajero.model.repository.IntRepositoryCuenta;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private IntRepositoryCuenta crepo;
	
	@GetMapping("/")
	public String inicio(HttpSession sesionCuenta) {
		
		Cuenta cuentaActiva = (Cuenta) sesionCuenta.getAttribute("cuentaActiva");
		if (cuentaActiva == null)
			return "redirect:/login";
		else
			return "redirect:/operar";		
	}
	
	@GetMapping("/login")
	public String login(HttpSession sesionCuenta) {
		return "login";	
	}
	
	@PostMapping("/login")
	public String loginPost(
			HttpSession sesionCuenta,
			RedirectAttributes attr,
			@RequestParam("cuenta") int idCuenta) {
		
		Cuenta cuentaActiva = crepo.getById(idCuenta);
		
		if (cuentaActiva == null) {
			attr.addFlashAttribute("mensaje", "La cuenta no existe");
			return "redirect:/login";
		} else {
			sesionCuenta.setAttribute("cuentaActiva", cuentaActiva);
			return "operar";			
		}
	}
	
	@GetMapping("/operar")
	public String operar(HttpSession sesionCuenta) {
		return null;		
	}
	
	@GetMapping("/ingresar")
	public String ingresar(HttpSession sesionCuenta) {
		return null;		
	}
	
	@PostMapping("/ingresar")
	public String ingresarPost(HttpSession sesionCuenta) {
		return null;		
	}
	
	@GetMapping("/extraer")
	public String extraer(HttpSession sesionCuenta) {
		return null;		
	}
	
	@PostMapping("/extraer")
	public String extraerPost(HttpSession sesionCuenta) {
		return null;		
	}
	
	@GetMapping("/movimientos")
	public String movimientos(HttpSession sesionCuenta) {
		return null;		
	}
	
	@GetMapping("/transferencia")
	public String transferencia(HttpSession sesionCuenta) {
		return null;		
	}
	
	@PostMapping("/transferencia")
	public String transferenciaPost(HttpSession sesionCuenta) {
		return null;		
	}
}
