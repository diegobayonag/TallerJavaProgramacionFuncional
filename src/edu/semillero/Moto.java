package edu.semillero;

public class Moto extends Vehicle{

	public Moto(String _brand, String _series, int _model, String _CC, double _price, String _color) {
		super(_brand, _series, _model, _price, _color);
		this._CC = _CC;
	}

	private String _CC;

	public String get_CC() {
		return _CC;
	}

	public void setCC(String _cC) {
		this._CC = _cC;
	}
	
}
