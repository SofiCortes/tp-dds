package tpaPOIs;

import java.util.ArrayList;

public class BancoJson {
	
	private String banco;
	private double x;
	private double y;
	private String sucursal;
	private String gerente;
	private ArrayList<String> servicios;
	
	public BancoJson (){
		
		
	} 
	
	
	public BancoJson(String banco, double x, double y, String sucursal, String gerente,	ArrayList<String> servicios) {
		this.setBanco(banco);
		this.setX(x);
		this.setY(y);
		this.setSucursal(sucursal);
		this.setGerente(gerente);
		this.setServicios(servicios);
	}


	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getGerente() {
		return gerente;
	}

	public void setGerente(String gerente) {
		this.gerente = gerente;
	}

	public ArrayList<String> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<String> servicios) {
		this.servicios = servicios;
	}

}
