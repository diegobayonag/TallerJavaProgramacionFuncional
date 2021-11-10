package edu.semillero;

public class Car extends Vehicle {

	private String _doorsQnty;
	private String _transmisionType;
	
	public Car(String _brand, String _series, int _model, double _price,  String _doorsQnty ,String _color, String _transmisionType) {
		super(_brand, _series, _model, _price, _color);
		this._doorsQnty = _doorsQnty;
		this._transmisionType = _transmisionType;
	}
	
	
	public String get_doorsQnty() {
		return _doorsQnty;
	}
	public void set_doorsQnty(String _doorsQnty) {
		this._doorsQnty = _doorsQnty;
	}
	public String get_transmisionType() {
		return _transmisionType;
	}
	public void set_transmisionType(String _transmisionType) {
		this._transmisionType = _transmisionType;
	}

	
}
